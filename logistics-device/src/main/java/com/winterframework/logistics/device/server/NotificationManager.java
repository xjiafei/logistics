package com.winterframework.logistics.device.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.winterframework.logistics.base.exception.BizException;
import com.winterframework.logistics.device.server.protocol.ProtocolUtil;
import com.winterframework.logistics.device.server.protocol.VkServer;

import io.netty.channel.Channel;

public class NotificationManager{
	private static final Logger log= LoggerFactory.getLogger(NotificationManager.class);
    
	/**
	 * @param terminalId
	 * @param server
	 * @return command(具体指令内容)
	 * @throws ServerException
	 */
	public static String push(String terminalId,VkServer server) throws ServerException {
		log.info("terminalId="+terminalId+","+server.toString()); 
		Channel channel=ChannelManager.get(terminalId);  
		if(null==channel){
			log.error("用户未登录，通道为空：terminalId = "+terminalId);
			return "";
			//throw new ServerException(StatusCode.UN_CONNECT.getValue());
		}
		try{
			String command=ProtocolUtil.encode(server);
			channel.writeAndFlush(command);
			return command;
		}catch(Exception e){
			log.error("推送数据时出现异常：[ vkServer = "+server.toString() +" ]");
			throw new ServerException(new BizException(4));
		}
	}    
}
