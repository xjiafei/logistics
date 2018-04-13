package com.winterframework.logistics.device.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.winterframework.logistics.base.model.Context;
import com.winterframework.logistics.common.exception.LmException;
public abstract class AbstractAdditionHandler{
	protected Logger log = LoggerFactory.getLogger(getClass()); 
	
	public abstract void handle(Context ctx,String additionData) throws LmException;
	
}
