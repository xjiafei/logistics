package com.winterframework.logistics.device.dto;

public class GoogleLocationOperatorResults {
	private GoogleLocationOperatorAddress[] address_components;
	private String formatted_address;
	private GoogleLocationGeometry geometry;
	private String place_id;
	private String[] types;
	
	
	public GoogleLocationOperatorAddress[] getAddress_components() {
		return address_components;
	}
	public void setAddress_components(GoogleLocationOperatorAddress[] address_components) {
		this.address_components = address_components;
	}
	public String getFormatted_address() {
		return formatted_address;
	}
	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}
	public GoogleLocationGeometry getGeometry() {
		return geometry;
	}
	public void setGeometry(GoogleLocationGeometry geometry) {
		this.geometry = geometry;
	}
	
	public String getPlace_id() {
		return place_id;
	}
	public void setPlace_id(String place_id) {
		this.place_id = place_id;
	}
	public String[] getTypes() {
		return types;
	}
	public void setTypes(String[] types) {
		this.types = types;
	}
	
	
	
}
