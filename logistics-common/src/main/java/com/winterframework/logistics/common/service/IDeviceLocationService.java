package com.winterframework.logistics.common.service;


import java.util.List;

import com.winterframework.logistics.base.service.IBaseService;
import com.winterframework.logistics.common.entity.DeviceLocation;

public interface IDeviceLocationService extends IBaseService<DeviceLocation> {
	public Long addDeviceLocation(DeviceLocation deviceLocation);
	
	public List<DeviceLocation> findByAttribute(List<Long> deviceIdList);
	public List<DeviceLocation> findByOrderId(Long orderId);
	
	public DeviceLocation findNewDeviceLocationById(Long orderId);
	
	public List<DeviceLocation> findNewDeviceLocations(List<Long> orderIds);
}
