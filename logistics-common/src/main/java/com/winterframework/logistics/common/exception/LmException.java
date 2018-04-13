package com.winterframework.logistics.common.exception;

import com.winterframework.logistics.base.exception.BizException;
import com.winterframework.logistics.common.enums.StatusCode;

public class LmException extends BizException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7412027649297245454L;

	public LmException(StatusCode status){
		super(status.getCode(),status.getMessage(),null);
	}
	public LmException(com.winterframework.logistics.base.enums.StatusCode status){
		super(status.getCode(),status.getMessage(),null);
	}
	public LmException(StatusCode status,Throwable e){
		super(status.getCode(),status.getMessage(),e);
	}
	public LmException(com.winterframework.logistics.base.enums.StatusCode status,Throwable e){
		super(status.getCode(),status.getMessage(),e);
	}
	public LmException(BizException bizExcep){
		super(bizExcep.getCode(),bizExcep.getMessage(),bizExcep);
	}
}
