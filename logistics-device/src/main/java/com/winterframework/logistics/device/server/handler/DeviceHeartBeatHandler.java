package com.winterframework.logistics.device.server.handler;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.winterframework.logistics.base.model.Context;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.device.server.AbstractAdditionHandler;
import com.winterframework.logistics.device.server.AbstractHandler;
import com.winterframework.logistics.device.server.protocol.VkDevice;
import com.winterframework.logistics.device.server.protocol.VkServer;
import com.winterframework.modules.spring.exetend.PropertyConfig;
 
/**
 * 设备心跳包处理类
 * @ClassName
 * @Description
 * @author ibm 
 */
@Service("deviceHeartBeatHandler")
public class DeviceHeartBeatHandler extends AbstractHandler{
	@Override
	protected VkServer doHandle(Context ctx, VkDevice vkDevice) throws LmException{
		//*MG20113800138000,AH&B1234ABCDEF&M458&N16#
		//&B1234ABCDEF&M458&N16
		return super.doHandle(ctx, vkDevice);
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