package com.winterframework.logistics.device.service;

import java.util.Date;
import java.util.List;

import com.winterframework.logistics.common.entity.DeviceLocationGps;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.common.service.IDeviceLocationGpsService;

public interface IDvcDeviceLocationGpsService extends IDeviceLocationGpsService{
	public List<DeviceLocationGps> queryUnhandleList(Date fromTime,Date toTime) throws LmException;
	public List<DeviceLocationGps> queryListByImeiAndTimes(String imei,Date fromTime,Date toTime) throws LmException;
}
