package com.winterframework.logistics.web.transport.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.type.TypeReference;
import com.winterframework.logistics.base.http.IHttpClient;
import com.winterframework.logistics.base.http.RequestContext;
import com.winterframework.logistics.base.model.Request;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.common.entity.City;
import com.winterframework.logistics.common.entity.Company;
import com.winterframework.logistics.common.entity.CompanyCity;
import com.winterframework.logistics.common.enums.HttpStatusCode;
import com.winterframework.logistics.web.service.ICompanyCityWebService;
import com.winterframework.logistics.web.service.ICompanyWebService;
import com.winterframework.logistics.web.service.IUserWebService;
import com.winterframework.logistics.web.transport.controller.dto.CityListForCompany;
import com.winterframework.modules.spring.exetend.PropertyConfig;

@Controller("companyController")
@RequestMapping("/user/company")
public class CompanyController {
	@Resource(name = "httpClientImpl")
	private IHttpClient iHttpClient;

	@Resource(name = "companyWebServiceImpl")
	private ICompanyWebService companyWebService;

	@Resource(name = "companyCityWebServiceImpl")
	private ICompanyCityWebService companyCityWebService;

	@PropertyConfig(value = "url.transport.connect")
	private String transportUrl;

	@PropertyConfig(value = "server.url.system")
	private String systemUrl;

	@Resource(name = "userWebServiceImpl")
	private IUserWebService userWebService;

	@PropertyConfig(value = "logistics.system.user.queryUser")
	private String queryUser;

	@PropertyConfig(value = "logistics.user.company.findCompanyIdByName")
	private String findCompanyIdByNameUrl;

	@PropertyConfig(value = "logistics.user.company.findCompanyCityById")
	private String findCompanyCityByIdUrl;

	@PropertyConfig(value = "logistics.user.company.findCityListById")
	private String findCityListByIdUrl;

	@PropertyConfig(value = "logistics.system.city.insertCitys")
	private String insertCitysrUrl;

	@PropertyConfig(value = "logistics.system.company.findByAttributeList")
	private String findByattributeListUrl;

	@PropertyConfig(value = "logistics.system.companyCity.removeCitys")
	private String removeCitys;

	@PropertyConfig(value = "logistics.system.companyCity.insertEntitys")
	private String insertCompanyCityUrl;

	/**
	 * 城市列表展示
	 * 
	 * @param request
	 *            城市名称
	 * @return 城市信息列表
	 */
	@RequestMapping("/cityList")
	public @ResponseBody Response<List<String>> findUserSupportCompany(
			@RequestBody Request<Map<String, String>> request) {
		Long companyId = userWebService.findCompanyIdByUser(systemUrl, queryUser, RequestContext.getUserId());
		Response<List<String>> response = new Response<List<String>>();
		response.setMessage(HttpStatusCode.SUCCESS.getMessage());
		response.setStatus(HttpStatusCode.SUCCESS.getCode());
		List<String> cityNameList = new ArrayList<String>();
		Response<List<City>> cResponse = null;
		List<CompanyCity> companyCities = companyCityWebService.getCompanyCityListByCompanys(transportUrl,
				findCompanyCityByIdUrl, companyId);
		// 查找城市信息
		if (companyCities != null) {
			List<Long> idList = new ArrayList<Long>();
			for (CompanyCity companyCity : companyCities) {
				idList.add(companyCity.getCityId());
			}
			try {
				cResponse = iHttpClient.invokeHttp(transportUrl + findCityListByIdUrl, idList,
						new TypeReference<Response<List<City>>>() {
						});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 对查找的结果进行处理
		if (cResponse != null && cResponse.getData() != null) {
			List<City> cityList = cResponse.getData();
			for (City city : cityList) {
				cityNameList.add(city.getName());
			}
		}
		response.setData(cityNameList);
		return response;
	}

	/**
	 * 设置城市列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/setCityList")
	public @ResponseBody Response<String> setCityList(@RequestBody Request<CityListForCompany> request) {
		Response<String> response = new Response<String>();
		CityListForCompany cityListForCompany = request.getData();
		Response<List<Long>> insertCitysResponse = null;
		// 插入城市数据
		try {
			insertCitysResponse = iHttpClient.invokeHttp(systemUrl + insertCitysrUrl, cityListForCompany.getCityList(),
					new TypeReference<Response<List<Long>>>() {
					});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 删除cityCompany中所有用户
		if (cityListForCompany != null && cityListForCompany.getCompanyId() != null) {
			try {
				iHttpClient.invokeHttp(systemUrl + removeCitys, cityListForCompany.getCompanyId(),
						new TypeReference<Response<Integer>>() {
						});
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (insertCitysResponse != null && insertCitysResponse.getData() != null) {
			List<CompanyCity> companyCities = new ArrayList<CompanyCity>();
			for (Long cityId : insertCitysResponse.getData()) {
				CompanyCity companyCity = new CompanyCity();
				companyCity.setCityId(cityId);
				companyCity.setCreatorId(0L);
				companyCity.setCompanyId(cityListForCompany.getCompanyId());
				companyCity.setCreateTime(System.currentTimeMillis());
				companyCities.add(companyCity);
			}
			try {
				response = iHttpClient.invokeHttp(systemUrl + insertCompanyCityUrl, companyCities,
						new TypeReference<Response<String>>() {
						});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return response;
	}

	@RequestMapping("/companyList")
	public @ResponseBody Response<List<Company>> showCompanyList(@RequestBody Request<Object> request) {
		Response<List<Company>> response = new Response<List<Company>>();
		Company company = new Company();

		try {
			response = iHttpClient.invokeHttp(systemUrl + findByattributeListUrl, company,
					new TypeReference<Response<List<Company>>>() {
					});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;

	}
}
