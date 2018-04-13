/**   
* @Title: LunarInitTask.java 
* @Package com.winterframework.efamily.service.schedule 
* @Description: TODO(用一句话描述该文件做什么) 
* @author floy   
* @date 2015-9-15 下午1:41:20 
* @version V1.0   
*/
package com.winterframework.logistics.device.service.scheduler.task;



import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.winterframework.logistics.base.exception.BizException;
import com.winterframework.logistics.base.thread.BizMultiThread;
import com.winterframework.logistics.base.utils.DateUtils;
import com.winterframework.logistics.common.entity.DeviceLocationSemi;
import com.winterframework.logistics.device.common.DeviceBizLock;
import com.winterframework.logistics.device.service.IDvcDeviceLocationSemiService;
import com.winterframework.logistics.device.service.IDvcDeviceService;

@Service("DeviceLocationSemiTask")
public class DeviceLocationSemiTask {

	private Logger log = LoggerFactory.getLogger(DeviceLocationSemiTask.class);

	@Resource(name = "dvcDeviceLocationSemiServiceImpl")
	private IDvcDeviceLocationSemiService dvcDeviceLocationSemiService;
	@Resource(name = "dvcDeviceServiceImpl")
	private IDvcDeviceService dvcDeviceService;
	
	private static final ExecutorService threadPool = Executors.newFixedThreadPool(80);
	private final String logPrefix="location semi task--";
	
	public void execute() throws Exception {
		log.debug("device location semi start.");
		List<String> imeis=dvcDeviceLocationSemiService.queryImeisUnhandled();
		if(!imeis.isEmpty()){
			for(final String imei:imeis){
				new BizMultiThread(threadPool,DeviceBizLock.DEVICE_LOCAIION_SEMI+imei,5*60) {	//5mins
					@Override
					protected void doBiz() throws BizException {
						Date fromTime=DateUtils.addDays(DateUtils.currentDate(),-1);
						Date toTime=DateUtils.currentDate();
						try{
							List<DeviceLocationSemi> semiList=dvcDeviceLocationSemiService.queryUnhandleListByImei(imei, fromTime, toTime);
							dvcDeviceLocationSemiService.handle(semiList);
						}catch(Exception e){
							log.error(logPrefix+"device location semi  execute failed.imei="+imei+" fromTime="+fromTime+" toTime="+toTime,e);
						}
					}
				}.start();
			}
		}
		log.debug("device location semi finish.");
	}
}
