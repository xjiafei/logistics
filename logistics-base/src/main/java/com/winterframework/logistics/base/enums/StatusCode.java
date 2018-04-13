package com.winterframework.logistics.base.enums;

import org.apache.commons.lang3.EnumUtils;

public enum StatusCode{
		SUCCESS(0,"successs."),	//请求成功
		HTTP(5,"http error."),	//HTTP请求错误
		UNKNOW(-1,"unkonw."),	//未知错误
		REQ_EXPIRED(101,"request expired."),	//请求过期
		SIGN_INVALID(102,"signature invalid."),	//signature校验失败
		TOKEN_INVALID(103,"token invalid."),	//token校验失败
		PARAM_INVALID(104,"parameter invalid."),	//参数不正确
		DAO_ERROR(105,"dao error."),	//DAO错误
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