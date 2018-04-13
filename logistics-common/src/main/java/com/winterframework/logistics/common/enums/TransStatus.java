package com.winterframework.logistics.common.enums;

public enum TransStatus {
	TRANSPORT_ERROR(2), // 运输状态：运输错误
	TRANSPORT_ING(0), // 运输状态：运输中
	TRANSPORT_END(1); // 运输状态：运输结束

	private int _value;

	TransStatus(int value) {
		this._value = value;
	}

	public int getValue() {
		return _value;
	}
}
