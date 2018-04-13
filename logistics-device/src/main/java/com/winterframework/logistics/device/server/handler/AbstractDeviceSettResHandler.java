package com.winterframework.logistics.device.server.handler;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.winterframework.logistics.base.model.Context;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.device.server.AbstractHandler;
import com.winterframework.logistics.device.server.protocol.VkDevice;
import com.winterframework.logistics.device.server.protocol.VkServer;
import com.winterframework.logistics.device.service.IDeviceCmdService;
 
/**
 * 设备设置响应处理基类
 * @ClassName
 * @Description
 * @author ibm 
 */
@Service("abstractDeviceSettResHandler")
public class AbstractDeviceSettResHandler extends AbstractHandler{
	@Resource(name = "deviceCmdServiceImpl")
	private IDeviceCmdService deviceCmdService;
	
	@Override
	protected VkServer doHandle(Context ctx, VkDevice vkDevice) throws LmException{
		super.doHandle(ctx, vkDevice);
		deviceCmdService.success(ctx,vkDevice.getTerminalId(), vkDevice.getFnType()+vkDevice.getFnKey());
		return null;
	}	
}