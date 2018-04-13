package com.winterframework.logistics.device.dto;

public class GoogleLocationGeometry {
	private GoogleLocation location;
	private String location_type;
	private GoogleLocationViewPort viewport;
	public GoogleLocation getLocation() {
		return location;
	}
	public void setLocation(GoogleLocation location) {
		this.location = location;
	}

	public String getLocation_type() {
		return location_type;
	}
	public void setLocation_type(String location_type) {
		this.location_type = location_type;
	}
	public GoogleLocationViewPort getViewport() {
		return viewport;
	}
	public void setViewport(GoogleLocationViewPort viewport) {
		this.viewport = viewport;
	}
	
}
