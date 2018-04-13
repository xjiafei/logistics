package com.winterframework.logistics.common.exception;

import com.winterframework.logistics.base.exception.BizException;
import com.winterframework.logistics.common.enums.StatusCode;

@SuppressWarnings("serial")
public class TestException extends BizException {
	private static int code=StatusCode.TEST.getCode();
	public TestException(){
		super(code,"",null);
	}
	
}
