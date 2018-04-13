package com.winterframework.logistics.device.server.notification;

import com.winterframework.logistics.device.server.ServerException;


public interface INotificationService {
	/**
	 * @param notification
	 * @return command(具体指令内容)
	 * @throws ServerException
	 */
	public String notify(Notification notification) throws ServerException;
}
