package com.winterframework.logistics.web.transport.controller.dto;

import java.io.Serializable;

import com.winterframework.logistics.common.entity.Address;

public class TransportSearchRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7016328134333441343L;
	private String startDate;
	private String endDate;
	private Address startPosition;
	private Address endPosition;
	private int transportType;
	private int transportStatus;
	private int page;
	private int pageCount;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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

	public int getTransportType() {
		return transportType;
	}

	public void setTransportType(int transportType) {
		this.transportType = transportType;
	}

	public int getTransportStatus() {
		return transportStatus;
	}

	public void setTransportStatus(int transportStatus) {
		this.transportStatus = transportStatus;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

}
