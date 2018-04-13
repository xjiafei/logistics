package com.winterframework.logistics.system.user.controller;

import java.util.ArrayList;
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
import com.winterframework.logistics.common.entity.DeviceChangeStatus;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.common.service.IDeviceChangeStatusService;
import com.winterframework.modules.web.jsonresult.Pager;

@Controller("deviceChangeStatusController")
@RequestMapping("/system/deviceManage")
public class DeviceChangeStatusController {
	private static Logger logger = LoggerFactory.getLogger(DeviceChangeStatusController.class);
	@Resource(name = "deviceChangeStatusServiceImpl")
	private IDeviceChangeStatusService deviceChangeStatusService;
	
	@RequestMapping("/findList")
	public @ResponseBody Response<List<DeviceChangeStatus>> findListByAttribute(@RequestBody Request<DeviceChangeStatus> request) throws LmException{
		logger.info("findList ----request:"+JsonUtils.toJson(request));
		Response<List<DeviceChangeStatus>> response=new Response<List<DeviceChangeStatus>>();
		List<DeviceChangeStatus> deList=new ArrayList<DeviceChangeStatus>();
		int count=0;
		Pager pager=request.getPager();
		count=deviceChangeStatusService.getCountByattribute(request.getData());
		deList=deviceChangeStatusService.findListByAttribute(request.getData(),pager,count);
		response.setData(deList);
		response.setCount(count);
		return response;
	}
	
	@RequestMapping("/insert")
	public @ResponseBody Response<Integer> insert(@RequestBody Request<DeviceChangeStatus> request) throws LmException{
		logger.info("insert ----request:"+JsonUtils.toJson(request));
		Response<Integer> response=new Response<Integer>();
		int status=0;
		if(request!=null) {
			status=deviceChangeStatusService.insert(request.getData());
		}
		response.setData(status);
		return response;
		
	}
}
