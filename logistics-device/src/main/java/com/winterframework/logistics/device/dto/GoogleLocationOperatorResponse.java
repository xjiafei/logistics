package com.winterframework.logistics.device.dto;

public class GoogleLocationOperatorResponse {
	private GoogleLocationOperatorResults[] results;
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public GoogleLocationOperatorResults[] getResults() {
		return results;
	}
	public void setResults(GoogleLocationOperatorResults[] results) {
		this.results = results;
	}
	
}
