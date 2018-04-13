package com.winterframework.logistics.common.enums;

import org.apache.commons.lang3.EnumUtils;

public enum HttpStatusCode {
	SUCCESS(0, "successs"), // 请求成功
	FAILED(-1, "failed"), // 请求失败
	NULLPOINTER(1, "The data is empty"), // 返回空数据
	NOTEXISTS(40004,"Data Not Available"), //不存在数据
	USEREXIST(60001,"The user has already existed"), //用户不存在
	USERNOTEXIST(60000,"The user not existed"), //用户不存在
	HTTP(5, "http error"), // HTTP错误
	TEST(10000, "test"); // TEST

	private int _code;
	private String _message;

	HttpStatusCode(int code, String message) {
		this._code = code;
		this._message = message;
	}

	public int getCode() {
		return this._code;
	}

	public String getMessage() {
		return this._message;
	}

	public static void main(String[] s) {
		System.out.println(EnumUtils.getEnum(HttpStatusCode.class, 5 + "").getCode());
	}
}