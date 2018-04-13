package com.winterframework.logistics.device.trdparty.vo;

public class Track {
	private long id;	//轨迹Id
	private String ComTime;	//定位时间
	private double Lat;	//GPS纬度
	private double Lng;	//GPS经度
	private double BLat;	//百度纬度
	private double BLng;	//百度经度
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getComTime() {
		return ComTime;
	}
	public void setComTime(String comTime) {
		ComTime = comTime;
	}
	public double getLat() {
		return Lat;
	}
	public void setLat(double lat) {
		Lat = lat;
	}
	public double getLng() {
		return Lng;
	}
	public void setLng(double lng) {
		Lng = lng;
	}
	public double getBLat() {
		return BLat;
	}
	public void setBLat(double bLat) {
		BLat = bLat;
	}
	public double getBLng() {
		return BLng;
	}
	public void setBLng(double bLng) {
		BLng = bLng;
	}
	
	

}
