package com.winterframework.logistics.common.dao.vo;

import com.winterframework.logistics.base.entity.ExtBaseEntity;

public class RoleVO extends ExtBaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 5969451026804345298L;

	private String name;

    private Integer status;

    private String remark;

    private Long creatorId;

    private Long updatorId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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