package com.winterframework.logistics.app.service;

import com.winterframework.logistics.app.exception.AppException;
import com.winterframework.logistics.common.entity.Company;
import com.winterframework.logistics.dto.LoginResponse;

public interface ILoginService{
	public LoginResponse login(String userName,String passwd,String loginIp) throws AppException;
	public void logout(Long userId) throws AppException;
	public Company getCompanyName(Long companyId) throws AppException ;
}

