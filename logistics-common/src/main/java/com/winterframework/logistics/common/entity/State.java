package com.winterframework.logistics.common.entity;

import java.util.List;
import java.util.Map;

public class State {
	private Long stateId;
	private List<Map<String,Long>> city;
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public List<Map<String, Long>> getCity() {
		return city;
	}
	public void setCity(List<Map<String, Long>> city) {
		this.city = city;
	}
}
