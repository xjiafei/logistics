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
import com.winterframework.logistics.common.entity.Company;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.common.service.ICompanyService;

@Controller("companyController")
@RequestMapping("/system/company")
public class CompanyController {
	private static Logger logger = LoggerFactory.getLogger(CompanyController.class);
	@Resource(name="companyServiceImpl")
	ICompanyService companyService;

	@RequestMapping("/findByAttribute")
	public @ResponseBody Response<Company> findCompanyByAttribute(@RequestBody Request<Company> request) throws LmException{
		logger.info("findByAttribute ----request:"+JsonUtils.toJson(request));
		Response<Company> response=new Response<Company>();
		Company company=null;
		if((company=request.getData())!=null) {
			company=companyService.findCompanyByAttribute(company);
		}
		response.setData(company);
		return response;
	}
	
	@RequestMapping("/findListById")
	public @ResponseBody Response<List<Company>> findCompanyListByIds(@RequestBody Request<List<Long>>request) throws LmException{
		logger.info("findListById ----request:"+JsonUtils.toJson(request));
		Response<List<Company>> response=new Response<List<Company>>();
		List<Company> companyList=null;
		if(request.getData()!=null) {
			companyList=companyService.findCompanyListByIds(request.getData());
		}
		response.setData(companyList);
		return response;	
	}
	
	@RequestMapping("/findByAttributeList")
	public @ResponseBody Response<List<Company>> findCompanyListByAttribute(@RequestBody Request<Company> request) throws LmException{
		logger.info("findByAttributeList ----request:"+JsonUtils.toJson(request));
		Response<List<Company>> response=new Response<List<Company>>();
		List<Company> companys=companyService.findCompanyByAttributes(request.getData());
		if(companys!=null) {
			response.setData(companys);
		}
		return response;
	}
	
	@RequestMapping("/insertByAttribute")
	public @ResponseBody Response<Long> insertByAttribute(@RequestBody Request<Company> request) throws LmException{
		logger.info("insertByAttribute ----request:"+JsonUtils.toJson(request));
		Response<Long> response=new Response<Long>();
		Long companyId=null;
		if(request.getData()!=null) {
			companyId=companyService.addCompany(request.getData());
		}
		response.setData(companyId);
		return response;
	}

}
