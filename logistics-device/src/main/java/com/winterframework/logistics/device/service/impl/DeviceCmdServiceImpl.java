package com.winterframework.logistics.device.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.winterframework.logistics.base.enums.StatusCode;
import com.winterframework.logistics.base.enums.YesNo;
import com.winterframework.logistics.base.exception.BizException;
import com.winterframework.logistics.base.model.Context;
import com.winterframework.logistics.base.service.BaseServiceImpl;
import com.winterframework.logistics.base.utils.DateUtils;
import com.winterframework.logistics.common.entity.Device;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.device.dao.IDeviceCmdDao;
import com.winterframework.logistics.device.entity.DeviceCmd;
import com.winterframework.logistics.device.server.notification.INotificationService;
import com.winterframework.logistics.device.server.notification.Notification;
import com.winterframework.logistics.device.service.IDeviceCmdService;

@Service("deviceCmdServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class DeviceCmdServiceImpl extends BaseServiceImpl<IDeviceCmdDao,DeviceCmd> implements IDeviceCmdService {
	@Resource(name="deviceCmdDaoImpl")
	IDeviceCmdDao deviceCmdDao;
	@Resource(name = "notificationServiceImpl")
	private INotificationService notificationService;
	
	@Override
	protected IDeviceCmdDao getEntityDao() {
		// TODO Auto-generated method stub
		return deviceCmdDao;
	}
	
	@Override
	public DeviceCmd queryEarliestOneNotClosed(String imei, String function) throws LmException {
		try {
			List<DeviceCmd.ExecStatus> execStatusList = getUnClosedExecStatus();
			return deviceCmdDao.getEarliestOneByImeiAndFunctionAndExecStatus(imei, function, execStatusList);
		}catch(Exception e){
			log.error("getEarliestOneByImeiAndFunctionAndExecStatus error. imei="+imei+" function="+function,e);
			throw new LmException(StatusCode.DAO_ERROR,e);
		}
	}
	@Override
	public List<String> queryImeisNotClosed() throws LmException {
		List<DeviceCmd.ExecStatus> execStatusList = getUnClosedExecStatus();
		try {
			return deviceCmdDao.getImeisByExecStatus(execStatusList);
		}catch(Exception e){
			log.error("getImeisByExecStatus error. execStatusList="+execStatusList.toString(),e);
			throw new LmException(StatusCode.DAO_ERROR,e);
		}
	}

	public List<DeviceCmd.ExecStatus> getUnClosedExecStatus() {
		List<DeviceCmd.ExecStatus> execStatusList=new ArrayList<DeviceCmd.ExecStatus>();
		execStatusList.add(DeviceCmd.ExecStatus.EXECUTE);
		execStatusList.add(DeviceCmd.ExecStatus.INIT);
		execStatusList.add(DeviceCmd.ExecStatus.WAITING);
		return execStatusList;
	}
	@Override
	public List<DeviceCmd> queryListNotClosedByImei(String imei) throws LmException {
		List<DeviceCmd.ExecStatus> execStatusList = getUnClosedExecStatus();
		try {
			return deviceCmdDao.getListByImeiAndExecStatus(imei,execStatusList);
		}catch(Exception e){
			log.error("getListByImeiAndExecStatus error. imei="+imei+" execStatusList="+execStatusList.toString(),e);
			throw new LmException(StatusCode.DAO_ERROR,e);
		}
	}
	@Override
	public void success(Context ctx,String imei, String function) throws LmException {
		DeviceCmd cmd=queryEarliestOneNotClosed(imei, function);
		cmd.setExecStatus(DeviceCmd.ExecStatus.SUCCESS.getValue());
		try {
			save(ctx, cmd);
		} catch (BizException e) {
			log.error("save failed when finish. imei="+imei+" function="+function,e);
			throw new LmException(e);
		}
	}
	private boolean isCmdClosed(DeviceCmd cmd){
		for(DeviceCmd.ExecStatus execStatus:getUnClosedExecStatus()){
			if(cmd.getExecStatus().intValue()==execStatus.getValue()){
				return false;
			}
		}
		return true;
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public boolean send(DeviceCmd cmd,Device device) throws LmException {
		final String logPrefix="cmd send--";
		//判断指令失败或者过期等
		/*
	 	0：未执行 初始
	 	1:执行中  下发指令 （1分钟未收到响应 重新执行)
	 	2：成功成功 收到响应
	 	3：执行失败  暂时不存在该状态
	 	4：执行过期 （上传周期+5分钟）之内未下发成功
	 	5：等待执行 设备不可用或者不在线
	 	*/
		
		final int failedMins=1;
		final int expiredMins=device.getLocationFreq()+5;
		if(isCmdClosed(cmd)){
			return false;
		}
		if(DateUtils.calcMinutesBetween(DateUtils.getDate(cmd.getCreateTime()),DateUtils.currentDate())>expiredMins){
			//执行过期
			cmd.setExecStatus(DeviceCmd.ExecStatus.EXPIRED.getValue());
		}else if(device.getStatus().intValue()==Device.Status.DISABLE.getValue() 
				|| device.getOnff().intValue()!=Device.ONFF.ONLINE.getValue()){
			//等待执行
			cmd.setExecStatus(DeviceCmd.ExecStatus.WAITING.getValue());
		/*}else if(cmd.getExecStatus().intValue()==DeviceCmd.ExecStatus.EXECUTE.getValue() 
				&& DateUtils.calcMinutesBetween(DateUtils.getDate(cmd.getExecTime()),DateUtils.currentDate())>failedMins){
			//未响应 重新下发
			cmd.setExecStatus(DeviceCmd.ExecStatus.INIT.getValue());
			cmd.setExecTime(null);*/
		}else if(cmd.getExecStatus().intValue()==DeviceCmd.ExecStatus.EXECUTE.getValue() 
				&& DateUtils.calcMinutesBetween(DateUtils.getDate(cmd.getExecTime()),DateUtils.currentDate())<=failedMins){
			//等待执行响应
			return true;
		}else{
			//INIT、EXECUTE(gt failedMins)、WAITING
			Notification notification=new Notification();
			notification.setTarget(cmd.getImei());
			notification.setFnType(cmd.getFunction().substring(0,1));
			notification.setFnKey(cmd.getFunction().substring(1));
			notification.setData(cmd.getData());
			notification.setIsSave(cmd.getSave()==YesNo.YES.getValue());
			notification.setIsReply(cmd.getReply()==YesNo.YES.getValue());
			
			try {
				cmd.setCommand(notificationService.notify(notification));
				cmd.setExecStatus(DeviceCmd.ExecStatus.EXECUTE.getValue());
				cmd.setExecTime(DateUtils.getCurTime());
				cmd.setExecCount(cmd.getExecCount()+1);
			} catch (BizException e) {
				log.error(logPrefix+"notication failed. imei："+cmd.getImei(),e);
				throw new LmException(com.winterframework.logistics.common.enums.StatusCode.NOTIFICATION_FAILED);
			}
		}
		try {
			save(new Context(-1L), cmd);
		} catch (BizException e) {
			log.error(logPrefix+"device command save failed. cmd："+cmd.toString(),e);
			throw new LmException(com.winterframework.logistics.common.enums.StatusCode.FAILED);
		}
		return false;
	}

}
