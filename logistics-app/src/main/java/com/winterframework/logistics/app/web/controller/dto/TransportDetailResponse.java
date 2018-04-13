package com.winterframework.logistics.app.web.controller.dto;

import java.io.Serializable;

import com.winterframework.logistics.common.entity.Address;

public class TransportDetailResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3369680955132397320L;
	private String containerId;
	private String ladingId;
	private String deviceId;
	private Long createTime;
	private Long completeTime;
	private Integer deviceStatus;
	private String frequencyType;
	private Integer locateFrequency;
	private Address startPosition;
	private Address endPosition;
	private Integer transportStatus;
	private Integer transportType;

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

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Integer getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(Integer deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	public String getFrequencyType() {
		return frequencyType;
	}

	public void setFrequencyType(String frequencyType) {
		this.frequencyType = frequencyType;
	}

	public Integer getLocateFrequency() {
		return locateFrequency;
	}

	public void setLocateFrequency(Integer locateFrequency) {
		this.locateFrequency = locateFrequency;
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

	public Integer getTransportStatus() {
		return transportStatus;
	}

	public void setTransportStatus(Integer transportStatus) {
		this.transportStatus = transportStatus;
	}

	public Integer getTransportType() {
		return transportType;
	}

	public void setTransportType(Integer transportType) {
		this.transportType = transportType;
	}

	public Long getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Long completeTime) {
		this.completeTime = completeTime;
	}
	

}
