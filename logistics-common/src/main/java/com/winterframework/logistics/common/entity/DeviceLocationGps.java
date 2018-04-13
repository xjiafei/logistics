package com.winterframework.logistics.common.entity;

import com.winterframework.logistics.base.entity.ExtBaseEntity;




public class DeviceLocationGps extends ExtBaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2785425975808124826L;
	private String imei;
	private double longitude;	//经度
	private double latitude;	//纬度
	private Integer locationFlag;
	private Integer speed;
	private Integer direction;
	private double factor;
	private Long time;
	private Integer status;
	private Integer handleFlag;
	
	public enum Status{
		//0.不可用 1.可用 
    	NO(0),YES(1); 
		private int _value=1;
		Status(int value){
			this._value=value;
		}
		public int getValue(){
			return this._value;
		}
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public Integer getLocationFlag() {
		return locationFlag;
	}
	public void setLocationFlag(Integer locationFlag) {
		this.locationFlag = locationFlag;
	}
	public Integer getSpeed() {
		return speed;
	}
	public void setSpeed(Integer speed) {
		this.speed = speed;
	}
	public Integer getDirection() {
		return direction;
	}
	public void setDirection(Integer direction) {
		this.direction = direction;
	}
	public double getFactor() {
		return factor;
	}
	public void setFactor(double factor) {
		this.factor = factor;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getHandleFlag() {
		return handleFlag;
	}
	public void setHandleFlag(Integer handleFlag) {
		this.handleFlag = handleFlag;
	}
	
	
}

