package com.winterframework.logistics.device.service.scheduler.location;

public class GaoDeLocationStruc {

	private String type;
	private String location;
	private String radius;
	private String desc;
	private String country;
	private String province;
	private String city;
	private String citycode;
	private String adcode;
	private String road;
	private String street;
	private String poi;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getRadius() {
		return radius;
	}
	public void setRadius(String radius) {
		this.radius = radius;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCitycode() {
		return citycode;
	}
	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}
	public String getAdcode() {
		return adcode;
	}
	public void setAdcode(String adcode) {
		this.adcode = adcode;
	}
	public String getRoad() {
		return road;
	}
	public void setRoad(String road) {
		this.road = road;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getPoi() {
		return poi;
	}
	public void setPoi(String poi) {
		this.poi = poi;
	}
	public String toString(){
		return "GaoDeLocationStruc:type="+type+",location="+location+",radius="+radius
				+",desc="+desc+",country="+country+",province="+province+",city="+city
				+",citycode="+citycode+",adcode="+adcode+",road="+road+",street="+street
				+",poi="+poi;
	}
	
}

