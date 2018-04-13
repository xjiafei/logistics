package com.winterframework.logistics.common.entity;


public class Location {
	private double latitude;
	private double longitude;
	private Long time;
	private String location;
	
	public Location() {
		
	}
	public Location(double latitude,double longitude,Long time) {
		this.latitude=latitude;
		this.longitude=longitude;
		this.time=time;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	

}
