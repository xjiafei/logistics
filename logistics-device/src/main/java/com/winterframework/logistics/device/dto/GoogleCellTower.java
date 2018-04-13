package com.winterframework.logistics.device.dto;


public class GoogleCellTower {
	private Integer cellId; // 小区唯一标识符
	private Integer locationAreaCode; // GSM和WCDMA网络的位置区域代码LAC
	private Integer mobileCountryCode;// 移动电话基站国家代码MCC
	private Integer mobileNetworkCode; // 移动电话基站的网络代码 MNC
	private Integer age;// 自从此小区成为主小区后经过的毫秒数
	private Integer signalStrength; // 测量到的无线信号强度（以 dBm 为单位）
	private Integer timingAdvance;// 时间提前值

	public GoogleCellTower(String cellId, String locationAreaCode, String mobileCountryCode, String mobileNetworkCode,
			Integer age, String signalStrength, Integer timingAdvance) {
		super();
		this.cellId = (cellId == null ? null : Integer.parseInt(cellId));
		this.locationAreaCode = (locationAreaCode == null ? null : Integer.parseInt(locationAreaCode));
		this.mobileCountryCode = (mobileCountryCode == null ? null : Integer.parseInt(mobileCountryCode));
		this.mobileNetworkCode = (mobileNetworkCode == null ? null : Integer.parseInt(mobileNetworkCode));
		this.age = age;
		this.signalStrength = (signalStrength == null ? null : Integer.parseInt(signalStrength));
		this.timingAdvance = timingAdvance;
	}

	public Integer getCellId() {
		return cellId;
	}

	public Integer getLocationAreaCode() {
		return locationAreaCode;
	}

	public Integer getMobileCountryCode() {
		return mobileCountryCode;
	}

	public Integer getMobileNetworkCode() {
		return mobileNetworkCode;
	}

	public Integer getAge() {
		return age;
	}

	public Integer getSignalStrength() {
		return signalStrength;
	}

	public Integer getTimingAdvance() {
		return timingAdvance;
	}

	public void setCellId(Integer cellId) {
		this.cellId = cellId;
	}

	public void setLocationAreaCode(Integer locationAreaCode) {
		this.locationAreaCode = locationAreaCode;
	}

	public void setMobileCountryCode(Integer mobileCountryCode) {
		this.mobileCountryCode = mobileCountryCode;
	}

	public void setMobileNetworkCode(Integer mobileNetworkCode) {
		this.mobileNetworkCode = mobileNetworkCode;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setSignalStrength(Integer signalStrength) {
		this.signalStrength = signalStrength;
	}

	public void setTimingAdvance(Integer timingAdvance) {
		this.timingAdvance = timingAdvance;
	}

}
