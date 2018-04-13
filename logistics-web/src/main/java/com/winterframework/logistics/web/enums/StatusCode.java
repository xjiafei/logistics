package com.winterframework.logistics.web.enums;

import org.apache.commons.lang3.EnumUtils;

public enum StatusCode{
		SUCCESS(0,"successs."),	//请求成功
		TEST(10000,"test");	//TEST
	
		private int _code;
		private String _message;
		StatusCode(int code,String message){
			this._code=code;
			this._message=message;
		}
		public int getCode(){
			return this._code;
		}
		public String getMessage(){
			return this._message;
		}
		public static void main(String[] s){
			System.out.println(EnumUtils.getEnum(StatusCode.class, 5+"").getCode());
		}
	}