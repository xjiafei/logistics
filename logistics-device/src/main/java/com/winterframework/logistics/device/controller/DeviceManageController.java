package com.winterframework.logistics.device.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.winterframework.logistics.base.aop.annotation.RequestInnerValidate;
import com.winterframework.logistics.base.enums.StatusCode;
import com.winterframework.logistics.base.model.Context;
import com.winterframework.logistics.base.model.Request;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.device.service.IDvcDeviceManageService;
import com.winterframework.logistics.dto.device.DeviceLocationFreqSetRequest;
import com.winterframework.logistics.dto.device.DeviceShutdownRequest;
 
@Controller("deviceManageController")
@RequestMapping("/device/manage")
public class DeviceManageController {
	private static final Logger log = LoggerFactory.getLogger(DeviceManageController.class);
	@Resource(name = "dvcDeviceManageServiceImpl")
	private IDvcDeviceManageService dvcDeviceManageService;
	
	@Deprecated
	@RequestMapping("/startup")
	@ResponseBody
	public Object startup(@RequestBody @RequestInnerValidate Request<String> req) throws LmException{
		Response<Map<String,String>> res=new Response<Map<String,String>>();  
		String imei=req.getData();
		//Long userId=req.getData().getUserId();
		Context ctx=new Context(-1L);
		dvcDeviceManageService.startup(ctx,imei);
		res.setStatus(StatusCode.SUCCESS.getCode());
		return res;
	}
	
	@RequestMapping("/shutdown")
	@ResponseBody
	public Object shutdown(@RequestBody @RequestInnerValidate Request<DeviceShutdownRequest> req) throws LmException{
		Response<Map<String,String>> res=new Response<Map<String,String>>();  
		String imei=req.getData().getImei();
		Long userId=req.getData().getUserId();
		Context ctx=new Context(userId);
		dvcDeviceManageService.shutdown(ctx,imei);
		res.setStatus(StatusCode.SUCCESS.getCode());
		return res;
	}
	
	@RequestMapping("/locationFreq")
	@ResponseBody
	public Object locationFreq(@RequestBody @RequestInnerValidate Request<DeviceLocationFreqSetRequest> req) throws LmException{
		Response<Map<String,String>> res=new Response<Map<String,String>>();  
		String imei=req.getData().getImei();
		Integer freq=req.getData().getFreq();
		Long userId=req.getData().getUserId();
		
		Context ctx=new Context(userId);
		dvcDeviceManageService.locationFreqSett(ctx,imei, freq);
		res.setStatus(StatusCode.SUCCESS.getCode());
		return res;
	}
	
}
