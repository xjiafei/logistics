package com.winterframework.logistics.common.entity;


import com.winterframework.logistics.base.entity.ExtBaseEntity;

public class Permission extends ExtBaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4074997900141032307L;
	private Long id;
	  private String code;

	    private String name;

	    private Long pid;

	    private Integer type;

	    private Integer seq;

	    private Integer status;

	    private String remark;

	    private Long creatorId;

	    private Long updatorId;


	    public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}


		public String getCode() {
	        return code;
	    }

	    public void setCode(String code) {
	        this.code = code == null ? null : code.trim();
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name == null ? null : name.trim();
	    }

	    public Long getPid() {
	        return pid;
	    }

	    public void setPid(Long pid) {
	        this.pid = pid;
	    }

	    public Integer getType() {
	        return type;
	    }

	    public void setType(Integer type) {
	        this.type = type;
	    }

	    public Integer getSeq() {
	        return seq;
	    }

	    public void setSeq(Integer seq) {
	        this.seq = seq;
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