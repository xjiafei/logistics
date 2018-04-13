package com.winterframework.logistics.transport.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.winterframework.logistics.base.enums.StatusCode;
import com.winterframework.logistics.base.model.Request;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.common.entity.Device;
import com.winterframework.logistics.common.enums.DeviceStatus;
import com.winterframework.logistics.common.enums.HttpStatusCode;
import com.winterframework.logistics.common.service.IDeviceService;

@Controller("deviceController")
@RequestMapping("/transport/device")
public class DeviceController {
	private static Logger logger = LoggerFactory.getLogger(DeviceController.class);

	@Resource(name = "deviceServiceImpl")
	IDeviceService iDeviceService;

	@RequestMapping("/findByAttribute")
	public @ResponseBody Response<List<Device>> findDeviceListByAttribute(@RequestBody Request<Device> request) {
		logger.info("/transport/device/findByAttribute");
		Response<List<Device>> response = new Response<List<Device>>();
		List<Device> deviceList = null;
		if (request != null && request.getData() != null) {
			deviceList = iDeviceService.findDeviceListByAttribute(request.getData(),null,0);
		}
		response.setData(deviceList);
		return response;
	}

	@RequestMapping("/findById")
	public @ResponseBody Response<Device> findDeviceByAttribute(@RequestBody Request<Device> request) {
		logger.info("/transport/device/findById");
		Response<Device> response = new Response<Device>();
		Device device = null;
		if (request != null && request.getData() != null) {
			device = iDeviceService.getVO(request.getData());
		}
		response.setData(device);
		return response;

	}

	@RequestMapping("/findDeviceByNumber")
	public @ResponseBody Response<Device> findDeviceByNumber(@RequestBody Request<Device> request) {
		logger.info("/transport/device/findDeviceByNumber");
		Response<Device> response = new Response<Device>();
		Device device = null;
		if (request != null && request.getData() != null) {
			device = iDeviceService.getByNumber(request.getData().getNumber());
		}
		response.setData(device);
		return response;
	}

	@RequestMapping("/findDeviceListByNumber")
	public @ResponseBody Response<List<Device>> findDeviceListByNumber(@RequestBody Request<List<String>> request) {
		logger.info("/transport/device/findDeviceListByNumber");
		Response<List<Device>> deResponse = new Response<List<Device>>();
		List<Device> deviceList = null;
		if (request != null && request.getData() != null) {
			deviceList = iDeviceService.getListByNumbers(request.getData());
		}
		deResponse.setData(deviceList);
		return deResponse;
	}

	@RequestMapping("/updateDevice")
	public @ResponseBody Response<Integer> updateDevice(@RequestBody Request<Device> request) {
		logger.info("/transport/device/updateDevice");
		Response<Integer> response = new Response<Integer>();
		response.setMessage(StatusCode.SUCCESS.getMessage());
		response.setStatus(StatusCode.SUCCESS.getCode());
		int status = 0;
		if (request != null && request.getData() != null) {
			status = iDeviceService.updateDevice(request.getData());
			response.setData(status);
		}
		if (status == 0) {
			response.setStatus(StatusCode.DAO_ERROR.getCode());
			response.setMessage(StatusCode.DAO_ERROR.getMessage());
		}
		
		return response;
	}

	@RequestMapping("/selectCountByAttribute")
	public @ResponseBody Response<Integer> selectCountByAttribute(@RequestBody Request<Device> request) {
		logger.info("/transport/device/selectCountByAttribute");
		Response<Integer> response = new Response<Integer>();
		Device device = null;
		if (request != null && request.getData() != null) {
			device = iDeviceService.getVO(request.getData());
		}
		response.setData(checked(device));
		return response;
	}

	private int checked(Device device) {
		// TODO Auto-generated method stub
		int status = HttpStatusCode.NOTEXISTS.getCode();
		if (device != null) {
			status = DeviceStatus.getKey(device.getStatus());
		}
		return status;
	}

}
