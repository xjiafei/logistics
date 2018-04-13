package com.winterframework.logistics.app.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.winterframework.logistics.app.service.IUserAppService;
import com.winterframework.logistics.base.http.IHttpClient;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.common.entity.User;
@Service("UserAppServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class UserAppServiceImpl implements IUserAppService{
	@Resource(name = "httpClientImpl")
	private IHttpClient iHttpClient;

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
