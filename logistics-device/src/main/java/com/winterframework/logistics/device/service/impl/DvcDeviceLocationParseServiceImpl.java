package com.winterframework.logistics.device.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.winterframework.logistics.base.enums.YesNo;
import com.winterframework.logistics.base.exception.BizException;
import com.winterframework.logistics.base.model.Context;
import com.winterframework.logistics.base.utils.DateUtils;
import com.winterframework.logistics.base.utils.JsonUtils;
import com.winterframework.logistics.common.entity.Device;
import com.winterframework.logistics.common.entity.DeviceLocationBts;
import com.winterframework.logistics.common.entity.DeviceLocationGps;
import com.winterframework.logistics.common.entity.DeviceLocationSemi;
import com.winterframework.logistics.common.entity.TransOrder;
import com.winterframework.logistics.common.enums.StatusCode;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.common.service.IDeviceLocationSemiService;
import com.winterframework.logistics.common.service.IDeviceService;
import com.winterframework.logistics.device.service.IDvcDeviceLocationBtsService;
import com.winterframework.logistics.device.service.IDvcDeviceLocationGpsService;
import com.winterframework.logistics.device.service.IDvcDeviceLocationParseService;
import com.winterframework.logistics.device.service.IDvcTransOrderService;
import com.winterframework.logistics.device.service.scheduler.location.AmapUtil;
import com.winterframework.logistics.device.service.scheduler.location.GaoDeLocationStruc;
import com.winterframework.logistics.device.service.scheduler.location.Gps2Amap;
import com.winterframework.logistics.device.service.scheduler.location.LatLng;
import com.winterframework.logistics.device.service.scheduler.location.Regeocode;

/**
 * 设备定位数据解析服务
 * @ClassName
 * @Description
 * @author ibm
 * 2017年11月29日
 */
//@Service("dvcDeviceLocationParseServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class DvcDeviceLocationParseServiceImpl implements IDvcDeviceLocationParseService {
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource(name = "dvcDeviceLocationGpsServiceImpl")
	private IDvcDeviceLocationGpsService dvcDeviceLocationGpsService;
	@Resource(name = "dvcDeviceLocationBtsServiceImpl")
	private IDvcDeviceLocationBtsService dvcDeviceLocationBtsService;
	@Resource(name = "deviceLocationSemiServiceImpl")
	private IDeviceLocationSemiService deviceLocationSemiService;
	@Resource(name = "deviceServiceImpl")
	private IDeviceService deviceService;
	@Resource(name = "dvcTransOrderServiceImpl")
	private IDvcTransOrderService dvcTransOrderService;
	
	
	@Override
	public void executeGps() {
		Date fromDate=DateUtils.addHours(DateUtils.currentDate(),-6);
		Date toDate=DateUtils.currentDate();
		try{
			List<DeviceLocationGps> gpsList=dvcDeviceLocationGpsService.queryUnhandleList(fromDate, toDate);
			List<DeviceLocationSemi> locationSemiList=new ArrayList<DeviceLocationSemi>();
			List<DeviceLocationGps> locationGpsList=new ArrayList<DeviceLocationGps>();
			for(DeviceLocationGps gps:gpsList){
				try{
					Device device=deviceService.getByNumber(gps.getImei());
					if(device==null){
						throw new LmException(StatusCode.IMEI_INVALID);
					}
					TransOrder order=dvcTransOrderService.queryByDeviceNumberAndTime(device.getNumber(),gps.getTime());
					if(order==null){
						throw new LmException(StatusCode.HAS_NO_ORDER);
					}
					DeviceLocationSemi locationSemi=new DeviceLocationSemi();
					locationSemi.setImei(gps.getImei());
					locationSemi.setLongitude(gps.getLongitude());
					locationSemi.setLatitude(gps.getLatitude());
					locationSemi.setRadius((int)(gps.getFactor()/1000));
					//locationSemi.setDistance();
					
					locationSemi.setTimeBegin(gps.getTime());
					locationSemi.setTimeEnd(gps.getTime());
					locationSemi.setSourceType(DeviceLocationSemi.SourceType.GPS.getValue());
					locationSemi.setSourceId(gps.getId());
					locationSemi.setStatus(YesNo.NO.getValue());
					locationSemi.setHandleStatus(DeviceLocationSemi.HandleStatus.INIT.getValue());
					locationSemi.setRemark("geocoding from amap.");
				    
					LatLng latLng = Gps2Amap.transWGS2GCJ(new LatLng(gps.getLatitude(),gps.getLongitude()));
					
					try{
						Regeocode regeocode =AmapUtil.getCityNameByLocation(latLng.longitude+","+latLng.latitude);
						log.info("Geocoding gps logitude="+latLng.longitude+" latitude="+latLng.latitude+" result:"+JsonUtils.toJson(regeocode));
						if(regeocode==null){
							locationSemi.setAddress("UNKOWN");
						}else{
							locationSemi.setAddress(regeocode.getFormatted_address());
							//locationSemi.setCity((regeocode.getAddressComponent().getCity()==null||regeocode.getAddressComponent().getCity()=="")?regeocode.getAddressComponent().getProvince():regeocode.getAddressComponent().getCity());
							locationSemi.setStatus(YesNo.YES.getValue());
						}
					} catch (Exception e) {
						log.error("parse gps address failed.", e);
						locationSemi.setAddress("UNKOWN");
					}
					gps.setRemark("success");
					locationSemiList.add(locationSemi);
				}catch(Exception e){
					String emsg=BizException.getStackTraceMsg(e);
					if(e instanceof LmException){
						log.error("parse gps location failed.status="+((LmException)e).getCode()+" msg="+((LmException)e).getMessage(),e);
					}else{
						log.error("parse gps location failed.", e);
					}
					gps.setRemark("failed:"+emsg.substring(0,200));
				}
				gps.setHandleFlag(YesNo.YES.getValue());
				locationGpsList.add(gps);
			}
			deviceLocationSemiService.save(new Context(-1L), locationSemiList);
			dvcDeviceLocationGpsService.save(new Context(-1L), locationGpsList);
		}catch(Exception e){
			log.error("device location parse error.fromDate="+fromDate+" toDate="+toDate,e);
		}
	}
	@Override
	public void executeBts() {
		final String key="a445886dba2e781c569899bf0478670c";
		final String baseUrl = "http://apilocate.amap.com/position?key="+key+"&";
		Date fromDate=DateUtils.addHours(DateUtils.currentDate(),-6);
		Date toDate=DateUtils.currentDate();
		try{
			List<DeviceLocationBts> lbsList=dvcDeviceLocationBtsService.queryUnhandleList(fromDate, toDate);
			List<DeviceLocationSemi> locationSemiList=new ArrayList<DeviceLocationSemi>();
			List<DeviceLocationBts> locationBtsList=new ArrayList<DeviceLocationBts>();
			for(DeviceLocationBts bts:lbsList){
				try{
					Device device=deviceService.getByNumber(bts.getImei());
					if(device==null){
						throw new LmException(StatusCode.IMEI_INVALID);
					}
					TransOrder order=dvcTransOrderService.queryByDeviceNumberAndTime(device.getNumber(),bts.getTime());
					if(order==null){
						throw new LmException(StatusCode.HAS_NO_ORDER);
					}
					DeviceLocationSemi locationSemi=new DeviceLocationSemi();
					locationSemi.setImei(bts.getImei());
					//locationSemi.setDistance();
					locationSemi.setTimeBegin(bts.getTime());
					locationSemi.setTimeEnd(bts.getTime());
					locationSemi.setSourceType(DeviceLocationSemi.SourceType.BTS.getValue());
					locationSemi.setSourceId(bts.getId());
					locationSemi.setStatus(YesNo.NO.getValue());
					locationSemi.setHandleStatus(DeviceLocationSemi.HandleStatus.INIT.getValue());
					locationSemi.setRemark("geocoding from amap.");
					try{
						StringBuffer url = new StringBuffer();
						url.append(baseUrl);
						url.append("&accesstype=0");
						url.append("&imei="+device.getNumber());
						url.append("&cdma=0");
						url.append("&bts="+bts.getMcc()+","+bts.getMnc()+","+bts.getLac1()+","+bts.getCi1()+","+bts.getRssi1());
						if(bts.getLac2()!=null){
							url.append("&nearbts="+bts.getMcc()+","+bts.getMnc()+","+bts.getLac2()+","+bts.getCi2()+","+bts.getRssi2());
							if(bts.getLac3()!=null){
								url.append("|"+bts.getMcc()+","+bts.getMnc()+","+bts.getLac3()+","+bts.getCi3()+","+bts.getRssi3());
								if(bts.getLac4()!=null){
									url.append("|"+bts.getMcc()+","+bts.getMnc()+","+bts.getLac4()+","+bts.getCi4()+","+bts.getRssi4());
									if(bts.getLac5()!=null){
										url.append("|"+bts.getMcc()+","+bts.getMnc()+","+bts.getLac5()+","+bts.getCi5()+","+bts.getRssi5());
									}
								}
							}
						}
						url.append("&output=json");
						GaoDeLocationStruc result = AmapUtil.getGaoDeLocation(url.toString());
						log.info("Geocoding bts request:"+url.toString()+" result:"+JsonUtils.toJson(result));
						if(result!=null){
							LatLng latlng =  new LatLng(result.getLocation().split(",")[1]+","+result.getLocation().split(",")[0]);
							latlng=Gps2Amap.transGCJ2WGS(latlng);
							locationSemi.setLongitude(latlng.getLongitude());
							locationSemi.setLatitude(latlng.getLatitude());
							locationSemi.setRadius((result.getRadius()!=null && Integer.valueOf(result.getRadius())<500 && Integer.valueOf(result.getRadius())>0)?Integer.valueOf(result.getRadius()):500);
							if(null!=result.getDesc()){
								locationSemi.setAddress(result.getDesc().replace(" " , ""));
								//locationSemi.setCity((regeocode.getAddressComponent().getCity()==null||regeocode.getAddressComponent().getCity()=="")?regeocode.getAddressComponent().getProvince():regeocode.getAddressComponent().getCity());
								locationSemi.setStatus(YesNo.YES.getValue());
							}
						}else{
							locationSemi.setLongitude(0.0);
							locationSemi.setLatitude(0.0);
						}
					} catch (Exception e){
						locationSemi.setLongitude(0.0);
						locationSemi.setLatitude(0.0);
						log.error("parse bts address failed.", e);
					}
					bts.setRemark("success");
					locationSemiList.add(locationSemi);
				}catch(Exception e){
					String emsg=BizException.getStackTraceMsg(e);
					if(e instanceof LmException){
						log.error("parse bts location failed.status="+((LmException)e).getCode()+" msg="+((LmException)e).getMessage(),e);
					}else{
						log.error("parse bts location failed.", e);
					}
					bts.setRemark("failed:"+emsg.substring(0,200));
				}
				bts.setHandleFlag(YesNo.YES.getValue());
				locationBtsList.add(bts);
			}
			deviceLocationSemiService.save(new Context(-1L), locationSemiList);
			dvcDeviceLocationBtsService.save(new Context(-1L), locationBtsList);
		}catch(Exception e){
			log.error("device location parse error.fromDate="+fromDate+" toDate="+toDate);
		}
		
	}
	@Override
	public void executeGoogleReGeocodeByGps() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void excuteGoogleGeolocationByBts() {
		// TODO Auto-generated method stub
		
	}

	
	
}
