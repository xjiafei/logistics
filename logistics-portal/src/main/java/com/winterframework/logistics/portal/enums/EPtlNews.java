package com.winterframework.logistics.portal.enums;

public enum EPtlNews {
	NEWS_TYPE_COMMON(1), //类型：普通
	NEWS_TYPE_SPECIAL(2), //类型：特殊
	NEWS_STATUS_BUILTED(0),  //状态： 已创建
	NEWS_STATUS_PUBLISHED(1),  //状态：已发布
	NEWS_STATUS_CANCELED(2);  //状态：已删除
	
	private int _value;

	EPtlNews(int value) {
		this._value = value;
	}

	public int getValue() {
		return _value;
	}
}
