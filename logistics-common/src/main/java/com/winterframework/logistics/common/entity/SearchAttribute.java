package com.winterframework.logistics.common.entity;

import java.io.Serializable;
import java.util.List;

public class SearchAttribute implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3748740141395505770L;
	private Long startDate;
	private Long endDate;
	private Integer transportStatus;
	private List<Integer> transportType;
	private Integer pageCount;
	private Integer page;
	private String startPosition;
	private String endPosition;
	private Long companyId;
	
	public Long getStartDate() {
		return startDate;
	}
	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}
	public Long getEndDate() {
		return endDate;
	}
	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}
	public Integer getTransportStatus() {
		return transportStatus;
	}
	public void setTransportStatus(Integer transportStatus) {
		this.transportStatus = transportStatus;
	}
	public List<Integer> getTransportType() {
		return transportType;
	}
	public void setTransportType(List<Integer> arrayList) {
		this.transportType = arrayList;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public String getStartPosition() {
		return startPosition;
	}
	public void setStartPosition(String startPosition) {
		this.startPosition = startPosition;
	}
	public String getEndPosition() {
		return endPosition;
	}
	public void setEndPosition(String endPosition) {
		this.endPosition = endPosition;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	
	
}
