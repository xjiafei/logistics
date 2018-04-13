package com.winterframework.logistics.portal.exception;

import com.winterframework.logistics.base.exception.BizException;
import com.winterframework.logistics.portal.common.StatusCode;

public class PortalException extends BizException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7412027649297245454L;

	public PortalException(StatusCode status){
		super(status.getCode(),status.getMessage(),null);
	}
	public PortalException(com.winterframework.logistics.base.enums.StatusCode status){
		super(status.getCode(),status.getMessage(),null);
	}
	public PortalException(StatusCode status,Throwable e){
		super(status.getCode(),status.getMessage(),e);
	}
	public PortalException(com.winterframework.logistics.base.enums.StatusCode status,Throwable e){
		super(status.getCode(),status.getMessage(),e);
	}
	public PortalException(BizException bizExcep){
		super(bizExcep.getCode(),bizExcep.getMessage(),bizExcep);
	}
}
