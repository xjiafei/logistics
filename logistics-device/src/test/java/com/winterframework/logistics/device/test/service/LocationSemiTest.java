package com.winterframework.logistics.device.test.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;

import com.winterframework.logistics.device.service.scheduler.task.DeviceLocationSemiTask;
import com.winterframework.logistics.device.test.BaseTestCase;



public class LocationSemiTest extends BaseTestCase {
	Logger log=LoggerFactory.getLogger(LocationSemiTest.class);

	@Resource(name="DeviceLocationSemiTask")
	private DeviceLocationSemiTask deviceLocationSemiTask;
	
	@Test
	@Rollback(false)
	public void test() throws Exception{
		deviceLocationSemiTask.execute();
		//dvcDeviceLocationParseService.executeBts();
	}
	
}