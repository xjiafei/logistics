package com.winterframework.logistics.app.dto;

import javax.validation.constraints.NotNull;

public class LogoutAppRequest {
	@NotNull
	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
