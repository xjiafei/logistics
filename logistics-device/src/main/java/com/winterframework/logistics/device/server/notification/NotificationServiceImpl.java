package com.winterframework.logistics.device.server.notification;

import javax.annotation.Resource;

import org.apache.commons.lang3.EnumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.winterframework.logistics.common.entity.Device;
import com.winterframework.logistics.common.service.IDeviceService;
import com.winterframework.logistics.device.server.NotificationManager;
import com.winterframework.logistics.device.server.ServerException;
import com.winterframework.logistics.device.server.protocol.VkServer;
import com.winterframework.logistics.device.service.IDeviceCmdService;

@Service("notificationServiceImpl")
public class NotificationServiceImpl implements INotificationService{
	private static final Logger log= LoggerFactory.getLogger(NotificationServiceImpl.class);
	@Resource(name="deviceServiceImpl")
	IDeviceService deviceService;
	
	@Override
	public String notify(Notification notification) throws ServerException{
		Device device=deviceService.getByNumber(notification.getTarget());
		boolean isT128=EnumUtils.getEnum(Device.Model.class, device.getModel()).equals(Device.Model.T128);
		VkServer server=new VkServer(isT128);
		server.setFnType(notification.getFnType());
		server.setFnKey(notification.getFnKey());
		server.setData(notification.getData());
		server.setSave(notification.getIsSave()?1:0);
		server.setReply(notification.getIsReply()?1:0);
		
		return NotificationManager.push(notification.getTarget(), server);
	}
}
