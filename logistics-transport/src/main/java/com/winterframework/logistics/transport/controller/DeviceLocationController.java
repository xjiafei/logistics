package com.winterframework.logistics.transport.controller;

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
import com.winterframework.logistics.common.entity.DeviceLocation;
import com.winterframework.logistics.common.service.IDeviceLocationService;

@Controller("deviceLocationController")
@RequestMapping("/transport/deviceLocation")
public class DeviceLocationController {
	private static Logger logger = LoggerFactory.getLogger(DeviceLocationController.class);

	@Resource(name = "deviceLocationServiceImpl")
	IDeviceLocationService deviceLocationService;

	/**
	 * 查询轨迹
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/findByOrderId")
	public @ResponseBody Response<List<DeviceLocation>> findDeviceLocations(@RequestBody Request<Long> request) {
		logger.info("/deviceLocation/findByOrderId");
		Response<List<DeviceLocation>> response = new Response<List<DeviceLocation>>();
		List<DeviceLocation> deviceLocationList = null;
		if (request != null && request.getData() != null) {
			deviceLocationList = deviceLocationService.findByOrderId(request.getData());
		}
		response.setData(deviceLocationList);
		return response;
	}
	@RequestMapping("/findByAttribute")
	public @ResponseBody Response<List<DeviceLocation>> findDeviceLocation(@RequestBody Request<List<Long>> request) {
		logger.info("/deviceLocation/findByAttribute");
		Response<List<DeviceLocation>> response = new Response<List<DeviceLocation>>();
		List<DeviceLocation> deviceLocationList = null;
		if (request != null && request.getData() != null) {
			deviceLocationList = deviceLocationService.findByAttribute(request.getData());
		}
		response.setData(deviceLocationList);
		return response;
	}
	
	@RequestMapping("/findNewDeviceLocation")
	public @ResponseBody Response<DeviceLocation> findNewDeviceLocation(@RequestBody Request<Long> request){
		logger.info("/deviceLocation/findNewDeviceLocation");
		Response<DeviceLocation> response=new Response<DeviceLocation>();
		DeviceLocation deviceLocation=new DeviceLocation();
		if(request!=null && request.getData() !=null) {
			deviceLocation=deviceLocationService.findNewDeviceLocationById(request.getData());
		}
		response.setData(deviceLocation);
		return response;
	}
	
	@RequestMapping("/findNewLocationList")
	public @ResponseBody Response<List<DeviceLocation>> findNewDeviceLocations(@RequestBody Request<List<Long>> request){
		logger.info("/deviceLocation/findNewLocationList");
		Response<List<DeviceLocation>> response=new Response<List<DeviceLocation>>();
		List<DeviceLocation> deviceLocations=null;
		if(request!=null && request.getData() !=null) {
			deviceLocations=deviceLocationService.findNewDeviceLocations(request.getData());
		}
		response.setData(deviceLocations);
		return response;
		
	}
}
