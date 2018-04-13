package com.winterframework.logistics.system.user.service;

import com.winterframework.logistics.common.entity.User;
import com.winterframework.logistics.common.exception.LmException;

public interface ILoginService{
	User login(String userName,String passwd,String loginIp) throws LmException;
	void logout(Long userId) throws LmException;

}
