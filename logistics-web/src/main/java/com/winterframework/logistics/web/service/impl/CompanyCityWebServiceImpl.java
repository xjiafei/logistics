package com.winterframework.logistics.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.winterframework.logistics.base.http.IHttpClient;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.common.entity.CompanyCity;
import com.winterframework.logistics.web.service.ICompanyCityWebService;
@Service("companyCityWebServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class CompanyCityWebServiceImpl implements ICompanyCityWebService{
	@Resource(name = "httpClientImpl")
	private IHttpClient iHttpClient;

	@Override
	public List<CompanyCity> getCompanyCityListByCompanys(String transportUrl, String findCompanyCityByIdUrl, Long companyId) {
		// TODO Auto-generated method stub
		Response<List<CompanyCity>> response=new Response<List<CompanyCity>>();
		CompanyCity companyCity=new CompanyCity();
		companyCity.setCompanyId(companyId);
		try {
			response=iHttpClient.invokeHttp(transportUrl + findCompanyCityByIdUrl, companyCity,
					new TypeReference<Response<List<CompanyCity>>>() {
					});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(response!=null && response.getData()!=null) {
			return response.getData();
		}
		return null;
	}
}
