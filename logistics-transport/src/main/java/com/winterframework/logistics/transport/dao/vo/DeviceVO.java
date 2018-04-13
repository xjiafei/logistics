package com.winterframework.logistics.transport.dao.vo;

import com.winterframework.orm.dal.ibatis3.BaseEntity;

public class DeviceVO extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4784868138509688642L;

    private String number;

    private String model;

    private Integer battery;

    private Integer onff;

    private Long onffTime;

    private String location;

    private Integer status;

    private String remark;

    private Long creatorId;

    private Long createTime;

    private Long updatorId;

    private Long updateTime;


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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdatorId() {
        return updatorId;
    }

    public void setUpdatorId(Long updatorId) {
        this.updatorId = updatorId;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}