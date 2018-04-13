package com.winterframework.logistics.system.user.controller;

import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.winterframework.logistics.base.model.Request;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.base.utils.JsonUtils;
import com.winterframework.logistics.common.entity.Device;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.common.service.IDeviceService;
import com.winterframework.modules.web.jsonresult.Pager;

@Controller("deviceController")
@RequestMapping("/system/device")
public class DeviceController {
	private static Logger logger = LoggerFactory.getLogger(CompanyController.class);
	@Resource(name = "deviceServiceImpl")
	IDeviceService iDeviceService;

	@RequestMapping("/findByAttribute")
	public @ResponseBody Response<List<Device>> findDeviceListByAttribute(@RequestBody Request<Device> request) throws LmException {
		logger.info("findByAttribute ----request:"+JsonUtils.toJson(request));
		Response<List<Device>> response = new Response<List<Device>>();
		List<Device> deviceList = null;
		int count=0;
		Pager pager=request.getPager();
		count=iDeviceService.getCountByAttribute(request.getData());
		deviceList = iDeviceService.findDeviceListByAttribute(request.getData(),pager,count);
		response.setData(deviceList);
		response.setCount(count);
		return response;
	}
	
	@RequestMapping("/findByNumberCode")
	public @ResponseBody Response<List<Device>> findDeviceListByNumberCode(@RequestBody Request<Device> request) throws LmException{
		logger.info("findByNumberCode ----request:"+JsonUtils.toJson(request));
		Response<List<Device>> response=new Response<List<Device>>();
		List<Device> deviceList = null;
		int count=0;
		Pager pager=request.getPager();
		count=iDeviceService.getCountByNumberCode(request.getData());
		deviceList = iDeviceService.findDeviceListByNumberCode(request.getData(),pager,count);
		response.setData(deviceList);
		response.setCount(count);
		return response;
	}
	
	@RequestMapping("/updateUseStatus")
	public @ResponseBody Response<Integer> updateUseStatus(@RequestBody Request<Device> request) throws LmException {
		logger.info("updateUseStatus ----request:"+JsonUtils.toJson(request));
		Response<Integer> response=new Response<Integer>();
		int status=0;
		if(request!=null) {
			status=iDeviceService.updateDevice(request.getData());
		}
		response.setStatus(status);
		return response;
	}
	
	
}
