package com.winterframework.logistics.portal.dto;

import javax.validation.constraints.NotNull;

public class LoginPortalRequest {
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
