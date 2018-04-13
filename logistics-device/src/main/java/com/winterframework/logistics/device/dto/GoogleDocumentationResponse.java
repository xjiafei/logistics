package com.winterframework.logistics.device.dto;

public class GoogleDocumentationResponse {
	private GoogleLocation location;
	private Integer accuracy;
	public GoogleLocation getLocation() {
		return location;
	}
	public Integer getAccuracy() {
		return accuracy;
	}
	public void setLocation(GoogleLocation location) {
		this.location = location;
	}
	public void setAccuracy(Integer accuracy) {
		this.accuracy = accuracy;
	}
	
}
