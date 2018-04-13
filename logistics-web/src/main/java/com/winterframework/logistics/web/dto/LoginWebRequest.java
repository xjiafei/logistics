package com.winterframework.logistics.web.dto;

import javax.validation.constraints.NotNull;

public class LoginWebRequest {
	@NotNull
	private String userName;
	@NotNull
	private String passwd;
	private Long kk;
	private Integer kk2;
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
	public Long getKk() {
		return kk;
	}
	public void setKk(Long kk) {
		this.kk = kk;
	}
	public Integer getKk2() {
		return kk2;
	}
	public void setKk2(Integer kk2) {
		this.kk2 = kk2;
	}
	

	
}
