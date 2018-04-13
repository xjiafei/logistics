package com.winterframework.logistics.web.user.controller.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class UserInsertRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5336391894889879724L;
	@NotNull
	private String account;
	@NotNull
	private Long companyId;
	@NotNull
	private String password;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	

}
