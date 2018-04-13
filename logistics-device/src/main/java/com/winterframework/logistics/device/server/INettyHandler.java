package com.winterframework.logistics.device.server;

import com.winterframework.logistics.base.exception.BizException;
import com.winterframework.logistics.device.server.protocol.VkServer;

import io.netty.channel.ChannelHandlerContext;
 
/**
 * 服务主处理接口
 * @ClassName
 * @Description
 * @author ibm
 * 2015年3月17日
 */
public interface INettyHandler {
	void active(final ChannelHandlerContext ctx) throws BizException;
    void read(final ChannelHandlerContext ctx, final Object obj) throws BizException;
    void inactive(final ChannelHandlerContext ctx) throws BizException;
    void push(final String terminalId, final VkServer server) throws BizException;
}
