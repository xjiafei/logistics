package com.winterframework.logistics.web.user.controller;

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
import com.winterframework.logistics.base.model.Request;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.common.entity.Company;
import com.winterframework.logistics.common.entity.User;
import com.winterframework.logistics.common.enums.HttpStatusCode;
import com.winterframework.logistics.common.enums.UserStatus;
import com.winterframework.logistics.common.enums.UserType;
import com.winterframework.logistics.web.service.ICompanyWebService;
import com.winterframework.logistics.web.service.IUserWebService;
import com.winterframework.logistics.web.user.controller.dto.UserInsertRequest;
import com.winterframework.logistics.web.user.controller.dto.UserListResponse;
import com.winterframework.logistics.web.user.controller.dto.UserResetRequest;
import com.winterframework.modules.spring.exetend.PropertyConfig;
import com.winterframework.modules.web.jsonresult.Pager;

@Controller
@RequestMapping("/user/manage")
public class UserController {
	@Resource(name = "httpClientImpl")
	private IHttpClient iHttpClient;
	
	@Resource(name="userWebServiceImpl")
	private IUserWebService userWebService;
	
	@Resource(name="companyWebServiceImpl")
	private ICompanyWebService companyWebService;
	
	@PropertyConfig(value = "logistics.system.user.queryList")
	private String queryListUrl;

	@PropertyConfig(value = "logistics.system.user.addUser")
	private String addUserUrl;

	@PropertyConfig(value = "logistics.system.user.deleteUser")
	private String deleteUserUrl;

	@PropertyConfig(value = "logistics.system.user.updatePassword")
	private String updatePasswordUrl;

	@PropertyConfig(value = "logistics.system.company.findByAttribute")
	private String findCompanyByAttribute;
	
	@PropertyConfig(value="logistics.system.company.findListById")
	private String findCompanysByIds;
	
	@PropertyConfig(value = "server.url.system")
	private String systemUrl;

	/**
	 * 用户列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/userList")
	public @ResponseBody Response<List<UserListResponse>> userList(@RequestBody Request<Map<String, String>> request) {
		Response<List<UserListResponse>> response = new Response<List<UserListResponse>>();
		Map<String, String> accountMap = request.getData();
		String account = accountMap.get("account");
		Pager pager = request.getPager();
		int count = 0;
		Response<List<User>> userResponse =userWebService.getUserList(systemUrl,queryListUrl,account,pager);
		List<UserListResponse> userListResponseList = new ArrayList<UserListResponse>();
		List<Long> companyIds=new ArrayList<Long>();
		for (User u : userResponse.getData()) {
			companyIds.add(u.getCompanyId());
		}
		List<Company> companies=companyWebService.getCompanyByIds(systemUrl,findCompanysByIds,companyIds);
		count = userResponse.getCount();
		for (User u : userResponse.getData()) {
			UserListResponse userListResponse = new UserListResponse();
			userListResponse.setAccount(u.getUserName());
			userListResponse.setUserId(u.getId());
			userListResponse.setCreateTime(u.getCreateTime());
			for (Company company : companies) {
				if(u.getCompanyId()==company.getId()) {
					userListResponse.setCompanyName(company.getName());
				}
			}
			userListResponseList.add(userListResponse);
		}
		response.setCount(count);
		response.setData(userListResponseList);
		return response;
	}

	/**
	 * 新增用户
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/insertUser")
	public @ResponseBody Response<Object> insertUser(@RequestBody Request<UserInsertRequest> request) {
		Response<Object> response = new Response<Object>();
		response.setMessage(HttpStatusCode.SUCCESS.getMessage());
		response.setStatus(HttpStatusCode.SUCCESS.getCode());
		UserInsertRequest userInsertRequest = request.getData();
		User u = new User();
		u.setUserName(userInsertRequest.getAccount());
		u.setPasswd(userInsertRequest.getPassword());
		u.setCompanyId(userInsertRequest.getCompanyId());
		u.setCreateTime(System.currentTimeMillis());
		u.setType(UserType.COMPANY_USER.getValue());
		u.setStatus(UserStatus.USEABLE.getValue());
		u.setCreatorId(423L);
		Response<Integer> uResponse = null;
		if (request != null) {
			try {
				uResponse = iHttpClient.invokeHttp(systemUrl + addUserUrl, u, new TypeReference<Response<Integer>>() {
				});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (uResponse.getData() == null) {
			response.setMessage(HttpStatusCode.FAILED.getMessage());
			response.setStatus(HttpStatusCode.FAILED.getCode());
		} else {
			if (uResponse.getStatus() == HttpStatusCode.USEREXIST.getCode()) {
				response.setMessage(HttpStatusCode.USEREXIST.getMessage());
				response.setStatus(HttpStatusCode.USEREXIST.getCode());
			}
		}
		return response;
	}

	/**
	 * 删除用户
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteUser")
	public @ResponseBody Response<Object> deleteUser(@RequestBody Request<Map<String, Long>> request) {
		Response<Object> response = new Response<Object>();
		response.setMessage(HttpStatusCode.SUCCESS.getMessage());
		response.setStatus(HttpStatusCode.SUCCESS.getCode());
		Response<Integer> uResponse = null;
		if (request.getData() != null) {
			Long userId = request.getData().get("userId");
			if (userId != null) {
				try {
					uResponse = iHttpClient.invokeHttp(systemUrl + deleteUserUrl, userId,
							new TypeReference<Response<Integer>>() {
							});
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if (uResponse.getData() == null || uResponse.getData() == 0) {
			response.setMessage(HttpStatusCode.FAILED.getMessage());
			response.setStatus(HttpStatusCode.FAILED.getCode());
		}
		return response;
	}

	/**
	 * 重置密码
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/resetPassword")
	public @ResponseBody Response<Object> resetPassword(@RequestBody Request<UserResetRequest> request) {
		Response<Object> response = new Response<Object>();
		response.setMessage(HttpStatusCode.SUCCESS.getMessage());
		response.setStatus(HttpStatusCode.SUCCESS.getCode());
		Response<Integer> uResponse = null;
		if (request.getData() != null) {
			User u = new User();
			u.setId(request.getData().getUserId());
			u.setPasswd(request.getData().getPassword());
			try {
				uResponse = iHttpClient.invokeHttp(systemUrl + updatePasswordUrl, u,
						new TypeReference<Response<Integer>>() {
						});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (uResponse.getData() == null || uResponse.getData() == 0) {
			response.setMessage(HttpStatusCode.FAILED.getMessage());
			response.setStatus(HttpStatusCode.FAILED.getCode());
		}
		return response;
	}

}
