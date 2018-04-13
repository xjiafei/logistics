package com.winterframework.logistics.device.dao;

import java.util.List;

import com.winterframework.logistics.common.dao.IDeviceLocationGpsDao;
import com.winterframework.logistics.common.entity.DeviceLocationGps;

public interface IDvcDeviceLocationGpsDao extends IDeviceLocationGpsDao {

	List<DeviceLocationGps> getListByHandleflagAndTimes(Integer handleFlag,Long fromTime,Long toTime) throws Exception;
	List<DeviceLocationGps> getListByImeiAndTimes(String imei,Long fromTime,Long toTime) throws Exception;

}
