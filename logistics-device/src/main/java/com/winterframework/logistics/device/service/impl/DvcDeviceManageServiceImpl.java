package com.winterframework.logistics.device.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winterframework.logistics.base.enums.YesNo;
import com.winterframework.logistics.base.exception.BizException;
import com.winterframework.logistics.base.model.Context;
import com.winterframework.logistics.common.entity.Device;
import com.winterframework.logistics.common.enums.StatusCode;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.common.service.IDeviceService;
import com.winterframework.logistics.device.entity.DeviceCmd;
import com.winterframework.logistics.device.server.notification.Notification;
import com.winterframework.logistics.device.service.IDeviceCmdService;
import com.winterframework.logistics.device.service.IDvcDeviceManageService;

/**
 * 设备管理服务
 * @ClassName
 * @Description
 * @author ibm
 * 2017年11月29日
 */
@Service("dvcDeviceManageServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class DvcDeviceManageServiceImpl implements IDvcDeviceManageService {
	protected Logger log = LoggerFactory.getLogger(getClass());
	@Resource(name = "deviceServiceImpl")
	private IDeviceService deviceService;
	@Resource(name = "deviceCmdServiceImpl")
	private IDeviceCmdService deviceCmdService;
	
	@Override
	public void startup(Context ctx,String imei) throws LmException {
		/*Notification notification=new Notification();
		notification.setTarget(imei);
		notification.setFnType("A");
		notification.setFnKey("H");
		notification.setData("(P1,0,65535)");
		notification.setIsSave(true);
		notification.setIsReply(true);
		
		try {
			notificationService.notify(notification);
		} catch (ServerException e) {
			log.error("notication failed when startup imei："+imei,e);
			throw new LmException(StatusCode.NOTIFICATION_FAILED);
		}*/
	}
	
	@Override
	public void shutdown(Context ctx,String imei) throws LmException {
		Notification notification=new Notification();
		notification.setTarget(imei);
		Device device=deviceService.getByNumber(imei);
		if(device==null || device.getStatus().intValue()==YesNo.NO.getValue()){
			throw new LmException(StatusCode.IMEI_INVALID);
		}
		if(device.getModel().equals(Device.Model.T19.name())){
			locationFreqSett(ctx,imei, 60);	//上传1天1次
		}else{
			locationFreqSett(ctx,imei, 60);	//上传60mins
		}
	}
	public void shutdown_old(Context ctx,String imei) throws LmException {
		/*Notification notification=new Notification();
		notification.setTarget(imei);
		notification.setFnType("A");
		notification.setFnKey("H");
		notification.setData("(P0,0,0)");
		notification.setIsSave(true);
		notification.setIsReply(true);
		
		try {
			notificationService.notify(notification);
		} catch (ServerException e) {
			log.error("notication failed when shutdown imei："+imei,e);
			throw new LmException(StatusCode.NOTIFICATION_FAILED);
		}*/
	}
	
	@Override
	public void locationFreqSett(Context ctx,String imei, Integer freq) throws LmException {
		Device device=deviceService.getByNumber(imei);
		if(device==null || device.getStatus().intValue()==YesNo.NO.getValue()){
			throw new LmException(StatusCode.IMEI_INVALID);
		}
		String function="";
		String data="";
		String functionDesc="";
		if(device.getModel().equals(Device.Model.T19.name())){
			//最小可设置5分钟一次，最长一天
			if(freq>24*60){
				freq=24*60;
			}
			int times=24*60/freq;
			function="GB";
			data="0000,"+times;
			functionDesc="设置固定上传时间";
		}else{
			//回传频率，最大只能60分钟
			if(freq>60){
				freq=60;
			}
			function="BI";
			data=String.format("%04x",freq*60).toUpperCase()+"FFFF";
			functionDesc="设置回传时间间隔";
		}
		//*MG2011BI0078012C# 
		DeviceCmd deviceCmd=new DeviceCmd();
		deviceCmd.setImei(imei);
		deviceCmd.setModel(device.getModel());
		deviceCmd.setFunction(function);
		deviceCmd.setFunctionDesc(functionDesc);
		deviceCmd.setData(data);
		deviceCmd.setExecMode(DeviceCmd.ExecMode.IMMEDIATE.getValue());
		deviceCmd.setExecStatus(DeviceCmd.ExecStatus.INIT.getValue());
		deviceCmd.setExecCount(0);
		deviceCmd.setStatus(YesNo.YES.getValue());
		deviceCmd.setSave(YesNo.YES.getValue());
		deviceCmd.setReply(YesNo.YES.getValue());
		
		try {
			deviceCmdService.save(ctx, deviceCmd);
		} catch (BizException e) {
			log.error("device command save failed when location freq sett.deviceCmd="+deviceCmd.toString(),e);
			throw new LmException(e);
		}
		
	}
	
}
