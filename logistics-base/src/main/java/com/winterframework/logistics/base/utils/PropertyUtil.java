package com.winterframework.logistics.base.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.winterframework.modules.spring.exetend.ExtendedPropertyPlaceholderConfigurer;

@Component("propertyUtil")
public class PropertyUtil {
	@Autowired
	private ExtendedPropertyPlaceholderConfigurer propertyConfigurer;
	
	public String getProperty(String key){
		return (String)propertyConfigurer.getProperty(key);
	}
}
