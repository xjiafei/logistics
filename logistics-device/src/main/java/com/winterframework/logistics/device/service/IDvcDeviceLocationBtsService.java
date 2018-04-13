package com.winterframework.logistics.device.service;

import java.util.Date;
import java.util.List;

import com.winterframework.logistics.common.entity.DeviceLocationBts;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.common.service.IDeviceLocationBtsService;

public interface IDvcDeviceLocationBtsService extends IDeviceLocationBtsService{
	public List<DeviceLocationBts> queryUnhandleList(Date fromTime,Date toTime) throws LmException;
	public List<DeviceLocationBts> queryListByImeiAndTimes(String imei,Date fromTime,Date toTime) throws LmException;

}
