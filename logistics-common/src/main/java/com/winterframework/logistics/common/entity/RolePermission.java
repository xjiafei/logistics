package com.winterframework.logistics.common.entity;


import com.winterframework.logistics.base.entity.ExtBaseEntity;

public class RolePermission extends ExtBaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3998554145378316763L;

	private Long id;

	 private Long roleId;

	    private Long permissionId;

	    private String remark;

	    private Long creatorId;

	    private Long updatorId;



	    public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

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