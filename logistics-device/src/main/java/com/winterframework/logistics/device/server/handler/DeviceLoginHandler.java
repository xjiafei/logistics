package com.winterframework.logistics.device.server.handler;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.winterframework.logistics.base.model.Context;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.common.service.IDeviceService;
import com.winterframework.logistics.device.server.AbstractHandler;
import com.winterframework.logistics.device.server.protocol.VkDevice;
import com.winterframework.logistics.device.server.protocol.VkServer;
 
/**
 * 设备登录处理类
 * @ClassName
 * @Description
 * @author ibm 
 */
@Service("deviceLoginHandler")
public class DeviceLoginHandler extends AbstractHandler{
	@Resource(name = "deviceServiceImpl")
	private IDeviceService deviceService;
	
	@Override
	protected VkServer doHandle(Context ctx, VkDevice vkDevice) throws LmException{
		//*MG20113800138000,AH&B1234ABCDEF&M458&N16#
		//&B1234ABCDEF&M458&N16
		VkServer server= super.doHandle(ctx, vkDevice);
		/*在更前中 更新
		String imei=vkDevice.getTerminalId();
		Device device=deviceService.getByNumber(imei);
		if(device!=null){
			device.setOnff(YesNo.YES.getValue());
			device.setOnffTime(DateUtils.getCurTime());
			try {
				deviceService.save(ctx, device);
			} catch (BizException e) {
				log.error("device login:save onff failed.imei="+imei);
			}
		}*/
		return server;
	}	
	public static void main(String[] sss){
		String data="&M458";
		String[] d=data.split("&");
		for(String s:d){
		if(StringUtils.isNotEmpty(s)){
			String additionType=s.substring(0,1);
			String additionData=s.substring(1);
			System.out.println("type:"+additionType+" data:"+additionData);
			
			}
		}
	}
}