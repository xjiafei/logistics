package com.winterframework.logistics.web.transport.controller.dto;

import java.io.Serializable;

public class TransportIdResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8775618889480339460L;
	private Long id;
	private String containerId;
	private String ladingId;
	private String deviceId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContainerId() {
		return containerId;
	}

	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}

	public String getLadingId() {
		return ladingId;
	}

	public void setLadingId(String ladingId) {
		this.ladingId = ladingId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

}
