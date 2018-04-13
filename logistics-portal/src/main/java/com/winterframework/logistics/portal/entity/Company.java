package com.winterframework.logistics.portal.entity;

import com.winterframework.logistics.base.entity.ExtBaseEntity;

public class Company extends ExtBaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2800411109669798549L;

	private Long id;

    private String name;

    private String logo;

    private String intro;

    private String philo;

    private String cooper;

    private String homePics;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    public String getPhilo() {
        return philo;
    }

    public void setPhilo(String philo) {
        this.philo = philo == null ? null : philo.trim();
    }

    public String getCooper() {
        return cooper;
    }

    public void setCooper(String cooper) {
        this.cooper = cooper == null ? null : cooper.trim();
    }

    public String getHomePics() {
        return homePics;
    }

    public void setHomePics(String homePics) {
        this.homePics = homePics == null ? null : homePics.trim();
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