package com.winterframework.logistics.common.entity;

import com.winterframework.logistics.base.entity.ExtBaseEntity;

/**
 * 设备基站信息
 * 
 */
public class DeviceLocationBts extends ExtBaseEntity {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1916094089287734078L;
	private String imei; // 设备IMEI
	private String mcc; // 移动用户所属国家代号
	private String mnc; // 移动网号码
	private String lac1;	//位置区码
	private String ci1;	//移动基站
	private String rssi1;	//信号强度
	private String lac2;	//位置区码
	private String ci2;	//移动基站
	private String rssi2;	//信号强度
	private String lac3;	//位置区码
	private String ci3;	//移动基站
	private String rssi3;	//信号强度
	private String lac4;	//位置区码
	private String ci4;	//移动基站
	private String rssi4;	//信号强度
	private String lac5;	//位置区码
	private String ci5;	//移动基站
	private String rssi5;	//信号强度
	private Long time; // 时间
	private Integer status;
	private Integer handleFlag;// 是否已经解析

	
	public enum Status{
		//0.不可用 1.可用 
    	NO(0),YES(1); 
		private int _value=1;
		Status(int value){
			this._value=value;
		}
		public int getValue(){
			return this._value;
		}
	}
	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getMcc() {
		return mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	public String getMnc() {
		return mnc;
	}

	public void setMnc(String mnc) {
		this.mnc = mnc;
	}

	public String getLac1() {
		return lac1;
	}

	public void setLac1(String lac1) {
		this.lac1 = lac1;
	}

	public String getCi1() {
		return ci1;
	}

	public void setCi1(String ci1) {
		this.ci1 = ci1;
	}

	public String getRssi1() {
		return rssi1;
	}

	public void setRssi1(String rssi1) {
		this.rssi1 = rssi1;
	}

	public String getLac2() {
		return lac2;
	}

	public void setLac2(String lac2) {
		this.lac2 = lac2;
	}

	public String getCi2() {
		return ci2;
	}

	public void setCi2(String ci2) {
		this.ci2 = ci2;
	}

	public String getRssi2() {
		return rssi2;
	}

	public void setRssi2(String rssi2) {
		this.rssi2 = rssi2;
	}

	public String getLac3() {
		return lac3;
	}

	public void setLac3(String lac3) {
		this.lac3 = lac3;
	}

	public String getCi3() {
		return ci3;
	}

	public void setCi3(String ci3) {
		this.ci3 = ci3;
	}

	public String getRssi3() {
		return rssi3;
	}

	public void setRssi3(String rssi3) {
		this.rssi3 = rssi3;
	}

	public String getLac4() {
		return lac4;
	}

	public void setLac4(String lac4) {
		this.lac4 = lac4;
	}

	public String getCi4() {
		return ci4;
	}

	public void setCi4(String ci4) {
		this.ci4 = ci4;
	}

	public String getRssi4() {
		return rssi4;
	}

	public void setRssi4(String rssi4) {
		this.rssi4 = rssi4;
	}

	public String getLac5() {
		return lac5;
	}

	public void setLac5(String lac5) {
		this.lac5 = lac5;
	}

	public String getCi5() {
		return ci5;
	}

	public void setCi5(String ci5) {
		this.ci5 = ci5;
	}

	public String getRssi5() {
		return rssi5;
	}

	public void setRssi5(String rssi5) {
		this.rssi5 = rssi5;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getHandleFlag() {
		return handleFlag;
	}

	public void setHandleFlag(Integer handleFlag) {
		this.handleFlag = handleFlag;
	}

}
