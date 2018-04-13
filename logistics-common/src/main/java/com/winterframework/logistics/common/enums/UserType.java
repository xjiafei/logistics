package com.winterframework.logistics.common.enums;

public enum UserType {
	COMMON_USER(0), // 平台管理员
	COMPANY_USER(1), // 普通用户
	OTHER_USER(2); //其他用户
	private int _value;

	UserType(int value) {
		this._value = value;
	}

	public int getValue() {
		return _value;
	}
}
