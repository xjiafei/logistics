package com.winterframework.logistics.common.enums;

public enum UserStatus {
	CANCEL(-1), // 注销
	FORBIDDEN(0), // 禁用
	USEABLE(1); // 可用
	
	private int _value;

	UserStatus(int value) {
		this._value = value;
	}

	public int getValue() {
		return _value;
	}
}
