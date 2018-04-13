package com.winterframework.logistics.app.web.controller.dto;

import java.io.Serializable;

import com.winterframework.logistics.base.utils.JsonUtils;
import com.winterframework.logistics.common.entity.Address;

public class TransportOrderResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8964371433286653573L;
	private Long id;
	private String containerId;
	private Address startPosition;
	private Address endPosition;
	private Integer transportStatus;
	private Integer transportType;
	private Long createTime;
	private String newAddress;

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

	public Address getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(String origin) {
		this.startPosition = JsonUtils.fromJson(origin, Address.class);
	}

	public Address getEndPosition() {
		return endPosition;
	}

	public void setEndPosition(String destination) {
		this.endPosition = JsonUtils.fromJson(destination, Address.class);
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

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getNewAddress() {
		return newAddress;
	}

	public void setNewAddress(String newAddress) {
		this.newAddress = newAddress;
	}

}
