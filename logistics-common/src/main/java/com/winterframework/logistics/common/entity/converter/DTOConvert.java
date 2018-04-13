package com.winterframework.logistics.common.entity.converter;

import com.winterframework.logistics.common.entity.Test;
import com.winterframework.logistics.dto.TestRequest;

public class DTOConvert {

	public static Test convert2Test(TestRequest req){
		if(null==req) return null;
		Test test=new Test(); 
		test.setId(req.getId());
		
		return test;
	}
}
