package com.winterframework.logistics.app.dto;

import javax.validation.constraints.NotNull;

public class LoginAppRequest {
	@NotNull
	private String userName;
	@NotNull
	private String passwd;
	
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
	

	
}
