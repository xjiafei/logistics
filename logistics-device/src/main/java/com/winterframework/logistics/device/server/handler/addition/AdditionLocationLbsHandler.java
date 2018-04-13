package com.winterframework.logistics.device.server.handler.addition;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.winterframework.logistics.base.exception.BizException;
import com.winterframework.logistics.base.model.Context;
import com.winterframework.logistics.base.utils.DateUtils;
import com.winterframework.logistics.common.entity.DeviceLocationBts;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.common.service.IDeviceLocationBtsService;
import com.winterframework.logistics.device.server.AbstractAdditionHandler;
 
@Service("additionLocationLbsHandler")
public class AdditionLocationLbsHandler extends AbstractAdditionHandler{
	@Resource(name="deviceLocationLbsServiceImpl")
	private IDeviceLocationBtsService deviceLocationLbsService;  
	
	
	@Override
	public void handle(Context ctx, String additionData) throws LmException {
		//&PD0D1D2D3 D4D5D6D7 D8D9D10D11 D12D13D14D15,L1L2L3L4C1C2C3C4,L5L6L7L8C5C6C7C8
		//&P0460 0000 27a1 0dfe
		
		String imei=(String)ctx.get("imei");
		String mcc=additionData.substring(0,4);
		String mnc=additionData.substring(4,8);
		String lac=additionData.substring(8,12);
		String ci=additionData.substring(12,16);
		DeviceLocationBts locationLbs=new DeviceLocationBts();
		locationLbs.setImei(imei);
		locationLbs.setMcc(mcc);
		locationLbs.setMnc(mnc);
		locationLbs.setLac1(Integer.parseInt(lac,16)+"");
		locationLbs.setCi1(Integer.parseInt(ci,16)+"");
		locationLbs.setStatus(DeviceLocationBts.Status.YES.getValue());
		locationLbs.setTime(DateUtils.getCurTime());
		
		try{
			deviceLocationLbsService.save(ctx.setUserId(99999L), locationLbs);
		}catch(BizException e){
			log.error("device location save battery error.",e);
			throw new LmException(e);
		}
 
	}
}