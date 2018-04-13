package com.winterframework.logistics.app.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.winterframework.logistics.app.dto.LoginAppRequest;
import com.winterframework.logistics.app.dto.LoginAppResponse;
import com.winterframework.logistics.app.dto.LogoutAppRequest;
import com.winterframework.logistics.app.service.ILoginService;
import com.winterframework.logistics.base.aop.annotation.RequestValidate;
import com.winterframework.logistics.base.model.Request;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.base.utils.IPUtil;
import com.winterframework.logistics.common.entity.Company;
import com.winterframework.logistics.common.entity.User;
import com.winterframework.logistics.dto.LoginResponse;

@Controller("loginController")
@RequestMapping(value = "/user")
public class LoginController {

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@Resource(name = "loginServiceImpl")
	private ILoginService loginService;

	@RequestMapping(value = "/login")
	@ResponseBody
	public Response<LoginAppResponse> login(@RequestBody @RequestValidate Request<LoginAppRequest> request,HttpServletRequest httpRequest)
			throws Exception {
		Response<LoginAppResponse> response = new Response<LoginAppResponse>();
		LoginAppRequest bizReq=request.getData();
		LoginResponse res=loginService.login(bizReq.getUserName(), bizReq.getPasswd(), IPUtil.getClientIp(httpRequest));
		LoginAppResponse bizRes=new LoginAppResponse();
		bizRes.setToken(res.getToken());	//7days
		bizRes.setUserId(res.getUserId());
		bizRes.setHeadImg(res.getHeadImg());
		bizRes.setNickName(res.getNickName());
		bizRes.setPhoneNumber(res.getPhoneNumber());
		bizRes.setType(res.getType());
		bizRes.setCompanyId(res.getCompanyId());
		if(res.getType().intValue()!=0){
			Company companyRes= loginService.getCompanyName(res.getCompanyId());
			bizRes.setCompanyName(companyRes.getName());
		}
		response.setData(bizRes);
		return response;
	}
	@RequestMapping(value = "/logout")
	@ResponseBody
	public Response<String> logout(@RequestBody @RequestValidate Request<LogoutAppRequest> request)
			throws Exception {
		Response<String> response = new Response<String>();
		LogoutAppRequest bizReq=request.getData();
		loginService.logout(bizReq.getUserId());
		
		return response;
	}
	
}
