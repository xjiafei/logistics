package com.winterframework.logistics.portal.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winterframework.logistics.base.enums.YesNo;
import com.winterframework.logistics.base.exception.BizException;
import com.winterframework.logistics.base.model.Context;
import com.winterframework.logistics.base.security.Security;
import com.winterframework.logistics.portal.common.StatusCode;
import com.winterframework.logistics.portal.entity.User;
import com.winterframework.logistics.portal.exception.PortalException;
import com.winterframework.logistics.portal.service.IPtlLoginService;
import com.winterframework.logistics.portal.service.IPtlUserService;

/**
 * 用户登录服务
 * @ClassName
 * @Description
 * @author ibm
 * 2017年11月29日
 */
@Service("ptlLoginServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class PtlLoginServiceImpl implements IPtlLoginService {
	private static final Logger log = LoggerFactory.getLogger(PtlLoginServiceImpl.class);
	@Resource(name="ptlUserServiceImpl")
	private IPtlUserService userService;
	@Resource(name="security")
	private Security security;
	
	@Override
	public User login(String userName, String passwd, String loginIp) throws PortalException {
		User user=userService.getByUserName(userName);
		if(user==null || !security.verify(user.getPasswd(), passwd)){
			throw new PortalException(StatusCode.USER_INVALID);
		}
		user.setOnline(YesNo.YES.getValue());
		try {
			userService.save(new Context(user.getId()), user);
		} catch (BizException e) {
			log.error("user save online failed when login. userId="+user.getId());
			throw new PortalException(e);
		}
		
		return user;
	}
	@Override
	public void logout(Long userId) throws PortalException {
		try {
			User user=userService.get(userId);
			if(user!=null){
				user.setOnline(YesNo.NO.getValue());
				userService.save(new Context(user.getId()), user);
			}
		} catch (BizException e) {
			log.error("user logout failed. userId="+userId);
			throw new PortalException(e);
		}
	}
	
}
