package com.winterframework.logistics.base.model;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
/**
 * 上下文
 * @ClassName
 * @Description
 * @author ibm
 * 2015年8月31日
 */
public class Context { 
	private final static Logger log = LoggerFactory.getLogger(Context.class);
	private Map<String,Object> attrs;
	private final String USERIDKEY="userId";
	public Context(){
		attrs=new HashMap<String,Object>();
	};
	public Context(Long userId){
		attrs=new HashMap<String,Object>();
		set("userId", userId);
	}
	public void set(String key,Object value){
		attrs.put(key, value);
	}
	public Object get(String key){
		return attrs.get(key);
	}
	public Long getUserId(){
		Object userId=get(USERIDKEY);
		if(userId instanceof Integer){
			return new Long((Integer)userId);
		}else if(userId instanceof Long){
			return (Long)get(USERIDKEY);
		}
		return null;
	}
	public Context setUserId(Long userId){
		set(USERIDKEY, userId);
		return this;
	}
	
}
