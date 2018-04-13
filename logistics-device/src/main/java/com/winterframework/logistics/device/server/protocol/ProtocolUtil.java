package com.winterframework.logistics.device.server.protocol;
 
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.winterframework.logistics.base.utils.Base64Util;
import com.winterframework.logistics.base.utils.CharsetFactory;
import com.winterframework.logistics.base.utils.DateUtils;
import com.winterframework.logistics.base.utils.JsonUtils;
import com.winterframework.logistics.base.utils.ResourceUtils;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
 
 
public class ProtocolUtil {
	private static Logger log = LoggerFactory.getLogger(ProtocolUtil.class); 
	private static Properties props=null;
    /**
     * 编码报文的数据部分
     * @param encode
     * @param values
     * @return
     */
    public static ByteBuf encode(int encode,Map<String,String> values,ChannelHandlerContext ctx){
    	ByteBuf totalBuffer=null;
        if (values!=null && values.size()>0) {
        	ByteBuf [] channelBuffers=new ByteBuf[values.size()];
        	try{
	            totalBuffer=ctx.alloc().buffer();
	            int index=0;
	            Charset charset=CharsetFactory.getCharset(encode);
	            for(Entry<String,String> entry:values.entrySet()){
	                String key=entry.getKey();
	                String value= entry.getValue();
	               
	                ByteBuf buffer=null;
	               /* try{*/
	                	buffer=ctx.alloc().buffer();
		                buffer.writeInt(key.getBytes(charset).length);
		                buffer.writeBytes(key.getBytes(charset));
		                
		                if(value==null||value.equals("null")){
		                	value="";
		                } 
		                if(ResourceUtils.isMediaContent(key)){
		                	byte [] contents=Base64Util.decode(value);
		                	buffer.writeInt(contents.length);
			                buffer.writeBytes(contents); 
		                }else{                
			                buffer.writeInt(value.getBytes(charset).length);
			                buffer.writeBytes(value.getBytes(charset));
		                }
		                channelBuffers[index++]=buffer;
	                /*}finally{
	                	if(buffer!=null){
	                		buffer.release();
	                	}
	                }*/
	              }
	             
	            for (int i = 0; i < channelBuffers.length; i++) {
	                totalBuffer.writeBytes(channelBuffers[i]);
	            }
            }catch(Exception e){
            	log.error("totalBuffer release.",e);
            	if(totalBuffer!=null){
            		totalBuffer.release();
            	}
        	}finally{
            	//释放内存
            	for (int i = 0; i < channelBuffers.length; i++) {
            		if(channelBuffers[i]!=null){
            			channelBuffers[i].release();
            			channelBuffers[i]=null;
            		}
	            }
            }
        }
        return totalBuffer;
    }
     
    /**
     * 解码报文的数据部分
     * @param encode
     * @param dataBuffer
     * @return
     */
    public static Map<String,String> decode(int encode,ByteBuf dataBuffer) throws Exception{
    	Map<String,String> dataMap=new HashMap<String, String>();
        if (dataBuffer!=null && dataBuffer.readableBytes()>0) {
            int processIndex=0,length=dataBuffer.readableBytes();
            Charset charset=CharsetFactory.getCharset(encode);
            int size=0;
            byte [] contents=new byte [size];
            try{
	            while(processIndex<length){
	                /**
	                 * 获取Key
	                 */
	                size=dataBuffer.readInt();
	                contents=new byte [size];
	                dataBuffer.readBytes(contents);
	                String key=new String(contents, charset);
	                processIndex+=(size+4);
	                /**
	                 * 获取Value
	                 */
	                size=dataBuffer.readInt();
	                contents=new byte [size];
	                dataBuffer.readBytes(contents); 
	                if(ResourceUtils.isMediaContent(key)){
	                	dataMap.put(key, Base64Util.encodeToString(contents));
	                }else{
	                	dataMap.put(key, new String(contents, charset));
	                } 
	                processIndex+=(size+4);
	            }
            }catch(Exception e){
        		log.error("util decode error:length="+length+" processIndex="+processIndex+" size="+size+" contents="+new String(contents,charset)+" dataMap="+JsonUtils.toJson(dataMap));
        		throw e;
        	}
        }
	    return dataMap;
    }
     
    public static VkDevice decode(String request){
    	//*MG20 1 867282030485128,AB &P0460000027a10dfd&B0000000000 &W00 &G001100 &M997 &N28 &O0000 &Z00 &T0001 #
		String head=request.substring(0,5);
		int reply=Integer.valueOf(request.substring(5,6));
		String terminalId=request.substring(6,21);
		String replyFlag=request.substring(22,23);
		boolean isReply=replyFlag.equals("Y");
		String fnType="";
		String fnKey="";
		String data="";
		if(isReply){
			fnType=request.substring(23,24);
			fnKey=request.substring(24,25);
		}else{
			fnType=replyFlag;
			fnKey=request.substring(23,24);
			data=request.substring(24,request.length()-1);
		}
		String tail=request.substring(request.length()-1,request.length());
		VkDevice vkDevice =new VkDevice();
		vkDevice.setHead(head);
		vkDevice.setReply(reply);
		vkDevice.setIsReply(isReply);
		vkDevice.setTerminalId(terminalId);
		vkDevice.setFnType(fnType);
		vkDevice.setFnKey(fnKey);
		vkDevice.setData(data);
		vkDevice.setTail(tail);
		return vkDevice;
    }
    public static String encode(VkServer vkServer){
    	//*MG20 1 867282030485128,AB &P0460000027a10dfd&B0000000000 &W00 &G001100 &M997 &N28 &O0000 &Z00 &T0001 #
		String head=vkServer.getHead();
		int save=vkServer.getSave();
		int reply=vkServer.getReply();
		boolean isReply=vkServer.isReply();
		String fnType=vkServer.getFnType();
		String fnKey=vkServer.getFnKey();
		String data=vkServer.getData();
		String tail=vkServer.getTail();
		StringBuffer sb=new StringBuffer();
		sb.append(head);
		if(isReply){
			sb.append("Y");
		}else{
			sb.append(save);
			sb.append(reply);
		}
		sb.append(fnType).append(fnKey);
		if(!isReply){
			sb.append(data);
		}
		sb.append(tail);
		return sb.toString();
    }
    /**
     * 获取客户端IP
     * @param channel
     * @return
     */
    public static String getClientIp(Channel channel){
        /**
         * 获取客户端IP
         */
        SocketAddress address = channel.remoteAddress();
        String ip = "";
        if (address != null) {
            ip = address.toString().trim();
            int index = ip.lastIndexOf(':');
            if (index < 1) {
                index = ip.length();
            }
            ip = ip.substring(1, index);
        }
        if (ip.length() > 15) {
            ip = ip.substring(Math.max(ip.indexOf("/") + 1, ip.length() - 15));
        }
        return ip;
    }
    /**
     * 获取客户端句柄
     * @param channel
     * @return
     */
    public static String getClientHandle(Channel channel){
    	return channel.remoteAddress().toString().split(":")[1];
    } 
    public static String getProp(String key){
    	if(null==props){
    		try {
    			props = PropertiesLoaderUtils.loadAllProperties("application.properties");
    			return (String)props.get("imageUrl");
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} 
    	}
    	return "";
    }
    
    public static void main(String[] args){ 
    	String ss="";
    	String ss2=null;
    	 
    	Integer[] lastResult=new Integer[]{1,2,3};
		System.out.println(ArrayUtils.toString(lastResult));
    	System.out.println(DateUtils.addDays(DateUtils.currentDate(), -28000));
    }
}