package com.winterframework.logistics.system.user.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.winterframework.logistics.base.aop.annotation.RequestInnerValidate;
import com.winterframework.logistics.base.model.Request;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.base.token.JwtUtil;
import com.winterframework.logistics.common.entity.User;
import com.winterframework.logistics.dto.LoginRequest;
import com.winterframework.logistics.dto.LoginResponse;
import com.winterframework.logistics.system.user.service.ILoginService;
import com.winterframework.modules.spring.exetend.PropertyConfig;

@Controller("loginController")
@RequestMapping(value = "/system/user")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Resource(name = "loginServiceImpl")
	private ILoginService loginService;
	@PropertyConfig(value = "security.token.secret")
	private String secret;

	@RequestMapping(value = "/login")
	@ResponseBody
	public Response<LoginResponse> login(@RequestBody @RequestInnerValidate Request<LoginRequest> request)
			throws Exception {
		Response<LoginResponse> response = new Response<LoginResponse>();
		LoginRequest bizReq=request.getData();
		User user=loginService.login(bizReq.getUserName(), bizReq.getPasswd(), bizReq.getLoginIp());
		LoginResponse bizRes=new LoginResponse();
		bizRes.setToken(JwtUtil.generate(user.getId()+"", user.getId()+"", 1000*365*24*60*60*1000, secret));	//1000years
		bizRes.setUserId(user.getId());
		bizRes.setHeadImg(user.getHeadImg());
		bizRes.setNickName(user.getNickName());
		bizRes.setPhoneNumber(user.getPhoneNumber());
		bizRes.setType(user.getType());
		bizRes.setCompanyId(user.getCompanyId());
		response.setData(bizRes);
		return response;
	}
	@RequestMapping(value = "/logout")
	@ResponseBody
	public Response<String> logout(@RequestBody @RequestInnerValidate Request<Long> request)
			throws Exception {
		Response<String> response = new Response<String>();
		Long userId=request.getData();
		loginService.logout(userId);
		return response;
	}
}
