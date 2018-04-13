/**   
* @Title: LunarInitTask.java 
* @Package com.winterframework.efamily.service.schedule 
* @Description: TODO(用一句话描述该文件做什么) 
* @author floy   
* @date 2015-9-15 下午1:41:20 
* @version V1.0   
*/
package com.winterframework.logistics.device.service.scheduler.task;



import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.winterframework.logistics.device.service.IDvcDeviceLocationParseService;


public class DeviceLocationParseGpsTask {

	private Logger log = LoggerFactory.getLogger(DeviceLocationParseGpsTask.class);

	@Resource(name = "dvcDeviceLocationParseServiceImpl")
	private IDvcDeviceLocationParseService dvcDeviceLocationParseService;

	public void execute() throws Exception {
		log.debug("device location parse start.");
//		dvcDeviceLocationParseService.executeGps();
		dvcDeviceLocationParseService.executeGoogleReGeocodeByGps();
		log.debug("device location parse finish.");
	}
}
