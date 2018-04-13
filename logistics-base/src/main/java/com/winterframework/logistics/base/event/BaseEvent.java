package com.winterframework.logistics.base.event;

import org.springframework.context.ApplicationEvent;
 
public class BaseEvent extends ApplicationEvent { 
	/**
	 * 
	 */
	private static final long serialVersionUID = -7653839371443595608L;

	/** 
	* Title:
	* Description:
	* @param source 
	*/
	public BaseEvent(Object source) {
		super(source);
	}

	
}
