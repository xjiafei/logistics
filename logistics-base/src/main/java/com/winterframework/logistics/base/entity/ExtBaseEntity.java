package com.winterframework.logistics.base.entity;



import com.winterframework.orm.dal.ibatis3.BaseEntity;

@SuppressWarnings("serial")
public class ExtBaseEntity extends BaseEntity {
	public static final String ALIAS_REMARK = "备注";
	public static final String ALIAS_CREATE_ID = "创建人";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_UPDATE_ID = "更新人";
	public static final String ALIAS_UPDATE_TIME = "更新时间";
	protected String remark;
	protected Long creatorId;
	protected Long createTime;
	protected Long updatorId;
	protected Long updateTime;
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
