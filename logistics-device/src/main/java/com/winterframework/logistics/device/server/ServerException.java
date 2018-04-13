package com.winterframework.logistics.device.server;

import com.winterframework.logistics.base.exception.BizException;
import com.winterframework.logistics.common.enums.StatusCode;

public class ServerException extends BizException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8272714884894139708L;
	public ServerException(StatusCode status){
		super(status.getCode(),status.getMessage(),null);
	}
	public ServerException(com.winterframework.logistics.base.enums.StatusCode status){
		super(status.getCode(),status.getMessage(),null);
	}
	public ServerException(StatusCode status,Throwable e){
		super(status.getCode(),status.getMessage(),e);
	}
	public ServerException(com.winterframework.logistics.base.enums.StatusCode status,Throwable e){
		super(status.getCode(),status.getMessage(),e);
	}
	public ServerException(BizException bizExcep){
		super(bizExcep.getCode(),bizExcep.getMessage(),bizExcep);
	}
}
