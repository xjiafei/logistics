package com.winterframework.logistics.common.dao.vo;

import com.winterframework.logistics.base.entity.ExtBaseEntity;

public class DeviceVO extends ExtBaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6982171107850590238L;

	private String number;

    private String model;

    private Integer battery;

    private Integer onff;

    private Long onffTime;

    private String location;

    private Integer status;

    private String remark;

    private Long creatorId;

    private Long updatorId;



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

}