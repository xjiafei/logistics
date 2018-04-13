package com.winterframework.logistics.common.dao.vo;

import java.math.BigDecimal;

import com.winterframework.logistics.base.entity.ExtBaseEntity;

public class DeviceLocationVO extends ExtBaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5333005398484586627L;

	private Long deviceId;

    private String billNumber;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private Integer radius;

    private Long time;

    private Integer timeStay;

    private String address;

    private Integer status;

    private String remark;

    private Long creatorId;

    private Long updatorId;


    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber == null ? null : billNumber.trim();
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Integer getTimeStay() {
        return timeStay;
    }

    public void setTimeStay(Integer timeStay) {
        this.timeStay = timeStay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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