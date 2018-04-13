package com.winterframework.logistics.common.enums;

import org.apache.commons.lang3.EnumUtils;

public enum StatusCode{
		SUCCESS(0,"successs."),	//请求成功
		FAILED(-1,"failed."),	//请求失败
		HTTP(5,"http error."),	//HTTP错误
		USER_INVALID(10001,"user invalid."),	//用户无效
		NOTIFICATION_FAILED(10002,"notication failed."),	//推送设备失败
		IMEI_INVALID(10003,"imei invalid."),	//IMEI无效
		HAS_NO_ORDER(10004,"has no order."),	//不存在订单
		GEOCODING_FAILED(10005,"geocoding failed."),	//地理解析失败
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