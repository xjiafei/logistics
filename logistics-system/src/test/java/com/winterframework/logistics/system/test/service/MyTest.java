package com.winterframework.logistics.system.test.service;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;

import com.winterframework.logistics.system.test.BaseTestCase;



public class MyTest extends BaseTestCase {
	Logger log=LoggerFactory.getLogger(MyTest.class);

	/*@Resource(name="testServiceImpl")
	private ITestService testService;*/
	
	@Test
	@Rollback(false)
	public void test() throws Exception{
		
	}
	
}