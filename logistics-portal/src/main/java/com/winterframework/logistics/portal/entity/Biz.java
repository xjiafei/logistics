package com.winterframework.logistics.portal.entity;

import com.winterframework.logistics.base.entity.ExtBaseEntity;

public class Biz extends ExtBaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6755546481191127848L;

	private Long id;

    private Long bizTypeId;

    private String bizIntro;

    private Integer status;

    private String remark;

    private Long creatorId;

    private Long createTime;

    private Long updatorId;

    private Long updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBizTypeId() {
        return bizTypeId;
    }

    public void setBizTypeId(Long bizTypeId) {
        this.bizTypeId = bizTypeId;
    }

    public String getBizIntro() {
        return bizIntro;
    }

    public void setBizIntro(String bizIntro) {
        this.bizIntro = bizIntro == null ? null : bizIntro.trim();
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