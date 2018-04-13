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
import com.winterframework.logistics.common.entity.City;
import com.winterframework.logistics.common.entity.Company;
import com.winterframework.logistics.common.entity.CompanyCity;
import com.winterframework.logistics.common.service.ICityService;
import com.winterframework.logistics.common.service.ICompanyCityService;
import com.winterframework.logistics.common.service.ICompanyService;

@RequestMapping("/user/company")
@Controller("companyController")
public class CompanyController {
	private static Logger logger = LoggerFactory.getLogger(CompanyController.class);
	@Resource(name = "companyServiceImpl")
	ICompanyService iCompanyService;

	@Resource(name = "cityServiceImpl")
	ICityService iCityService;

	@Resource(name = "companyCityServiceImpl")
	ICompanyCityService iCompanyCityService;

	@RequestMapping("/findCompanyIdByName")
	public @ResponseBody Response<Company> findCompanyIdByName(@RequestBody Request<Company> request) {
		logger.info("/user/company/findCompanyIdByName");
		Response<Company> response = new Response<Company>();
		Company company = null;
		if (request != null && request.getData() != null) {
			company = iCompanyService.findCompanyByAttribute(request.getData());
		}
		response.setData(company);
		return response;
	}

	@RequestMapping("/findCompanyCityById")
	public @ResponseBody Response<List<CompanyCity>> findCompanyCityById(@RequestBody Request<CompanyCity> request) {
		logger.info("/user/company/findCompanyIdByName");
		Response<List<CompanyCity>> response = new Response<List<CompanyCity>>();
		List<CompanyCity> companyCityList = null;
		if (request != null && request.getData() != null) {
			companyCityList = iCompanyCityService.findCompanyCityListByAttribute(request.getData());
		}
		response.setData(companyCityList);
		return response;
	}

	@RequestMapping("/findCityById")
	public @ResponseBody Response<City> findCityById(@RequestBody Request<City> request) {
		logger.info("/user/company/findCompanyIdByName");
		Response<City> response = new Response<City>();
		City city = null;
		if (request != null && request.getData() != null) {
			city = iCityService.findCityByAttribute(request.getData());
		}
		response.setData(city);
		return response;
	}

	@RequestMapping("/findCityListById")
	public @ResponseBody Response<List<City>> findCityListById(@RequestBody Request<List<Long>> request) {
		logger.info("/user/company/findCompanyIdByName");
		Response<List<City>> response = new Response<List<City>>();
		List<City> cityList = null;
		if (request != null && request.getData() != null) {
			cityList = iCityService.findCityListByCityId(request.getData());
		}
		response.setData(cityList);
		return response;
	}
}
