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
import com.winterframework.logistics.common.entity.CompanyCity;
import com.winterframework.logistics.common.enums.HttpStatusCode;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.common.service.ICompanyCityService;

@Controller("companyCityController")
@RequestMapping("/system/companyCity")
public class CompanyCityController {
	private static Logger logger = LoggerFactory.getLogger(CompanyCityController.class);
	@Resource(name="companyCityServiceImpl")
	ICompanyCityService companyCityService;
	
	@RequestMapping("/insertEntitys")
	public @ResponseBody Response<String> insertEntitys(@RequestBody Request<List<CompanyCity>> request) throws LmException{
		logger.info("insertEntitys ----request:"+JsonUtils.toJson(request));
		Response<String> response=new Response<String>();
		response.setStatus(HttpStatusCode.SUCCESS.getCode());
		response.setMessage(HttpStatusCode.SUCCESS.getMessage());
		int status=0;
		if(request.getData()!=null) {
			status=companyCityService.insertEntitys(request.getData());
		}
		if(status==0) {
			response.setStatus(HttpStatusCode.FAILED.getCode());
			response.setMessage(HttpStatusCode.FAILED.getMessage());
		}
		return response;
	}
	
	@RequestMapping("/removeCitys")
	public @ResponseBody Response<Integer> removeCitys(@RequestBody Request<Long> request) throws LmException{
		logger.info("removeCitys ----request:"+JsonUtils.toJson(request));
		Response<Integer> response=new Response<Integer>();
		int status=0;
		if(request.getData()!=null) {
			status=companyCityService.removeCitys(request.getData());
		}
		response.setData(status);
		return response;
		
	}
}
