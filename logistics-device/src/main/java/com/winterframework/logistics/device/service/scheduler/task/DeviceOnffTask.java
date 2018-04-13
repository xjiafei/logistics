/**   
* @Title: LunarInitTask.java 
* @Package com.winterframework.efamily.service.schedule 
* @Description: TODO(用一句话描述该文件做什么) 
* @author floy   
* @date 2015-9-15 下午1:41:20 
* @version V1.0   
*/
package com.winterframework.logistics.device.service.scheduler.task;



import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.winterframework.logistics.base.enums.YesNo;
import com.winterframework.logistics.base.exception.BizException;
import com.winterframework.logistics.base.thread.BizMultiThread;
import com.winterframework.logistics.common.entity.Device;
import com.winterframework.logistics.device.common.DeviceBizLock;
import com.winterframework.logistics.device.service.IDvcDeviceService;


/**
 * 设备关机状态更新
 * @ClassName
 * @Description
 * @author ibm
 * 2017年12月21日
 */
public class DeviceOnffTask {

	private Logger log = LoggerFactory.getLogger(DeviceOnffTask.class);

	@Resource(name = "dvcDeviceServiceImpl")
	private IDvcDeviceService dvcDeviceService;
	private static final ExecutorService threadPool = Executors.newFixedThreadPool(80);
	
	public void execute() throws Exception {
		log.debug("device off start.");
		List<Device> deviceList=dvcDeviceService.queryListByStatus(YesNo.YES.getValue());
		if(!deviceList.isEmpty()){
			for(Device device:deviceList){
				final String imei=device.getNumber();
				new BizMultiThread(threadPool,DeviceBizLock.DEVICE_ONFF+imei,5*60) {	//5mins
					@Override
					protected void doBiz() throws BizException {
						try{
							dvcDeviceService.onffRefresh(imei);
						}catch(Exception e){
							log.error("device onff refresh failed.imei="+imei,e);
						}
					}
				}.start();
			}
		}
		log.debug("device off finish.");
	}
}
