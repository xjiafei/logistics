package com.winterframework.logistics.device.server;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.winterframework.logistics.base.model.Context;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.device.server.protocol.VkDevice;
import com.winterframework.logistics.device.server.protocol.VkServer;
public abstract class AbstractHandler{
	protected Logger log = LoggerFactory.getLogger(getClass()); 
	
	@Resource(name = "handlerAdditionMap")
	protected Map<String,AbstractAdditionHandler> handlerAdditionMap;
	
	/*@Resource(name="httpUtil")
	protected HttpUtil httpUtil;
	*/
	public VkServer handle(Context ctx,VkDevice vkDevice) throws LmException{ 
		VkServer res=null;
		preHandle(ctx,vkDevice);
		res=doHandle(ctx,vkDevice);
		afterHandle(ctx,vkDevice);
		return res;
	}
	
	protected void preHandle(Context ctx,VkDevice vkDevice) throws LmException{
	} 
	
	protected VkServer doHandle(Context ctx,VkDevice vkDevice) throws LmException{
		if(!vkDevice.isReply()){
			String data=vkDevice.getData();
			String[] d=data.split("&");
			for(String s:d){
				if(StringUtils.isNotEmpty(s)){
					String additionType=s.substring(0,1);
					String additionData=s.substring(1);
					AbstractAdditionHandler additionHandler=handlerAdditionMap.get(additionType);
					if(additionHandler!=null){
						additionHandler.handle(ctx, additionData);
					}
				}
			}
			VkServer server=new VkServer(vkDevice);
			server.setIsReply(true);
			return server;
		}
		return null;
	}
	 
	protected void afterHandle(Context ctx,VkDevice vkDevice) throws LmException{
	}
	
	protected VkServer getVkResponse(VkServer res,Response<?> bizRes){
		if(null!=bizRes){			
		}else{
			
		}
		return res;
	}	
}
