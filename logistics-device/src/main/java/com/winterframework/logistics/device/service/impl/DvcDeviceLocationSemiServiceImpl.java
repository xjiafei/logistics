package com.winterframework.logistics.device.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winterframework.logistics.base.enums.StatusCode;
import com.winterframework.logistics.base.enums.YesNo;
import com.winterframework.logistics.base.exception.BizException;
import com.winterframework.logistics.base.model.Context;
import com.winterframework.logistics.base.utils.DateUtils;
import com.winterframework.logistics.common.entity.Device;
import com.winterframework.logistics.common.entity.DeviceLocation;
import com.winterframework.logistics.common.entity.DeviceLocationSemi;
import com.winterframework.logistics.common.entity.DeviceLocationSemi.HandleStatus;
import com.winterframework.logistics.common.entity.TransOrder;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.common.service.IDeviceService;
import com.winterframework.logistics.common.service.impl.DeviceLocationSemiServiceImpl;
import com.winterframework.logistics.device.dao.IDvcDeviceLocationSemiDao;
import com.winterframework.logistics.device.service.IDvcDeviceLocationSemiService;
import com.winterframework.logistics.device.service.IDvcDeviceLocationService;
import com.winterframework.logistics.device.service.IDvcTransOrderService;
import com.winterframework.logistics.device.service.scheduler.location.LatLng;
import com.winterframework.logistics.device.service.scheduler.location.LocationUtil;

/**
 * 设备定位处理服务
 * @ClassName
 * @Description
 * @author ibm
 * 2017年11月29日
 */
@Service("dvcDeviceLocationSemiServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class DvcDeviceLocationSemiServiceImpl extends DeviceLocationSemiServiceImpl implements IDvcDeviceLocationSemiService {
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource(name="dvcDeviceLocationSemiDaoImpl")
	private IDvcDeviceLocationSemiDao dvcDeviceLocationSemiDao;
	
	@Resource(name="deviceServiceImpl")
	private IDeviceService deviceService;
	@Resource(name="dvcTransOrderServiceImpl")
	private IDvcTransOrderService dvcTransOrderService;
	@Resource(name="dvcDeviceLocationServiceImpl")
	private IDvcDeviceLocationService dvcDeviceLocationService;
	
	
	@Override
	public List<String> queryImeisUnhandled() throws LmException {
		List<DeviceLocationSemi.HandleStatus> statusList = new ArrayList<DeviceLocationSemi.HandleStatus>();
		statusList.add(HandleStatus.INIT);
		try{
			return dvcDeviceLocationSemiDao.getImeisByHandleStatus(statusList);
		}catch(Exception e){
			log.error("getImeisByHandleStatus error. statusList="+statusList.toString(),e);
			throw new LmException(StatusCode.DAO_ERROR,e);
		}
	}
	@Override
	public List<DeviceLocationSemi> queryUnhandleListByImei(String imei,Date fromTime, Date toTime) throws LmException {
		try{
			return dvcDeviceLocationSemiDao.getUnhandleListByImeiAndTimes(imei, fromTime.getTime(), toTime.getTime());
		}catch(Exception e){
			log.error("getUnhandleListByImeiAndTimes error. imei="+imei+" fromTime="+fromTime+" toTime="+toTime,e);
			throw new LmException(StatusCode.DAO_ERROR,e);
		}
	}
	@Override
	public DeviceLocationSemi queryPrevious(Long id) throws LmException {
		try{
			return dvcDeviceLocationSemiDao.getPrevious(id);
		}catch(Exception e){
			log.error("getLast error. id="+id,e);
			throw new LmException(StatusCode.DAO_ERROR,e);
		}
	}
	@Override
	public void handle(List<DeviceLocationSemi> semiList) throws LmException {
		/**
		 * 1.未处理List（排序）
		 * 2.过滤、聚合处理（last在|不在list中）
		 * 3.生成location
		 */
		generateLocation(aggregation(semiList));
	}
	private void generateLocation(List<DeviceLocationSemi> aggrSemiList) throws LmException{
		if(aggrSemiList!=null && aggrSemiList.size()>0){
			String imei=aggrSemiList.get(0).getImei();
			Device device=deviceService.getByNumber(imei);
			if(device==null){
				throw new LmException(com.winterframework.logistics.common.enums.StatusCode.IMEI_INVALID);
			}
			List<DeviceLocation> locationList=new ArrayList<DeviceLocation>();
			for(DeviceLocationSemi semi:aggrSemiList){
				TransOrder order=dvcTransOrderService.queryByDeviceNumberAndTime(device.getNumber(),semi.getTimeBegin());
				if(order==null){
					throw new LmException(com.winterframework.logistics.common.enums.StatusCode.HAS_NO_ORDER);
				}
				//查询是否存在已有location（deviceId+orderId+time） 有则update 无则insert
				//起始时间比订单开始时间 说明定位点存在业务重叠
				Long time=semi.getTimeBegin()<order.getStartTime()?order.getStartTime():semi.getTimeBegin();
				DeviceLocation location=dvcDeviceLocationService.queryByDeviceIdAndOrderIdAndTime(device.getId(),order.getId(),time);
				if(location==null){
					location=new DeviceLocation();
					location.setDeviceId(device.getId());
					location.setOrderId(order.getId());
					location.setLongitude(semi.getLongitude());
					location.setLatitude(semi.getLatitude());
					location.setAddress(semi.getAddress());
					location.setTime(time);
				}
				location.setRadius(semi.getRadius());
				location.setTimeStay((int)DateUtils.calcSecondsBetween(location.getTime(), semi.getTimeEnd()));
				location.setSourceType(semi.getSourceType());
				location.setSourceId(semi.getId());
				location.setStatus(YesNo.YES.getValue());
				location.setInnerCn(semi.getInnerCn());
				location.setRemark(semi.getRemark());
				locationList.add(location);
			}
			try{
				dvcDeviceLocationService.save(new Context(-1L), locationList);
			}catch(BizException e){
				log.error("device location save failed. locationList="+locationList);
				throw new LmException(e);
			}
		}
	}
	private double calcDistance(DeviceLocationSemi semiPre,DeviceLocationSemi semiCur){
		if(semiPre==null){
			return 0;
		}
		LatLng ll1=new LatLng(semiPre.getLatitude(),semiPre.getLongitude());
		LatLng ll2=new LatLng(semiCur.getLatitude(),semiCur.getLongitude());
		return LocationUtil.getDistance(ll1, ll2);
	}
	private DeviceLocationSemi aggregation(DeviceLocationSemi semiPre,DeviceLocationSemi semiCur){
		if(semiPre==null){
			return semiCur;
		}
		semiPre.setTimeEnd(semiCur.getTimeEnd());
		semiCur.setHandleStatus(DeviceLocationSemi.HandleStatus.DELETED.getValue());
		return semiPre;
	}
	private List<DeviceLocationSemi> aggregation(List<DeviceLocationSemi> semiList) throws LmException{
		final int aggDistance=2000; //10km 10000
		List<DeviceLocationSemi> locationSemiList=new ArrayList<DeviceLocationSemi>();
		if(semiList!=null && semiList.size()>0){
			DeviceLocationSemi fstSemi=semiList.get(0);
			DeviceLocationSemi semiPre=queryPrevious(fstSemi.getId());
			if(semiPre!=null){
				semiList.add(0,semiPre);
			}
			DeviceLocationSemi lastSemi=null;
			for(DeviceLocationSemi semi:semiList){
				if(calcDistance(lastSemi,semi)<=aggDistance){
					lastSemi=aggregation(lastSemi,semi);
				}else{
					lastSemi=semi;
				}
				if(!locationSemiList.contains(lastSemi)){
					locationSemiList.add(lastSemi);
					lastSemi.setHandleStatus(DeviceLocationSemi.HandleStatus.FINISHED.getValue());
				}
			}
			try{
				save(new Context(-1L), semiList);
			}catch(BizException e){
				log.error("device location semilist save failed. semiList="+semiList);
				throw new LmException(e);
			}
		}
		return locationSemiList;
	}
}
