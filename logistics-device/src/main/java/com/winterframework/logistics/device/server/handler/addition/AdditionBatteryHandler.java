package com.winterframework.logistics.device.server.handler.addition;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.winterframework.logistics.base.exception.BizException;
import com.winterframework.logistics.base.model.Context;
import com.winterframework.logistics.base.utils.DateUtils;
import com.winterframework.logistics.common.entity.Device;
import com.winterframework.logistics.common.entity.DeviceBattery;
import com.winterframework.logistics.common.entity.TransOrder;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.common.service.IDeviceBatteryService;
import com.winterframework.logistics.common.service.IDeviceService;
import com.winterframework.logistics.device.server.AbstractAdditionHandler;
import com.winterframework.logistics.device.service.IDvcTransOrderService;
 
@Service("additionBatteryHandler")
public class AdditionBatteryHandler extends AbstractAdditionHandler{
	@Resource(name="deviceServiceImpl")
	private IDeviceService deviceService;  
	@Resource(name="deviceBatteryServiceImpl")
	private IDeviceBatteryService deviceBatteryService;  
	@Resource(name="dvcTransOrderServiceImpl")
	private IDvcTransOrderService dvcTransOrderService;
	
	@Override
	public void handle(Context ctx, String additionData) throws LmException {
		//&B1234ABCDEF&M458&N16
		String imei=(String)ctx.get("imei");
		Integer value=Integer.valueOf(additionData)/10;
		try{
			ctx.setUserId(99999L);
			
			Device device=deviceService.getByNumber(imei);
			device.setBattery(value);
			deviceService.save(ctx, device);
			
			TransOrder transOrder=dvcTransOrderService.queryByDeviceNumberAndTime(device.getNumber(),DateUtils.getCurTime());
			
			DeviceBattery deviceBattery=new DeviceBattery();
			deviceBattery.setDeviceId(device.getId());
			if(transOrder!=null){
				deviceBattery.setOrderId(transOrder.getId());
			}else{
				deviceBattery.setOrderId(-1L);
			}
			deviceBattery.setBattery(value);
			deviceBattery.setTime(DateUtils.getCurTime());
			deviceBattery.setStatus(DeviceBattery.Status.YES.getValue());
			
			deviceBatteryService.save(ctx, deviceBattery);
		}catch(BizException e){
			log.error("device save battery error.",e);
			throw new LmException(e);
		}
 
	}
}