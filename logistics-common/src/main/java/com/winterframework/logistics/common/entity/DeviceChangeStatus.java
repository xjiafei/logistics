package com.winterframework.logistics.common.entity;

import com.winterframework.logistics.base.entity.ExtBaseEntity;

public class DeviceChangeStatus extends ExtBaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2544707153768607600L;
	private Long id;
	private Long deviceId;
	private Integer fromStatus;
	private Integer toStatus;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}
	public Integer getFromStatus() {
		return fromStatus;
	}
	public void setFromStatus(Integer fromStatus) {
		this.fromStatus = fromStatus;
	}
	public Integer getToStatus() {
		return toStatus;
	}
	public void setToStatus(Integer toStatus) {
		this.toStatus = toStatus;
	}

	
	

}
