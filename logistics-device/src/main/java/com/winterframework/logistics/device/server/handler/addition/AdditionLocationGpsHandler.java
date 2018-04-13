package com.winterframework.logistics.device.server.handler.addition;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.winterframework.logistics.base.exception.BizException;
import com.winterframework.logistics.base.model.Context;
import com.winterframework.logistics.base.utils.DateUtils;
import com.winterframework.logistics.common.entity.DeviceLocationGps;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.common.service.IDeviceLocationGpsService;
import com.winterframework.logistics.device.server.AbstractAdditionHandler;
 
@Service("additionLocationGpsHandler")
public class AdditionLocationGpsHandler extends AbstractAdditionHandler{
	@Resource(name="deviceLocationGpsServiceImpl")
	private IDeviceLocationGpsService deviceLocationGpsService;  
	
	
	@Override
	public void handle(Context ctx, String additionData) throws LmException {
		//&A0732142233550011405829060520190600&B0000000000
		String imei=(String)ctx.get("imei");
		double latitude=convertLatLng(additionData.substring(6,10)+"."+additionData.substring(10,14));
		double longitude=convertLatLng(additionData.substring(14,19)+"."+additionData.substring(19,23));
		int locFlag=Integer.valueOf(additionData.substring(23,24));
		int speed=Integer.valueOf(additionData.substring(24,26))*2;	//单位2节
		int direction=Integer.valueOf(additionData.substring(26,28))*10;	//单位10度
		Date time=DateUtils.parse(additionData.substring(28,34)+additionData.substring(0,6), "ddMMyyHHmmss");
		double factor=0.0;
		if(additionData.length()==43){
			factor=Long.valueOf(additionData.substring(34,43))*1.0/10000;
		}
		
		try{
			DeviceLocationGps locationGps=new DeviceLocationGps();
			locationGps.setImei(imei);
			locationGps.setLongitude(longitude);
			locationGps.setLatitude(latitude);
			locationGps.setLocationFlag(locFlag);
			locationGps.setSpeed(speed);
			locationGps.setDirection(direction);
			locationGps.setFactor(factor);
			locationGps.setTime(time.getTime());
			locationGps.setStatus(DeviceLocationGps.Status.YES.getValue());
			deviceLocationGpsService.save(ctx.setUserId(99999L), locationGps);
		}catch(BizException e){
			log.error("device location save battery error.",e);
			throw new LmException(e);
		}
 
	}
	private double convertLatLng(String value){
		value=value.length()==9?"0"+value:value;
		return Integer.valueOf(value.substring(0,3))+Integer.valueOf(value.substring(3,5))*1.0/60+Integer.valueOf(value.substring(6,10))*1.0/600000;
	}
	
	public static void main(String[] s){
		String additionData="1820212233470511358257660001131217";
		//double latitude=convertLatLng(additionData.substring(6,10)+"."+additionData.substring(10,14));
		//double longitude=convertLatLng(additionData.substring(14,19)+"."+additionData.substring(19,23));
		int locFlag=Integer.valueOf(additionData.substring(23,24));
		int speed=Integer.valueOf(additionData.substring(24,26))*2;	//单位2节
		int direction=Integer.valueOf(additionData.substring(26,28))*10;	//单位10度
		Date time=DateUtils.parse(additionData.substring(28,34)+additionData.substring(0,6), "ddMMyyHHmmss");
		double factor=0.0;
		if(additionData.length()==43){
			factor=Long.valueOf(additionData.substring(34,43))*1.0/10000;
		}
		System.out.println(Integer.valueOf("11220000")*1.0/10000);
		String value="2233.4821";value=value.length()==9?"0"+value:value;
		System.out.println(Integer.valueOf(value.substring(0,3))+Integer.valueOf(value.substring(3,5))*1.0/60+Integer.valueOf(value.substring(6,10))*1.0/600000);
	}
}