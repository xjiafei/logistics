package com.winterframework.logistics.device.server.protocol;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

public class ServerReqDecoder extends ReplayingDecoder<Void> { // (1)
	private static final Logger log = LoggerFactory.getLogger(ServerReqDecoder.class); 

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {		
		try {
			if (in.readableBytes() < 9) {
				log.error("ServerReqDecoder ：：：：：：：：： in.readableBytes() < 9");
				return;
			}

			byte[] headb=new byte[5];
			in.readBytes(headb);
			String head=new String(headb);
			/*byte start = in.readByte();
			byte vk1 = in.readByte();
			byte vk2 = in.readByte();
			byte version = in.readByte();*/
			byte reply=in.readByte();
			byte[] imei=new byte[15];
			in.readBytes(imei);
			String terminalId=new String(imei);
			byte comma=in.readByte();
			byte yes=in.readByte();
			boolean isReply=yes=='Y';
				
			byte fnType;
			if(isReply){
				fnType = in.readByte();
			}else{
				fnType=yes;
			}
			byte fnKey = in.readByte();
			String data="";
			if(!isReply){
				//int length=in.readableBytes()-1;
				byte[] datab=new byte[50];
				in.readBytes(datab);
				data=new String(datab);
			}
			byte[] tailb=new byte[1];
			in.readBytes(tailb);
			String tail=new String(tailb);
			
			VkDevice request = new VkDevice();
			request.setReply(reply);
			request.setIsReply(isReply);
			request.setTerminalId(terminalId);
			request.setFnType(new String(new byte[]{fnType}));
			request.setFnKey(new String(new byte[]{fnKey}));
			request.setData(data);
			
			request.setIp(ProtocolUtil.getClientIp(ctx.channel()));
			
			log.debug("=============== 输入参数  ========================");
			log.debug("= head = "+head);
			/*log.debug("= start = "+start);
			log.debug("= vk1 = "+vk1);
			log.debug("= vk2 = "+vk2);
			log.debug("= version = "+version);*/
			log.debug("= reply = "+reply);
			log.debug("= isReply = "+isReply);
			log.debug("= terminalId = "+terminalId);
			log.debug("= comma = "+comma);
			log.debug("= fnType = "+fnType);
			log.debug("= fnKey = "+fnKey);
			log.debug("= data = "+data);
			log.debug("= ip = "+request.getIp());
			log.debug("= tail = "+tail);
			
			log.debug("=============== 输入结束  ========================");
			
			
			out.add(request);
		} catch (Exception e) {
			log.error("ServerReqDecoder",e);
		}
		
		
//		for(Object o:out){
//			System.out.println(o.toString());
//		}
		
	}
	
}