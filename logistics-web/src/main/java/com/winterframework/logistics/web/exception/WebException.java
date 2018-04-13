package com.winterframework.logistics.web.exception;

import com.winterframework.logistics.base.exception.BizException;
import com.winterframework.logistics.base.model.Request;
import com.winterframework.logistics.base.utils.JsonUtils;
import com.winterframework.logistics.dto.LoginRequest;
import com.winterframework.logistics.web.enums.StatusCode;

public class WebException extends BizException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7412027649297245454L;

	public WebException(int code) {
		super(code);
	}
	public WebException(int code,Throwable exception) {
		super(code,exception);
	}
	public WebException(int code, String msg, Throwable exception) {
		super(code, msg,exception);
	}
	public WebException(StatusCode status){
		super(status.getCode(),status.getMessage(),null);
	}
	
	public WebException(com.winterframework.logistics.base.enums.StatusCode status){
		super(status.getCode(),status.getMessage(),null);
	}
	public WebException(StatusCode status,Throwable e){
		super(status.getCode(),status.getMessage(),e);
	}
	public WebException(com.winterframework.logistics.base.enums.StatusCode status,Throwable e){
		super(status.getCode(),status.getMessage(),e);
	}
	public WebException(BizException bizExcep){
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
