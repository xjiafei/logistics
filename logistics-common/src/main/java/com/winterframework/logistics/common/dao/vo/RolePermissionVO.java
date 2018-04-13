package com.winterframework.logistics.common.dao.vo;

import com.winterframework.logistics.base.entity.ExtBaseEntity;

public class RolePermissionVO extends ExtBaseEntity{


    /**
	 * 
	 */
	private static final long serialVersionUID = -1878063561301468102L;

	private Long roleId;

    private Long permissionId;

    private String remark;

    private Long creatorId;

    private Long updatorId;



    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
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