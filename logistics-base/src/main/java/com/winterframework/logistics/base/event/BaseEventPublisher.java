package com.winterframework.logistics.base.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


@Component("baseEventPublisher")
public class BaseEventPublisher implements ApplicationContextAware {
	private ApplicationContext ctx;

	/**
	* Title: setApplicationContext
	* Description:
	* @param applicationContext
	* @throws BeansException 
	* @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext) 
	*/
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.ctx = applicationContext;
	}

	public void publish(BaseEvent event) {
		ctx.publishEvent(event);
	}

}
