package com.winterframework.logistics.common.dao;

import java.util.List;

import com.winterframework.logistics.base.dao.IBaseDao;
import com.winterframework.logistics.common.entity.DeviceLocation;

public interface IDeviceLocationDao  extends IBaseDao<DeviceLocation>{
	
	Long insertOrder(DeviceLocation deviceLocation);
	
	List<DeviceLocation> findListByAttribute(List<Long> deviceIdList);
	 
	DeviceLocation findNewDeviceLocation(Long orderId);
	
	List<DeviceLocation> getListByOrderId(Long orderId);
	
	List<DeviceLocation> findNewDeviceLocations(List<Long> orderId);
}