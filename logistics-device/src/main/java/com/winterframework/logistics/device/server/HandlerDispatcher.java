/**   
* @Title: EventDispatcher.java 
* @Package com.winterframework.firefrog.event.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author 你的名字   
* @date 2014-5-8 上午11:34:32 
* @version V1.0   
*/
package com.winterframework.logistics.device.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winterframework.logistics.base.enums.YesNo;
import com.winterframework.logistics.base.exception.BizException;
import com.winterframework.logistics.base.model.Context;
import com.winterframework.logistics.base.utils.DateUtils;
import com.winterframework.logistics.base.utils.JsonUtils;
import com.winterframework.logistics.common.entity.Device;
import com.winterframework.logistics.common.enums.StatusCode;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.common.service.IDeviceService;
import com.winterframework.logistics.device.server.protocol.VkDevice;
import com.winterframework.logistics.device.server.protocol.VkServer;
 
@Service("handlerDispatcher")
@Transactional(rollbackFor = Exception.class)
public class HandlerDispatcher implements IHandlerDispatcher {
	private static final Logger log = LoggerFactory.getLogger(ServerHandler.class);
	
	@Resource(name = "handlerFunctionMap")
	private Map<String,AbstractHandler> handlerFunctionMap;
	@Resource(name = "deviceServiceImpl")
	private IDeviceService deviceService;
	
	@Override
	public VkServer dispatch(Context ctx,VkDevice vkDevice) throws LmException{
		if(vkDevice!=null){
			log.info("HandlerDispatcher.dispatch ,req is not null ");
			AbstractHandler handler = getHandler(vkDevice.getFnType()+vkDevice.getFnKey());
			if(handler!=null){ 
				log.info("The handler is "+handler.getClass());
				return doDispatch(ctx,handler,vkDevice);
			}else{ 
				if(vkDevice.getReply()==YesNo.YES.getValue()){
					VkServer vkServer=new VkServer(vkDevice);
					vkServer.setIsReply(true);
					return vkServer;
				}
				log.error("No handler to handle this request. fnType+fnKey="+vkDevice.getFnType()+vkDevice.getFnKey()+" VkRequest："+vkDevice);
			}
		}else{
			log.info("HandlerDispatcher.dispatch ,req == null ");
		}
		return null;
	}
	private VkServer doDispatch(Context ctx,AbstractHandler handler,VkDevice vkDevice) throws LmException{ 
		return handler.handle(ctx,vkDevice);
	}
	protected AbstractHandler getHandler(String command){
		return handlerFunctionMap.get(command);
	}
	@Override
	public void connect(String terminalId) throws LmException {
		String imei=terminalId;
		Device device=deviceService.getByNumber(imei);
		if(device==null||device.getStatus().intValue()==YesNo.NO.getValue()){
			log.info("HandlerDispatcher.dispatch ,device is invalid. imei="+imei);
			throw new LmException(StatusCode.IMEI_INVALID);
		}
		//更新设备在线状态
		if(device.getOnff().intValue()!=Device.ONFF.ONLINE.getValue()){
			device.setOnff(Device.ONFF.ONLINE.getValue());
			device.setOnffTime(DateUtils.getCurTime());
			try {
				deviceService.save(new Context(-1L), device);
			} catch (BizException e) {
				log.error("device login:save onff failed.imei="+imei);
				//忽略.... 等待下次更新
			}
		}
	}
	@Override
	public void disconnect(String terminalId) throws LmException {
		String imei=terminalId;
		Device device=deviceService.getByNumber(imei);
		if(device==null||device.getStatus().intValue()==YesNo.NO.getValue()){
			log.info("HandlerDispatcher.dispatch ,device is invalid. imei="+imei);
			throw new LmException(StatusCode.IMEI_INVALID);
		}
		//更新设备在线状态--进入休眠
		if(device.getOnff().intValue()!=Device.ONFF.SLEEP.getValue()){
			device.setOnff(Device.ONFF.ONLINE.getValue());
			device.setOnffTime(DateUtils.getCurTime());
			try {
				deviceService.save(new Context(-1L), device);
			} catch (BizException e) {
				log.error("device login:save onff failed.imei="+imei);
				//忽略.... 等待下次更新
			}
		}
	}
	
	public static void main(String[] args){
		List<Map<String,String>> l=new ArrayList<Map<String,String>>();
		Map<String,String> m=new HashMap<String,String>();
		m.put("aa", "111");
		l.add(m);
		String s=JsonUtils.toJson(m); 
		/*List<Map> l2 = jmapper.fromJson(s,
				jmapper.createCollectionType(ArrayList.class, Map.class));*/
		Map<String,String> mm=JsonUtils.fromJson(s, Map.class);
		//System.out.println(l2.get(0).get("aa"));
		System.out.println(mm.get("aa"));
	}

}
