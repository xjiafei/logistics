package com.winterframework.logistics.web.transport.controller.dto;

import java.io.Serializable;
import com.winterframework.logistics.common.entity.Address;

public class UpdateTransportStatusRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1242706376607824993L;
	private Long id;
	private int transportStatus;
	private String deviceNumber;
	private Address recyclePosition;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getTransportStatus() {
		return transportStatus;
	}

	public void setTransportStatus(int transportStatus) {
		this.transportStatus = transportStatus;
	}

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	public Address getRecyclePosition() {
		return recyclePosition;
	}

	public void setRecyclePosition(Address recyclePosition) {
		this.recyclePosition = recyclePosition;
	}

	
}
