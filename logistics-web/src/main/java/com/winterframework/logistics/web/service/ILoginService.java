package com.winterframework.logistics.web.service;

import com.winterframework.logistics.common.entity.Company;
import com.winterframework.logistics.dto.LoginResponse;
import com.winterframework.logistics.web.exception.WebException;

public interface ILoginService{
	public LoginResponse login(String userName,String passwd,String loginIp) throws WebException;
	public void logout(Long userId) throws WebException;
	public Company getCompanyName(Long companyId) throws WebException;
}

