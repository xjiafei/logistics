package com.winterframework.logistics.device.test.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;

import com.winterframework.logistics.device.service.IDvcDeviceLocationParseService;
import com.winterframework.logistics.device.test.BaseTestCase;



public class LocationParseTest extends BaseTestCase {
	Logger log=LoggerFactory.getLogger(LocationParseTest.class);

	@Resource(name="dvcDeviceLocationParseServiceImpl")
	private IDvcDeviceLocationParseService dvcDeviceLocationParseService;
	
	@Test
	@Rollback(false)
	public void test() throws Exception{
		dvcDeviceLocationParseService.executeGps();
		//dvcDeviceLocationParseService.executeBts();
	}
	
}