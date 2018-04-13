package com.winterframework.logistics.device.dto;

public class GoogleWiFiAccessPoint {
	private String macAddress;//Wi-Fi 节点的 MAC 地址
	private Integer signalStrength; //测量到的当前信号强度
	private Integer age;//自从检测到此接入点后经过的毫秒数
	private Integer channel;//客户端与接入点进行通信的信道
	private Integer signalToNoiseRatio;//测量到的当前信噪比
	public String getMacAddress() {
		return macAddress;
	}
	public Integer getSignalStrength() {
		return signalStrength;
	}
	public Integer getAge() {
		return age;
	}
	public Integer getChannel() {
		return channel;
	}
	public Integer getSignalToNoiseRatio() {
		return signalToNoiseRatio;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	public void setSignalStrength(Integer signalStrength) {
		this.signalStrength = signalStrength;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public void setChannel(Integer channel) {
		this.channel = channel;
	}
	public void setSignalToNoiseRatio(Integer signalToNoiseRatio) {
		this.signalToNoiseRatio = signalToNoiseRatio;
	}
	
	
}
