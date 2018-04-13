package com.winterframework.logistics.web.transport.controller.dto;

import java.util.List;


public class CityListForCompany {
	private Long companyId;
	private List<String> cityList;
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public List<String> getCityList() {
		return cityList;
	}
	public void setCityList(List<String> cityList) {
		this.cityList = cityList;
	}
	
}
