package com.winterframework.logistics.common.dao.vo;

import com.winterframework.logistics.base.entity.ExtBaseEntity;

public class UserRoleVO extends ExtBaseEntity{


    /**
	 * 
	 */
	private static final long serialVersionUID = -5377914393340488599L;

	private Long userId;

    private Long roleId;

    private String remark;

    private Long creatorId;

    private Long updatorId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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