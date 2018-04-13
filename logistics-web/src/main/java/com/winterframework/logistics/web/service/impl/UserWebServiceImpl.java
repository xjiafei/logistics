package com.winterframework.logistics.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.winterframework.logistics.base.http.IHttpClient;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.common.entity.User;
import com.winterframework.logistics.web.service.IUserWebService;
import com.winterframework.modules.web.jsonresult.Pager;
@Service("userWebServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class UserWebServiceImpl implements IUserWebService{
	@Resource(name = "httpClientImpl")
	private IHttpClient iHttpClient;

	@Override
	public Response<List<User>> getUserList(String systemUrl, String queryListUrl, String account, Pager pager) {
		// TODO Auto-generated method stub
		Response<List<User>> response=new Response<List<User>>();
		try {
			response = iHttpClient.invokeHttp(systemUrl + queryListUrl, account, pager,
					new TypeReference<Response<List<User>>>() {
					});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(response!=null && response.getData()!=null) {
			return response;
		}
		return null;
	}

	@Override
	public Long findCompanyIdByUser(String systemUrl, String queryUser, Long userId) {
		// TODO Auto-generated method stub
		Response<User> response=null;
		User u=new User();
		u.setId(userId);
		try {
			response = iHttpClient.invokeHttp(systemUrl + queryUser, u,
					new TypeReference<Response<User>>() {
					});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(response!=null && response.getData()!=null) {
			return response.getData().getCompanyId();
		}
		return null;
	}
	
	
}
