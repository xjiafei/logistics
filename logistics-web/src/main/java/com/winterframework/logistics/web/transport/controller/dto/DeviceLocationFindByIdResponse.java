package com.winterframework.logistics.web.transport.controller.dto;

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
	private int transportStatus;
	private Address startPosition;
	private Address endPosition;
	private String deviceNumber;
	private Long startTime;
	private List<Location> tracks;
	private int deviceOnff;
	private int transportType;
	public String getContainerId() {
		return containerId;
	}

	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}

	public int getTransportStatus() {
		return transportStatus;
	}

	public void setTransportStatus(int transportStatus) {
		this.transportStatus = transportStatus;
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

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public List<Location> getTracks() {
		return tracks;
	}

	public void setTracks(List<Location> tracks) {
		this.tracks = tracks;
	}

	public int getDeviceOnff() {
		return deviceOnff;
	}

	public void setDeviceOnff(int deviceOnff) {
		this.deviceOnff = deviceOnff;
	}

	public int getTransportType() {
		return transportType;
	}

	public void setTransportType(int transportType) {
		this.transportType = transportType;
	}

	

}
