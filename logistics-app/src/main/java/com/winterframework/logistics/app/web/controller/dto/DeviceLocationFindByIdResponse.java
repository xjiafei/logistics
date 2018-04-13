package com.winterframework.logistics.app.web.controller.dto;

import java.io.Serializable;
import java.util.List;

import com.winterframework.logistics.common.entity.Address;
import com.winterframework.logistics.common.entity.Location;

public class DeviceLocationFindByIdResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5836105996394465791L;
	private String containerId;
	private String ladingId;
	private String deviceId;
	private Address startPosition;
	private Address endPosition;
	private Integer transportStatus;
	private Integer transportType;
	private List<Location> tracks;

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

	public List<Location> getTracks() {
		return tracks;
	}

	public void setTracks(List<Location> tracks) {
		this.tracks = tracks;
	}
}
