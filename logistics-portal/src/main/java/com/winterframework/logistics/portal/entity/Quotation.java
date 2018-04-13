package com.winterframework.logistics.portal.entity;

import java.math.BigDecimal;

import com.winterframework.logistics.base.entity.ExtBaseEntity;

public class Quotation extends ExtBaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5769429555003002292L;

	private Long id;

    private String origin;

    private String originPort;

    private String dest;

    private String destPort;

    private String goodsName;

    private Long eta;

    private Long etd;

    private Integer cartonType;

    private Integer cartonCount;

    private Integer weight;

    private Integer volume;

    private BigDecimal cvalue;

    private String companyName;

    private String cttName;

    private String cttPhone;

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

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin == null ? null : origin.trim();
    }

    public String getOriginPort() {
        return originPort;
    }

    public void setOriginPort(String originPort) {
        this.originPort = originPort == null ? null : originPort.trim();
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest == null ? null : dest.trim();
    }

    public String getDestPort() {
        return destPort;
    }

    public void setDestPort(String destPort) {
        this.destPort = destPort == null ? null : destPort.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public Long getEta() {
        return eta;
    }

    public void setEta(Long eta) {
        this.eta = eta;
    }

    public Long getEtd() {
        return etd;
    }

    public void setEtd(Long etd) {
        this.etd = etd;
    }

    public Integer getCartonType() {
        return cartonType;
    }

    public void setCartonType(Integer cartonType) {
        this.cartonType = cartonType;
    }

    public Integer getCartonCount() {
        return cartonCount;
    }

    public void setCartonCount(Integer cartonCount) {
        this.cartonCount = cartonCount;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public BigDecimal getCvalue() {
        return cvalue;
    }

    public void setCvalue(BigDecimal cvalue) {
        this.cvalue = cvalue;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCttName() {
        return cttName;
    }

    public void setCttName(String cttName) {
        this.cttName = cttName == null ? null : cttName.trim();
    }

    public String getCttPhone() {
        return cttPhone;
    }

    public void setCttPhone(String cttPhone) {
        this.cttPhone = cttPhone == null ? null : cttPhone.trim();
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