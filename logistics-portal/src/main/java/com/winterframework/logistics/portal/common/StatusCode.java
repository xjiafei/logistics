package com.winterframework.logistics.portal.common;

import org.apache.commons.lang3.EnumUtils;

public enum StatusCode{
		USER_INVALID(50001,"user invalid."),	//用户无效
		FILE_UPLOAD_FAILED(50002,"file upload failed."),	//文件上传失败
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