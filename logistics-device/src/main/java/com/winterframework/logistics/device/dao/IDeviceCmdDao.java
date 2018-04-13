package com.winterframework.logistics.device.dao;


import java.util.List;

import com.winterframework.logistics.base.dao.IBaseDao;
import com.winterframework.logistics.device.entity.DeviceCmd;
import com.winterframework.logistics.device.entity.DeviceCmd.ExecStatus;

public interface IDeviceCmdDao  extends IBaseDao<DeviceCmd> {
	DeviceCmd getEarliestOneByImeiAndFunctionAndExecStatus(String imei,String function,List<ExecStatus> execStatusList) throws Exception;
	List<String> getImeisByExecStatus(List<DeviceCmd.ExecStatus> execStatusList) throws Exception;
	List<DeviceCmd> getListByImeiAndExecStatus(String imei,List<DeviceCmd.ExecStatus> execStatusList) throws Exception;
	
}