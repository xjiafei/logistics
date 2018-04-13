package com.winterframework.logistics.web.user.controller.dto;

import java.io.Serializable;

public class UserListResponse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3589459905072676846L;
	private Long userId;
	private String account;
	private String companyName;
	private Long createTime;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	

}
