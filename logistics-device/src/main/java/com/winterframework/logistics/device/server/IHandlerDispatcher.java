/**   
* @Title: IEventDispatcher.java 
* @Package com.winterframework.firefrog.event.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author 你的名字   
* @date 2014-5-8 上午9:12:12 
* @version V1.0   
*/
package com.winterframework.logistics.device.server;

import com.winterframework.logistics.base.model.Context;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.device.server.protocol.VkDevice;
import com.winterframework.logistics.device.server.protocol.VkServer;

 
/**
 * 处理器分发
 * @ClassName
 * @Description
 * @author ibm
 * 2015年3月16日
 */
public interface IHandlerDispatcher { 
	VkServer dispatch(Context ctx,VkDevice req) throws LmException;
	void connect(String terminalId) throws LmException;
	void disconnect(String terminalId) throws LmException;

}
