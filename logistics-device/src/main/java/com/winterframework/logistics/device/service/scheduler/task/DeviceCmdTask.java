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
import org.springframework.stereotype.Service;

import com.winterframework.logistics.base.exception.BizException;
import com.winterframework.logistics.base.thread.BizMultiThread;
import com.winterframework.logistics.common.entity.Device;
import com.winterframework.logistics.device.common.DeviceBizLock;
import com.winterframework.logistics.device.entity.DeviceCmd;
import com.winterframework.logistics.device.server.notification.INotificationService;
import com.winterframework.logistics.device.service.IDeviceCmdService;
import com.winterframework.logistics.device.service.IDvcDeviceService;


/**
 * 设备指令执行
 * @ClassName
 * @Description
 * @author ibm
 * 2017年12月21日
 */
@Service("DeviceCmdTask")
public class DeviceCmdTask {

	private Logger log = LoggerFactory.getLogger(getClass());
	private final String logPrefix="cmd task--";

	@Resource(name = "dvcDeviceServiceImpl")
	private IDvcDeviceService dvcDeviceService;
	@Resource(name = "deviceCmdServiceImpl")
	private IDeviceCmdService deviceCmdService;
	@Resource(name = "notificationServiceImpl")
	private INotificationService notificationService;
	

	private static final ExecutorService threadPool = Executors.newFixedThreadPool(80);
	
	public void execute() throws Exception {
		log.debug("device cmd start.");
		List<String> imeis=deviceCmdService.queryImeisNotClosed();
		if(!imeis.isEmpty()){
			for(final String imei:imeis){
				new BizMultiThread(threadPool,DeviceBizLock.DEVICE_CMD+imei,5*60) {	//5mins
					@Override
					protected void doBiz() throws BizException {
						try{
							Device device=dvcDeviceService.getByNumber(imei);
							if(device!=null){
								if(device.getStatus().intValue()==Device.Status.DISABLE.getValue()){
									log.error(logPrefix+"device is unavailable.imei="+imei);
								}
								if(device.getOnff().intValue()!=Device.ONFF.ONLINE.getValue()){
									log.error(logPrefix+"device is offline.imei="+imei);
								}
								//在线才执行指令
								List<DeviceCmd> cmdList=deviceCmdService.queryListNotClosedByImei(imei);
								for(DeviceCmd cmd:cmdList){
									//一个报错 就全部停止 保证指令时间上串行,已经执行的指令不回滚
									if(deviceCmdService.send(cmd,device)){
										break; //指令执行中 则全部指令依次等待
									}
								}
							}else{
								log.error(logPrefix+"imei is invalid.imei="+imei);
							}
						}catch(Exception e){
							log.error("device command execute failed.imei="+imei,e);
						}
					}
				}.start();
			}
		}
		log.debug("device cmd finish.");
	}
	
}
