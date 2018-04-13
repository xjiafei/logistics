package com.winterframework.logistics.device.server.handler;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.winterframework.logistics.base.model.Context;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.device.server.AbstractAdditionHandler;
import com.winterframework.logistics.device.server.AbstractHandler;
import com.winterframework.logistics.device.server.protocol.VkDevice;
import com.winterframework.logistics.device.server.protocol.VkServer;
 
/**
 * 设备定位上传处理类
 * @ClassName
 * @Description
 * @author ibm
 * 2015年3月17日
 * 
 * 设备响应的定位数据入库 
 */
@Service("deviceLocationHandler")
public class DeviceLocationHandler extends AbstractHandler{
	@Override
	protected VkServer doHandle(Context ctx, VkDevice vkDevice) throws LmException {
		//*MG20113800138000,BB3(&A0732142233550011405829060520190600&B0000000000) (&A0732142233550011405829060520190600&B0000000000) (&A0732142233550011405829060520190600&B0000000000)#
		//*MG20113800138000,BA&A0732142233550011405829060520190600&B0000000000#
		return super.doHandle(ctx, vkDevice);
	}
	public static void main(String[] sss){
		String data="&A0732142233550011405829060520190600&B0000000000";
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