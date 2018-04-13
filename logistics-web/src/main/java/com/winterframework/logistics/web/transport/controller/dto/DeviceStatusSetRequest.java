package com.winterframework.logistics.web.transport.controller.dto;

import javax.validation.constraints.NotNull;

public class DeviceStatusSetRequest {
	@NotNull
	private Long deviceId;
	@NotNull
	private int usageStatus;
	public Long getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}
	public int getUsageStatus() {
		return usageStatus;
	}
	public void setUsageStatus(int usageStatus) {
		this.usageStatus = usageStatus;
	}
	
}
