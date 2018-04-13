package com.winterframework.logistics.common.dao.vo;

import com.winterframework.logistics.base.entity.ExtBaseEntity;

public class TransOrderVO extends ExtBaseEntity{


    /**
	 * 
	 */
	private static final long serialVersionUID = -1767672969061796255L;

	private String deviceNumber;

    private String carrierNumber;

    private String billNumber;

    private String origin;

    private String destination;

    private Integer locationFreq;
    
    private Integer transMode;
    
    private Integer transStatus;

    private Long startTime;

    private Long finishTime;

    private Integer status;

    private String remark;

    private Long creatorId;

    private Long updatorId;
    
    private String containerId;
    
    public Integer getTransMode() {
		return transMode;
	}

	public void setTransMode(Integer transMode) {
		this.transMode = transMode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber == null ? null : deviceNumber.trim();
    }

    public String getCarrierNumber() {
        return carrierNumber;
    }

    public void setCarrierNumber(String carrierNumber) {
        this.carrierNumber = carrierNumber == null ? null : carrierNumber.trim();
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber == null ? null : billNumber.trim();
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin == null ? null : origin.trim();
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination == null ? null : destination.trim();
    }

    public Integer getLocationFreq() {
        return locationFreq;
    }

    public void setLocationFreq(Integer locationFreq) {
        this.locationFreq = locationFreq;
    }

    public Integer getTransStatus() {
        return transStatus;
    }

    public void setTransStatus(Integer transStatus) {
        this.transStatus = transStatus;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Long finishTime) {
        this.finishTime = finishTime;
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

	public String getContainerId() {
		return containerId;
	}

	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}

}