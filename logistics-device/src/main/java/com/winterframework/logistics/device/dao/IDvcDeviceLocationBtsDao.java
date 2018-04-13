package com.winterframework.logistics.device.dao;

import java.util.List;

import com.winterframework.logistics.common.dao.IDeviceLocationBtsDao;
import com.winterframework.logistics.common.entity.DeviceLocationBts;

public interface IDvcDeviceLocationBtsDao extends IDeviceLocationBtsDao {

	List<DeviceLocationBts> getListByHandleflagAndTimes(Integer handleFlag,Long fromTime,Long toTime) throws Exception;
	List<DeviceLocationBts> getListByImeiAndTimes(String imei,Long fromTime,Long toTime) throws Exception;

}
