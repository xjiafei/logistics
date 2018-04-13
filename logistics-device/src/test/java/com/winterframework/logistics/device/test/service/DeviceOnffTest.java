package com.winterframework.logistics.device.test.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;

import com.winterframework.logistics.device.service.IDvcDeviceService;
import com.winterframework.logistics.device.service.scheduler.task.DeviceCmdTask;
import com.winterframework.logistics.device.test.BaseTestCase;



public class DeviceOnffTest extends BaseTestCase {
	Logger log=LoggerFactory.getLogger(DeviceOnffTest.class);

	@Resource(name = "dvcDeviceServiceImpl")
	private IDvcDeviceService dvcDeviceService;
	@Test
	@Rollback(false)
	public void test() throws Exception{
		dvcDeviceService.onffRefresh("868500027750579");
	}
	
}