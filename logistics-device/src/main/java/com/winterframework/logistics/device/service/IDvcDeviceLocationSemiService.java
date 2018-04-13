package com.winterframework.logistics.device.service;

import java.util.Date;
import java.util.List;

import com.winterframework.logistics.common.entity.DeviceLocationSemi;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.common.service.IDeviceLocationSemiService;

public interface IDvcDeviceLocationSemiService extends IDeviceLocationSemiService{
	List<String> queryImeisUnhandled() throws LmException;
	List<DeviceLocationSemi> queryUnhandleListByImei(String imei,Date fromTime,Date toTime) throws LmException;
	DeviceLocationSemi queryPrevious(Long id) throws LmException;
	void handle(List<DeviceLocationSemi> semiList) throws LmException;
}
