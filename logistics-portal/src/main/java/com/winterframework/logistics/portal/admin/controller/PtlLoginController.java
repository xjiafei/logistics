package com.winterframework.logistics.portal.admin.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.winterframework.logistics.base.aop.annotation.RequestValidate;
import com.winterframework.logistics.base.http.RequestContext;
import com.winterframework.logistics.base.model.Request;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.base.token.JwtUtil;
import com.winterframework.logistics.base.utils.IPUtil;
import com.winterframework.logistics.portal.dto.LoginPortalRequest;
import com.winterframework.logistics.portal.dto.LoginPortalResponse;
import com.winterframework.logistics.portal.entity.User;
import com.winterframework.logistics.portal.service.IPtlLoginService;
import com.winterframework.modules.spring.exetend.PropertyConfig;

@Controller("ptlLoginController")
@RequestMapping(value = "/user")
public class PtlLoginController {
	private static final Logger log = LoggerFactory.getLogger(PtlLoginController.class);

	@Resource(name = "ptlLoginServiceImpl")
	private IPtlLoginService ptlLoginService;
	@PropertyConfig(value = "security.token.secret")
	private String secret;
	
	@RequestMapping(value = "/login")
	@ResponseBody
	public Response<LoginPortalResponse> login(@RequestBody @RequestValidate Request<LoginPortalRequest> request,HttpServletRequest httpRequest)
			throws Exception {
		Response<LoginPortalResponse> response = new Response<LoginPortalResponse>();
		LoginPortalRequest bizReq=request.getData();
		User user=ptlLoginService.login(bizReq.getUserName(), bizReq.getPasswd(), IPUtil.getClientIp(httpRequest));
		
		LoginPortalResponse bizRes=new LoginPortalResponse();
		bizRes.setUserId(user.getId());
		bizRes.setPhoneNumber(user.getPhoneNumber());
		bizRes.setType(user.getType());
		bizRes.setToken(JwtUtil.generate(user.getId()+"", user.getId()+"", 7*24*60*60*1000, secret));	//7days);
		response.setData(bizRes);
		return response;
	}
	@RequestMapping(value = "/logout")
	@ResponseBody
	public Response<String> logout(@RequestBody @RequestValidate Request<String> request)
			throws Exception {
		Response<String> response = new Response<String>();
		ptlLoginService.logout(RequestContext.getUserId());
		return response;
	}
	
}

