package com.winterframework.logistics.portal.enums;

/**
 * 编码前缀
 * @ClassName
 * @Description
 */
public enum NumberPrefix {
	COMMON("100"), //基础数据
	QUOTATION("201"); //询价单
	
	private String _value;

	NumberPrefix(String value) {
		this._value = value;
	}

	public String getValue() {
		return _value;
	}
}
