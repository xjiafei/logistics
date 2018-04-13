package com.winterframework.logistics.device.dto;

public class GoogleLocationViewPort {
	private GoogleLocation northeast;
	private GoogleLocation southwest;
	public GoogleLocation getNortheast() {
		return northeast;
	}
	public void setNortheast(GoogleLocation northeast) {
		this.northeast = northeast;
	}
	public GoogleLocation getSouthwest() {
		return southwest;
	}
	public void setSouthwest(GoogleLocation southwest) {
		this.southwest = southwest;
	}
	
}
