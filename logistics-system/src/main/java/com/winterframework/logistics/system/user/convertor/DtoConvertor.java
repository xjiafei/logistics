package com.winterframework.logistics.system.user.convertor;

import com.winterframework.logistics.common.entity.Test;
import com.winterframework.logistics.common.entity.User;
import com.winterframework.logistics.dto.TestRequest;
import com.winterframework.logistics.dto.UserRequest;

public class DtoConvertor {

	public static Test toTest(TestRequest req){
		if(null==req) return null;
		Test test=new Test(); 
		test.setId(req.getId());
		
		return test;
	}
	public static User toUser(UserRequest req){
		if(null==req) return null;
		User entity=new User(); 
		entity.setUserName(req.getUserName());
		entity.setPasswd(req.getPasswd());
		entity.setHeadImg(req.getHeadImg());
		entity.setType(req.getType());
		entity.setPhoneNumber(req.getPasswd());
		entity.setRemark(req.getRemark());
		
		return entity;
	}
	
}
