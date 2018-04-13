package com.winterframework.logistics.app.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.core.type.TypeReference;
import com.winterframework.logistics.app.service.IUserAppService;
import com.winterframework.logistics.base.http.IHttpClient;
import com.winterframework.logistics.base.http.RequestContext;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.common.entity.City;
import com.winterframework.logistics.common.entity.Company;
import com.winterframework.logistics.common.entity.CompanyCity;
import com.winterframework.logistics.common.enums.HttpStatusCode;
import com.winterframework.modules.spring.exetend.PropertyConfig;

@Controller("companyController")
@RequestMapping("/user/company")
public class CompanyController {
	@Resource(name = "httpClientImpl")
	private IHttpClient iHttpClient;
	
	@Resource(name="UserAppServiceImpl")
	IUserAppService userAppService;
	
	@PropertyConfig(value = "url.transport.connect")
	private String url;
	
	@PropertyConfig(value = "server.url.system")
	private String systemUrl;
	
	@PropertyConfig(value="logistics.system.user.queryUser")
	private String queryUser;
	
	@PropertyConfig(value = "logistics.user.company.findCompanyIdByName")
	private String findCompanyIdByNameUrl;

	@PropertyConfig(value = "logistics.user.company.findCompanyCityById")
	private String findCompanyCityByIdUrl;

	@PropertyConfig(value = "logistics.user.company.findCityListById")
	private String findCityListByIdUrl;

	/**
	 * 公司支持的城市列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/cityList")
	public @ResponseBody Response<Object> findUserSupportCompany(@RequestBody Response<Map<String, String>> request) {
		Long companyId=userAppService.findCompanyIdByUser(systemUrl,queryUser,RequestContext.getUserId());
		Response<Object> response = new Response<Object>();
		response.setMessage(HttpStatusCode.SUCCESS.getMessage());
		response.setStatus(HttpStatusCode.SUCCESS.getCode());
		List<String> cityNameList = new ArrayList<String>();
		Response<List<CompanyCity>> companyCityListResponse = null;
		Response<List<City>> cResponse = null;
		CompanyCity companyCity = new CompanyCity();
		companyCity.setCompanyId(companyId);
			try {
				companyCityListResponse = iHttpClient.invokeHttp(url + findCompanyCityByIdUrl, companyCity,
						new TypeReference<Response<List<CompanyCity>>>() {
						});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		// 查找城市信息
		if (companyCityListResponse != null && companyCityListResponse.getData() != null) {
			List<Long> idList = new ArrayList<Long>();
			for (CompanyCity companyCitys : companyCityListResponse.getData()) {
				idList.add(companyCitys.getCityId());
			}

			try {
				cResponse = iHttpClient.invokeHttp(url + findCityListByIdUrl, idList,
						new TypeReference<Response<List<City>>>() {
						});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 对查找的信息进行处理
		if (cResponse != null && cResponse.getData() != null) {
			List<City> cityList = cResponse.getData();
			for (City city : cityList) {
				cityNameList.add(city.getName());
			}
		}
		response.setData(cityNameList == null ? new JSONArray() : cityNameList);
		return response;
	}
}
