package com.winterframework.logistics.device.dao;

import java.util.List;

import com.winterframework.logistics.common.dao.IDeviceLocationSemiDao;
import com.winterframework.logistics.common.entity.DeviceLocationSemi;

public interface IDvcDeviceLocationSemiDao extends IDeviceLocationSemiDao {
	List<String> getImeisByHandleStatus(List<DeviceLocationSemi.HandleStatus> handleStatusList) throws Exception;
	List<DeviceLocationSemi> getUnhandleListByImeiAndTimes(String imei,Long fromTime,Long toTime) throws Exception;
	DeviceLocationSemi getPrevious(Long id) throws Exception;

}
