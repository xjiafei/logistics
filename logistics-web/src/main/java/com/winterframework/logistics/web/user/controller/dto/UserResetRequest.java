package com.winterframework.logistics.web.user.controller.dto;

import javax.validation.constraints.NotNull;

public class UserResetRequest {
	@NotNull
	private Long userId;
	@NotNull
	private String password;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
