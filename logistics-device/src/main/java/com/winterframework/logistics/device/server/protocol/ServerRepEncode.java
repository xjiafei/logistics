package com.winterframework.logistics.device.server.protocol;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ServerRepEncode extends MessageToByteEncoder<VkServer> {
	private static final Logger log = LoggerFactory.getLogger(ServerRepEncode.class); 
	private final String head="*VK20";
	private final String tail="#";
	
	@Override
	protected void encode(ChannelHandlerContext ctx, VkServer msg, ByteBuf out) throws Exception {
		try {
			int fixLen=9;
			int length=fixLen+msg.getData().length()+(msg.isReply()?1:0);
			ByteBuffer headBuffer = ByteBuffer.allocate(length);
			/**
			 * 先组织报文头
			 */
			headBuffer.put(head.getBytes());
			if(msg.isReply()){
				headBuffer.put((byte)'Y');
			}else{
				headBuffer.put((msg.getSave()+"").getBytes()[0]);
				headBuffer.put((msg.getReply()+"").getBytes()[0]);	
			}
			headBuffer.put(msg.getFnType().getBytes()[0]);
			headBuffer.put(msg.getFnKey().getBytes()[0]);
			if(!msg.isReply()){
				headBuffer.put(msg.getData().getBytes());
			}
			headBuffer.put(tail.getBytes());
			
			/**
			 * 组织报文的数据部分
			 */
			ByteBuf totalBuffer =null;
			try{
				/**
				 * 非常重要
				 * ByteBuffer需要手动flip()，ChannelBuffer不需要
				 */
				headBuffer.flip();
				totalBuffer = ctx.alloc().buffer();
				totalBuffer.writeBytes(headBuffer);

				totalBuffer.writeBytes(Delimiters.getDelimiter());
				out.writeBytes(totalBuffer);
			
				log.debug("*************** 输出参数  ************************");
				log.debug("= head = ");
				log.debug("= save = "+msg.getSave());
				log.debug("= reply = "+msg.getReply());
				log.debug("= isReply = "+msg.isReply());
				log.debug("= fnType = "+msg.getFnType());
				log.debug("= fnKey = "+msg.getFnKey());
				log.debug("= data = "+msg.getData());
				log.debug("= tail = ");
				
				log.debug("*************** 输出结束 *************************");
				msg=null;
			}finally{
				if(totalBuffer!=null){
					totalBuffer.release();
					totalBuffer=null;
				}
			}
		} catch (Exception e) {
			log.error("ServerRepEncode",e);
			throw e;
		}
	
	}

}