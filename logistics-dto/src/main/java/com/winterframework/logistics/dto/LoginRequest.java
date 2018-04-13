package com.winterframework.logistics.dto;

import javax.validation.constraints.NotNull;

public class LoginRequest {
	@NotNull
	private String userName;
	@NotNull
	private String passwd;
	@NotNull
	private String loginIp; 
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	

	
}
