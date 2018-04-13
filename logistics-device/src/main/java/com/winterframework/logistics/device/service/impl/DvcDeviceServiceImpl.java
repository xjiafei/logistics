package com.winterframework.logistics.device.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winterframework.logistics.base.exception.BizException;
import com.winterframework.logistics.base.model.Context;
import com.winterframework.logistics.base.utils.DateUtils;
import com.winterframework.logistics.common.entity.Device;
import com.winterframework.logistics.common.entity.DeviceLocationBts;
import com.winterframework.logistics.common.entity.DeviceLocationGps;
import com.winterframework.logistics.common.enums.StatusCode;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.common.service.impl.DeviceServiceImpl;
import com.winterframework.logistics.device.service.IDvcDeviceLocationBtsService;
import com.winterframework.logistics.device.service.IDvcDeviceLocationGpsService;
import com.winterframework.logistics.device.service.IDvcDeviceLocationService;
import com.winterframework.logistics.device.service.IDvcDeviceService;

/**
 * 设备服务
 * @ClassName
 * @Description
 * @author ibm
 * 2017年11月29日
 */
@Service("dvcDeviceServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class DvcDeviceServiceImpl extends DeviceServiceImpl implements IDvcDeviceService {
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource(name="dvcDeviceLocationServiceImpl")
	private IDvcDeviceLocationService dvcDeviceLocationService;
	@Resource(name="dvcDeviceLocationGpsServiceImpl")
	private IDvcDeviceLocationGpsService dvcDeviceLocationGpsService;
	@Resource(name="dvcDeviceLocationBtsServiceImpl")
	private IDvcDeviceLocationBtsService dvcDeviceLocationBtsService;
	
	@Override
	public void onffRefresh(String imei) throws LmException {
		/**
		 * 只有状态发生变化 才更新
		 * 在线：连上服务器
		 * 休眠：offtime超过7分钟 且  1个频率内没有定位数据 || offtime超过3个频率 且3个频率内有定位数据
		 * 离线：offtime超过3个频率 且 3个频率内没有定位数据
		 */
		Device device=getByNumber(imei);
		if(device==null){
			throw new LmException(StatusCode.IMEI_INVALID);
		}
		final int periodMin=device.getLocationFreq()-5;
		final int periodMax=device.getLocationFreq()+5;
		
		final int disconnectMins=7;
		if(device.getOnffTime()!=null){
			Date offTime=DateUtils.getDate(device.getOnffTime());
			int oldOnff=device.getOnff().intValue();
			if(device.getOnff().intValue()==Device.ONFF.ONLINE.getValue()){
				if(DateUtils.calcMinutesBetween(offTime, DateUtils.currentDate())>disconnectMins
				&& DateUtils.calcMinutesBetween(offTime, DateUtils.currentDate())<periodMax){
					if(!hasLocationData(imei,periodMax)){
						device.setOnff(Device.ONFF.SLEEP.getValue());
					}
				}else if(DateUtils.calcMinutesBetween(offTime, DateUtils.currentDate())>periodMin){
					if(hasLocationData(imei,periodMax)){
						device.setOnff(Device.ONFF.SLEEP.getValue());
					}else{
						device.setOnff(Device.ONFF.OFFLINE.getValue());
					}
				}
			}else if(device.getOnff().intValue()==Device.ONFF.SLEEP.getValue()){
				if(DateUtils.calcMinutesBetween(offTime, DateUtils.currentDate())>periodMin){
					if(!hasLocationData(imei,periodMax)){
						device.setOnff(Device.ONFF.OFFLINE.getValue());
					}
				}
			}else if(device.getOnff().intValue()==Device.ONFF.OFFLINE.getValue()){
				if(DateUtils.calcMinutesBetween(offTime, DateUtils.currentDate())>periodMin){
					if(hasLocationData(imei,periodMax)){
						device.setOnff(Device.ONFF.SLEEP.getValue());
					}
				}
			}
			if(oldOnff!=device.getOnff().intValue()){
				device.setOnffTime(DateUtils.getCurTime());
				try {
					save(new Context(-1L), device);
				} catch (BizException e) {
					log.error("dao error.",e);
					throw new LmException(com.winterframework.logistics.base.enums.StatusCode.DAO_ERROR);
				}
			}
		}
	}
	
	private boolean hasLocationData(String imei,int periodMins) throws LmException{
		List<DeviceLocationGps> gpsList=dvcDeviceLocationGpsService.queryListByImeiAndTimes(imei,DateUtils.addMinutes(DateUtils.currentDate(), -periodMins), DateUtils.currentDate());
		if(gpsList.isEmpty()){
			List<DeviceLocationBts> btsList=dvcDeviceLocationBtsService.queryListByImeiAndTimes(imei,DateUtils.addMinutes(DateUtils.currentDate(), -periodMins), DateUtils.currentDate());
			if(btsList.isEmpty()){
				return false;
			}
		}
		return true;
	}
}
