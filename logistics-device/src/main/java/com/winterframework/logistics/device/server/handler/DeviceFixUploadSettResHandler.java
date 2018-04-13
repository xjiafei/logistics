package com.winterframework.logistics.device.server.handler;

import org.springframework.stereotype.Service;

import com.winterframework.logistics.base.model.Context;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.device.server.protocol.VkDevice;
import com.winterframework.logistics.device.server.protocol.VkServer;
 
/**
 * 设备设置固定上传时间响应处理类
 * @ClassName
 * @Description
 * @author ibm 
 */
@Service("deviceFixUploadSettResHandler")
public class DeviceFixUploadSettResHandler extends AbstractDeviceSettResHandler{
	
	@Override
	protected VkServer doHandle(Context ctx, VkDevice vkDevice) throws LmException{
		return super.doHandle(ctx, vkDevice);
	}	
}