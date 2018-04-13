package com.winterframework.logistics.system.user.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winterframework.logistics.base.enums.YesNo;
import com.winterframework.logistics.base.exception.BizException;
import com.winterframework.logistics.base.model.Context;
import com.winterframework.logistics.base.security.Security;
import com.winterframework.logistics.common.entity.User;
import com.winterframework.logistics.common.enums.StatusCode;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.common.service.IUserService;
import com.winterframework.logistics.system.user.service.ILoginService;

/**
 * 用户登录服务
 * @ClassName
 * @Description
 * @author ibm
 * 2017年11月29日
 */
@Service("loginServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class LoginServiceImpl implements ILoginService {
	private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);
	@Resource(name="userServiceImpl")
	private IUserService userService;
	@Resource(name="security")
	private Security security;
	
	@Override
	public User login(String userName, String passwd, String loginIp) throws LmException {
		User user=userService.getByUserName(userName);
		if(user==null || !security.verify(user.getPasswd(), passwd)){
			throw new LmException(StatusCode.USER_INVALID);
		}
		user.setOnline(YesNo.YES.getValue());
		try {
			userService.save(new Context(user.getId()), user);
		} catch (BizException e) {
			log.error("user save online failed when login. userId="+user.getId());
			throw new LmException(e);
		}
		return user;
	}
	@Override
	public void logout(Long userId) throws LmException {
		try {
			User user=userService.get(userId);
			if(user!=null){
				user.setOnline(YesNo.NO.getValue());
				userService.save(new Context(user.getId()), user);
			}
		} catch (BizException e) {
			log.error("user logout failed. userId="+userId);
			throw new LmException(e);
		}
	}
	
}
