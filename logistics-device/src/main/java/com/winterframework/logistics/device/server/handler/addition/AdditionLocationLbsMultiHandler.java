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
 
@Service("additionLocationLbsMultiHandler")
public class AdditionLocationLbsMultiHandler extends AbstractAdditionHandler{
	@Resource(name="deviceLocationLbsServiceImpl")
	private IDeviceLocationBtsService deviceLocationLbsService;  
	
	
	@Override
	public void handle(Context ctx, String additionData) throws LmException {
		//&XM,N,L1,C1,D1;L2,C2,D2……Ln,Cn,Dn
		String imei=(String)ctx.get("imei");
		String mcc=additionData.substring(0,additionData.indexOf(","));
		String tmpData=additionData.substring(additionData.indexOf(",")+1);
		String mnc=tmpData.substring(0,tmpData.indexOf(","));
		String[] lbss=tmpData.substring(tmpData.indexOf(",")+1).split(";");
		DeviceLocationBts locationLbs=new DeviceLocationBts();
		locationLbs.setImei(imei);
		locationLbs.setMcc(mcc);
		locationLbs.setMnc(mnc);
		for(int i=0;i<lbss.length && i<5;i++){
			if(i==0){
				locationLbs.setLac1(lbss[i].split(",")[0]);
				locationLbs.setCi1(lbss[i].split(",")[1]);
				locationLbs.setRssi1(lbss[i].split(",")[2]);
			}
			if(i==1){
				locationLbs.setLac2(lbss[i].split(",")[0]);
				locationLbs.setCi2(lbss[i].split(",")[1]);
				locationLbs.setRssi2(lbss[i].split(",")[2]);
			}
			if(i==2){
				locationLbs.setLac3(lbss[i].split(",")[0]);
				locationLbs.setCi3(lbss[i].split(",")[1]);
				locationLbs.setRssi3(lbss[i].split(",")[2]);
			}
			if(i==3){
				locationLbs.setLac4(lbss[i].split(",")[0]);
				locationLbs.setCi4(lbss[i].split(",")[1]);
				locationLbs.setRssi4(lbss[i].split(",")[2]);
			}
			if(i==4){
				locationLbs.setLac5(lbss[i].split(",")[0]);
				locationLbs.setCi5(lbss[i].split(",")[1]);
				locationLbs.setRssi5(lbss[i].split(",")[2]);
			}
		}
		locationLbs.setTime(DateUtils.getCurTime());
		locationLbs.setStatus(DeviceLocationBts.Status.YES.getValue());
		
		try{
			deviceLocationLbsService.save(ctx.setUserId(99999L), locationLbs);
		}catch(BizException e){
			log.error("device location save battery error.",e);
			throw new LmException(e);
		}
 
	}
	public static void main(String[] s){
		String additionData="460,0,10145,3572,60;10145,3573,57;10145,3582,57;10145,3583,63;10145,3581,63;10145,3682,74;10145,3571,77";
		String mcc=additionData.substring(0,additionData.indexOf(","));
		String tmpData=additionData.substring(additionData.indexOf(",")+1);
		String mnc=tmpData.substring(0,tmpData.indexOf(","));
		String[] lbss=tmpData.substring(tmpData.indexOf(",")+1).split(";");
		System.out.println(11);
	}
}