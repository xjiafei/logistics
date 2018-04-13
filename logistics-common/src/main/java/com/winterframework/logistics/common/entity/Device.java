package com.winterframework.logistics.common.entity;


import com.winterframework.logistics.base.entity.ExtBaseEntity;

public class Device extends ExtBaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -317658373171893210L;

	private Long id;

	private String number;

	private String model;

	private Integer battery;

	private Integer onff;

	private Long onffTime;

	private String location;

	private Integer locationFreq;

	private Integer status;

	private String remark;

	private Long creatorId;

	private Long updatorId;
	private Long companyId;

	public enum Model {
		T19(), T128();
		Model() {
		}
	}
	public enum ONFF {
		OFFLINE(0),ONLINE(1), SLEEP(2);
		private int _value;
		ONFF(int value) {
			_value=value;
		}
		public int getValue(){
			return _value;
		}
	}
	public enum Status {
		//0：不可用 1:可用 2:使用中 3：回收中 4:使用完成
		DISABLE(0),ENABLE(1), USING(2), RECYCLING(3),FINISHED(4);
		private int _value;
		Status(int value) {
			_value=value;
		}
		public int getValue(){
			return _value;
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number == null ? null : number.trim();
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model == null ? null : model.trim();
	}

	public Integer getBattery() {
		return battery;
	}

	public void setBattery(Integer battery) {
		this.battery = battery;
	}

	public Integer getOnff() {
		return onff;
	}

	public void setOnff(Integer onff) {
		this.onff = onff;
	}

	public Long getOnffTime() {
		return onffTime;
	}

	public void setOnffTime(Long onffTime) {
		this.onffTime = onffTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location == null ? null : location.trim();
	}

	public Integer getLocationFreq() {
		return locationFreq;
	}

	public void setLocationFreq(Integer locationFreq) {
		this.locationFreq = locationFreq;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public Long getUpdatorId() {
		return updatorId;
	}

	public void setUpdatorId(Long updatorId) {
		this.updatorId = updatorId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

}