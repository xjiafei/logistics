package com.winterframework.logistics.portal.enums;

public enum EPtlBiz {
	BIZ_TYPE_STATUS_AVAILABLE(1), //业务类型的状态：可用
	BIZ_TYPE_STATUS_NOTAVAILABLE(0); //业务类型的状态： 不可用
	
	private int _value;

	EPtlBiz(int value) {
		this._value = value;
	}

	public int getValue() {
		return _value;
	}
}
