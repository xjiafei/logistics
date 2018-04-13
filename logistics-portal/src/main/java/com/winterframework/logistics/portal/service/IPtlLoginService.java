package com.winterframework.logistics.portal.service;

import com.winterframework.logistics.portal.entity.User;
import com.winterframework.logistics.portal.exception.PortalException;

public interface IPtlLoginService{
	public User login(String userName,String passwd,String loginIp) throws PortalException;
	public void logout(Long userId) throws PortalException;
}

