package com.winterframework.logistics.app.exception;

import com.winterframework.logistics.base.exception.BizException;
import com.winterframework.logistics.base.model.Request;
import com.winterframework.logistics.base.utils.JsonUtils;
import com.winterframework.logistics.common.enums.StatusCode;
import com.winterframework.logistics.dto.LoginRequest;

public class AppException extends BizException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7412027649297245454L;

	public AppException(int code) {
		super(code);
	}
	public AppException(int code,Throwable exception) {
		super(code,exception);
	}
	public AppException(int code, String msg, Throwable exception) {
		super(code, msg,exception);
	}
	public AppException(StatusCode status){
		super(status.getCode(),status.getMessage(),null);
	}
	
	public AppException(com.winterframework.logistics.base.enums.StatusCode status){
		super(status.getCode(),status.getMessage(),null);
	}
	public AppException(StatusCode status,Throwable e){
		super(status.getCode(),status.getMessage(),e);
	}
	public AppException(com.winterframework.logistics.base.enums.StatusCode status,Throwable e){
		super(status.getCode(),status.getMessage(),e);
	}
	public AppException(BizException bizExcep){
		super(bizExcep.getCode(),bizExcep.getMessage(),bizExcep);
	}
	public static void main(String[] s){
		Request<LoginRequest> a=new Request<LoginRequest>();
		a.setToken("aaa");
		a.setTimestamp(1234566L);LoginRequest ss=new LoginRequest();ss.setUserName("test");
		a.setData(ss);
		System.out.println(JsonUtils.toJson(a));
	}
	
}
