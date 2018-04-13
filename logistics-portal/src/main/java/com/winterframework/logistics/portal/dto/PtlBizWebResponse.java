package com.winterframework.logistics.portal.dto;

public class PtlBizWebResponse {
	private Long id;
	private Long bizTypeId;
	private String bizTypeName;
	private String bizTypeNumber;
	private String bizIntro;
	private Long createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBizIntro() {
		return bizIntro;
	}

	public void setBizIntro(String bizIntro) {
		this.bizIntro = bizIntro;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getBizTypeId() {
		return bizTypeId;
	}

	public void setBizTypeId(Long bizTypeId) {
		this.bizTypeId = bizTypeId;
	}

	public String getBizTypeName() {
		return bizTypeName;
	}

	public void setBizTypeName(String bizTypeName) {
		this.bizTypeName = bizTypeName;
	}

	public String getBizTypeNumber() {
		return bizTypeNumber;
	}

	public void setBizTypeNumber(String bizTypeNumber) {
		this.bizTypeNumber = bizTypeNumber;
	}

}
