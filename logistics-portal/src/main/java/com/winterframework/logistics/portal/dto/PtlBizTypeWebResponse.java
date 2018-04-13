package com.winterframework.logistics.portal.dto;

public class PtlBizTypeWebResponse {
	private Long id;
	private Long BizId;
	private String number;
	private String name;
	private Integer status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getBizId() {
		return BizId;
	}
	public void setBizId(Long bizId) {
		BizId = bizId;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
