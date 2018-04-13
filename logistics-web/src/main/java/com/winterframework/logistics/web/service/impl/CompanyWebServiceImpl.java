package com.winterframework.logistics.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.winterframework.logistics.base.http.IHttpClient;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.common.entity.Company;
import com.winterframework.logistics.web.service.ICompanyWebService;
@Service("companyWebServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class CompanyWebServiceImpl implements ICompanyWebService{
	@Resource(name = "httpClientImpl")
	private IHttpClient iHttpClient;
	
	@Override
	public Company getCompanyByAttribute(String transportUrl, String findCompanyIdByNameUrl, Company company) {
		// TODO Auto-generated method stub
		Response<Company> response=null;
		try {
			response = iHttpClient.invokeHttp(transportUrl + findCompanyIdByNameUrl, company,
					new TypeReference<Response<Company>>() {
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

	@Override
	public List<Company> getCompanyByIds(String systemUrl, String findCompanysByIds, List<Long> companyIds) {
		// TODO Auto-generated method stub
		Response<List<Company>> response=null;
		try {
			response=iHttpClient.invokeHttp(systemUrl + findCompanysByIds, companyIds,
					new TypeReference<Response<List<Company>>>() {
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
