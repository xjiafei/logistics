package com.winterframework.logistics.device.dto;

public class GoogleDocumentationRequest {
	private Integer homeMobileCountryCode; // MCC代码
	private Integer homeMobileNetworkCode; // MNC代码
	private String radioType; // 移动无线网络类型
	private String carrier; // 运营商名称
	private String considerIp; // 指定wi-fi和移动电话基站的信号不可用时，是否回退Ip地理位置 false禁用
	private GoogleCellTower[] cellTowers; // 移动电话基站对象的数组
	private GoogleWiFiAccessPoint[] wifiAccessPoints; // Wi-Fi 接入点对象的数组

	public Integer getHomeMobileCountryCode() {
		return homeMobileCountryCode;
	}

	public Integer getHomeMobileNetworkCode() {
		return homeMobileNetworkCode;
	}

	public String getRadioType() {
		return radioType;
	}

	public String getCarrier() {
		return carrier;
	}

	public String getConsiderIp() {
		return considerIp;
	}

	public GoogleCellTower[] getCellTowers() {
		return cellTowers;
	}

	

	public void setHomeMobileCountryCode(Integer homeMobileCountryCode) {
		this.homeMobileCountryCode = homeMobileCountryCode;
	}

	public void setHomeMobileNetworkCode(Integer homeMobileNetworkCode) {
		this.homeMobileNetworkCode = homeMobileNetworkCode;
	}

	public void setRadioType(String radioType) {
		this.radioType = radioType;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public void setConsiderIp(String considerIp) {
		this.considerIp = considerIp;
	}

	public void setCellTowers(GoogleCellTower[] cellTowers) {
		this.cellTowers = cellTowers;
	}

	public GoogleWiFiAccessPoint[] getWifiAccessPoints() {
		return wifiAccessPoints;
	}

	public void setWifiAccessPoints(GoogleWiFiAccessPoint[] wifiAccessPoints) {
		this.wifiAccessPoints = wifiAccessPoints;
	}



}
