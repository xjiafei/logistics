package com.winterframework.logistics.web.dto;

import javax.validation.constraints.NotNull;

public class LogoutWebRequest {
	@NotNull
	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
