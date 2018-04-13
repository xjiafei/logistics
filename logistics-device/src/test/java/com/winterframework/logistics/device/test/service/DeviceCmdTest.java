package com.winterframework.logistics.device.test.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;

import com.winterframework.logistics.base.utils.NumberUtil;
import com.winterframework.logistics.device.service.scheduler.task.DeviceCmdTask;
import com.winterframework.logistics.device.test.BaseTestCase;



public class DeviceCmdTest extends BaseTestCase {
	Logger log=LoggerFactory.getLogger(DeviceCmdTest.class);

	@Resource(name="DeviceCmdTask")
	private DeviceCmdTask task;
	@Resource(name="bizNumberUtil")
	private NumberUtil bizNumberUtil;
	
	@Test
	@Rollback(false)
	public void test() throws Exception{
		//task.execute();
		try{
		log.error("AAAAAAAAAAAAAAAAAAAA"+bizNumberUtil.getNumber("200", 3, false));
		}catch(Exception e){
			log.error("",e);
		}
	}
	
}