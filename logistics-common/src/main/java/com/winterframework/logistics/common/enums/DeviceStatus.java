package com.winterframework.logistics.common.enums;

public enum DeviceStatus {
	DISABLED(10007,0),  //不可用
	USABLE(10000,1),   //可用    闲置中 
	USING(10007,2),  //使用中    使用中 
	CALLBACK(10007,3), // 回收中  
	FINISH(10007,4);  // 使用完成
	private int _value;
	private int _key;
	DeviceStatus(int key,int value) {
		this._key=key;
		this._value = value;
		
	}
	public int getKey() {
		return _key;
	}
	public int getValue() {
		return _value;
	}
	public static int getKey(int value) {
		DeviceStatus[] values=DeviceStatus.values();
		for (DeviceStatus deviceStatus : values) {
			if(deviceStatus.getValue()==value) {
				return deviceStatus.getKey();
			}
		}
		return value;
	}

}
