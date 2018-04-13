package com.winterframework.logistics.device.server;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.winterframework.logistics.base.enums.YesNo;
import com.winterframework.logistics.base.exception.BizException;
import com.winterframework.logistics.base.model.Context;
import com.winterframework.logistics.base.redis.RedisClient;
import com.winterframework.logistics.device.server.protocol.ProtocolUtil;
import com.winterframework.logistics.device.server.protocol.VkDevice;
import com.winterframework.logistics.device.server.protocol.VkServer;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;

@Service("serviceHandler")
public class ServiceHandler implements INettyHandler{
	private static final Logger log = LoggerFactory.getLogger(ServiceHandler.class); 
	
	@Resource(name="handlerDispatcher")
	private IHandlerDispatcher handlerDispatcher;
	@Resource(name="RedisClient")
	private RedisClient redisClient;	
	
	@Override
	public void active(ChannelHandlerContext ctx) throws BizException{
		/**
		 * 1.获取句柄--handle
		 * 2.生成token
		 * 3.管理句柄
		 */
	}
	@Override 
	public void read(ChannelHandlerContext ctx,Object obj) throws BizException{
		VkDevice vkDevice =ProtocolUtil.decode((String)obj);
		vkDevice.setIp(ProtocolUtil.getClientIp(ctx.channel()));
		
		log.info(vkDevice.toString());
		VkServer server=null;
		try{
			ChannelManager.save(vkDevice.getTerminalId(),ctx.channel());
			handlerDispatcher.connect(vkDevice.getTerminalId());
			Context context=new Context(-1L);	//系统
			context.set("imei", vkDevice.getTerminalId());
			server=handlerDispatcher.dispatch(context,vkDevice);
			if(null==server) return;	//没有响应  则不回写
		}catch(Exception e){
			log.error("Exception:",e);
		} 
		//设备的所有响应均返回status=0  除非更新新版软件
		
		log.info(server.toString());
		if(vkDevice.getReply()==YesNo.YES.getValue()){ //确保没有实现错
			final ChannelFuture f=ctx.channel().writeAndFlush(ProtocolUtil.encode(server));
		}
	}
	@Override
	public void inactive(ChannelHandlerContext ctx) throws BizException {
		String terminalId=ChannelManager.remove(ctx.channel());
		handlerDispatcher.disconnect(terminalId);
	}
	@Override
	public void push(String terminalId, VkServer server) throws BizException {
		log.info("terminalId="+terminalId+","+server.toString()); 
		Channel channel=ChannelManager.get(terminalId);  
		if(null==channel){
			log.error("用户未登录，通道为空：terminalId = "+terminalId);
			return;
			//throw new ServerException(StatusCode.UN_CONNECT.getValue());
		}
		try{
			channel.writeAndFlush(ProtocolUtil.encode(server));
		}catch(Exception e){
			log.error("推送数据时出现异常：[ vkServer = "+server.toString() +" ]");
			throw new BizException(4);
		}
	}
	
}