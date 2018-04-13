package com.winterframework.logistics.app.web.controller.dto;

import java.io.Serializable;

import com.winterframework.logistics.common.entity.Address;

public class TransportOrderRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4526157603905680209L;
	private String containerId;
	private String ladingId;
	private String deviceId;
	private Integer transportType;
	private Address startPosition;
	private Address endPosition;
	private Integer locateFrequency;
	private String frequencyType;

	public String getLadingId() {
		return ladingId;
	}

	public String getContainerId() {
		return containerId;
	}

	public void setContainerId(String containerId) {
		this.containerId = containerId;
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

	public Integer getTransportType() {
		return transportType;
	}

	public void setTransportType(Integer transportType) {
		this.transportType = transportType;
	}

	public Address getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(Address startPosition) {
		this.startPosition = startPosition;
	}

	public Address getEndPosition() {
		return endPosition;
	}

	public void setEndPosition(Address endPosition) {
		this.endPosition = endPosition;
	}

	public Integer getLocateFrequency() {
		return locateFrequency;
	}

	public void setLocateFrequency(Integer locateFrequency) {
		this.locateFrequency = locateFrequency;
	}

	public String getFrequencyType() {
		return frequencyType;
	}

	public void setFrequencyType(String frequencyType) {
		this.frequencyType = frequencyType;
	}
	

}
