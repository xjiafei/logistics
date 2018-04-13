package com.winterframework.logistics.system.user.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.winterframework.logistics.base.aop.annotation.RequestInnerValidate;
import com.winterframework.logistics.base.enums.StatusCode;
import com.winterframework.logistics.base.http.RequestContext;
import com.winterframework.logistics.base.model.Context;
import com.winterframework.logistics.base.model.Request;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.base.utils.JsonUtils;
import com.winterframework.logistics.common.entity.User;
import com.winterframework.logistics.common.enums.HttpStatusCode;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.common.service.IUserService;
import com.winterframework.logistics.dto.UserListResponse;
import com.winterframework.logistics.dto.UserRequest;
import com.winterframework.logistics.dto.UserResponse;
import com.winterframework.logistics.system.user.convertor.DtoConvertor;
import com.winterframework.logistics.system.user.convertor.VoConvertor;
import com.winterframework.logistics.vo.UserVO;
import com.winterframework.modules.web.jsonresult.Pager;

@Controller("userController")
@RequestMapping("/system/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Resource(name="userServiceImpl")
	IUserService userService;
	
	@RequestMapping(value = "/add")
	@ResponseBody
	public Response<UserListResponse> add(@RequestBody @RequestInnerValidate Request<UserRequest> request)
			throws Exception {
		logger.info("add ----request:"+JsonUtils.toJson(request));
		Response<UserListResponse> response = new Response<UserListResponse>();
		User user=DtoConvertor.toUser(request.getData());
		if(user==null){
			throw new LmException(StatusCode.PARAM_INVALID);
		}
		user.setType(User.Type.GENERAL.getValue());
		userService.save(new Context(RequestContext.getUserId()), user);
		UserListResponse bizRes=new UserListResponse();
		response.setData(bizRes);
		return response;
	}
	
	@RequestMapping(value = "/modify")
	@ResponseBody
	public Response<UserListResponse> modify(@RequestBody @RequestInnerValidate Request<UserRequest> request)
			throws Exception {
		logger.info("modify ----request:"+JsonUtils.toJson(request));
		Response<UserListResponse> response = new Response<UserListResponse>();
		
		User user=DtoConvertor.toUser(request.getData());
		if(user==null){
			throw new LmException(StatusCode.PARAM_INVALID);
		}
		userService.save(new Context(RequestContext.getUserId()), user);
		UserListResponse bizRes=new UserListResponse();
		response.setData(bizRes);
		return response;
	}
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Response<UserListResponse> delete(@RequestBody @RequestInnerValidate Request<UserRequest> request)
			throws Exception {
		logger.info("delete ----request:"+JsonUtils.toJson(request));
		Response<UserListResponse> response = new Response<UserListResponse>();
		
		User user=DtoConvertor.toUser(request.getData());
		if(user==null){
			throw new LmException(StatusCode.PARAM_INVALID);
		}
		userService.remove(new Context(RequestContext.getUserId()), user.getId());
		UserListResponse bizRes=new UserListResponse();
		response.setData(bizRes);
		return response;
	}
	@RequestMapping(value = "/query")
	@ResponseBody
	public Response<UserListResponse> query(@RequestBody @RequestInnerValidate Request<UserRequest> request)
			throws Exception {
		logger.info("query ----request:"+JsonUtils.toJson(request));
		Response<UserListResponse> response = new Response<UserListResponse>();
		
		User user=DtoConvertor.toUser(request.getData());
		if(user==null){
			throw new LmException(StatusCode.PARAM_INVALID);
		}
		List<User> userList=userService.selectListByAttribute(new Context(RequestContext.getUserId()),user);
		if(userList!=null){
			List<UserVO> userVoList=new ArrayList<UserVO>();
			for(User u:userList){
				userVoList.add(VoConvertor.toUserVo(u));
			}
			UserListResponse bizRes=new UserListResponse();
			bizRes.setUserList(userVoList);
			response.setData(bizRes);
		}
		return response;
	}
	@RequestMapping("/queryById")
	public @ResponseBody Response<UserResponse> queryById(@RequestBody @RequestInnerValidate Request<Long> request) throws Exception{
		logger.info("queryById ----request:"+JsonUtils.toJson(request));
		Response<UserResponse> response=new Response<UserResponse>();
		UserResponse bizRes=new UserResponse();
		response.setData(bizRes);
		User user=userService.get(request.getData());
		if(user!=null) {
			bizRes.setId(user.getId());
			bizRes.setUserName(user.getUserName());
			bizRes.setNickName(user.getNickName());
			bizRes.setPasswd(user.getPasswd());
			bizRes.setHeadImg(user.getHeadImg());
			bizRes.setPhoneNumber(user.getPhoneNumber());
			bizRes.setType(user.getType());
			bizRes.setCompanyId(user.getCompanyId());
			bizRes.setOnline(user.getOnline());
			bizRes.setStatus(user.getStatus());
			bizRes.setRemark(user.getRemark());
			bizRes.setCreatorId(user.getCreatorId());
			bizRes.setCreateTime(user.getCreateTime());
			bizRes.setUpdatorId(user.getUpdatorId());
			bizRes.setUpdateTime(user.getUpdateTime());
		}
		return response;
	}
	/**
	 * 查询用户列表
	 * @param request
	 * @return
	 * @throws LmException 
	 */
	@RequestMapping(value=" /queryList")
	public @ResponseBody Response<List<User>> queryList(@RequestBody Request<String> request) throws LmException{
		logger.info("queryList ----request:"+JsonUtils.toJson(request));
		Response<List<User>> response=new Response<List<User>>();
		User u=new User();
		u.setUserName(request.getData());
		Pager pager=request.getPager();
		int count=0;
		count=userService.getCountByAttribute(u);
		List<User> users=userService.getList(u,pager,count);
		response.setData(users);
		response.setCount(count);
		return response;
	}
	
	/**
	 * 增加用户
	 * @param request
	 * @return
	 * @throws LmException 
	 */
	@RequestMapping(value="/addUser")
	public @ResponseBody Response<Integer> addUser(@RequestBody Request<User> request) throws LmException{
		logger.info("addUser ----request:"+JsonUtils.toJson(request));
		Response<Integer> response=new Response<Integer>();
		User u=request.getData();
		int status=0;
		if(checkIsExist(u)) {
			status=userService.addUser(request.getData());
		}else {
			response.setMessage(HttpStatusCode.USEREXIST.getMessage());
			response.setStatus(HttpStatusCode.USEREXIST.getCode());
		}
		response.setData(status);
		return response;
		
	}
	
	private boolean checkIsExist(User u) throws LmException {
		// TODO Auto-generated method stub
		User user=null;
		if(u!=null) {
			user=userService.findByAccount(u.getUserName().trim());
		}
		return user==null? true:false;
	}

	/**
	 * 删除用户
	 * @param request
	 * @return
	 * @throws LmException 
	 */
	@RequestMapping(value="/deleteUser")
	public @ResponseBody Response<Integer> deleteUser(@RequestBody Request<Long> request) throws LmException{
		logger.info("deleteUser ----request:"+JsonUtils.toJson(request));
		Response<Integer> response=new Response<Integer>();
		int status=0;
		if(request.getData()!=null) {
			status=userService.delete(request.getData());
		}
		response.setData(status);
		return response;
	}
	
	/**
	 * 更新用户密码
	 * @param request
	 * @return
	 * @throws LmException 
	 */
	@RequestMapping(value="/updatePassword")
	public @ResponseBody Response<Integer> updatePassword(@RequestBody Request<User> request) throws LmException{
		logger.info("updatePassword ----request:"+JsonUtils.toJson(request));
		Response<Integer> response=new Response<Integer>();
		int status=0;
		if(request.getData()!=null) {
			status=userService.updateByAttribute(request.getData());
		}
		response.setData(status);
		return response;
	}
	
	@RequestMapping(value="/queryUser")
	public @ResponseBody Response<User> queryUser(@RequestBody Request<User> request) throws LmException{
		logger.info("queryUser ----request:"+JsonUtils.toJson(request));
		Response<User> response=new Response<User>();
		User u=null;
		if(request!=null) {
			u=userService.getEntityByAttribute(request.getData());
		}
		response.setData(u);
		return response;
		
	}
}
