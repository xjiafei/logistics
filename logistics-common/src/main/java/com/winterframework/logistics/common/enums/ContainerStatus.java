package com.winterframework.logistics.common.enums;

public enum ContainerStatus {
	USING_CONTAINER(50001,1), // 柜号已满
	NULL_CONTAINER(0,0); // 柜号不存在
	private int _key;
	private int _value;
	ContainerStatus(int key, int value) {
		this._key=key;
		this._value = value;	
	}
	public int getValue() {
		return _value;
	}
	public int getKey() {
		return _key;
	}
	
	public static int getKey(int value) {
		ContainerStatus[] values=ContainerStatus.values();
		for (ContainerStatus containerSatus : values) {
			if(containerSatus.getValue()==value) {
				return containerSatus.getKey();
			}
		}
		return value;
	}

}
