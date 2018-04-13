package com.winterframework.logistics.dto.device;

import javax.validation.constraints.NotNull;

public class DeviceShutdownRequest {
	@NotNull
	private String imei;
	@NotNull
	private Long userId; //用户Id 系统操作则-1
	
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	
	
}
