package com.winterframework.logistics.web.service;

import java.util.List;

import com.winterframework.logistics.common.entity.DeviceLocation;
import com.winterframework.logistics.common.entity.TransOrder;

public interface IDeviceLocationWebService {

	List<DeviceLocation> findNewLocationListByOrders(String url, String findNewLocationList,
			List<TransOrder> transOrders);

	DeviceLocation findNewLocationByOrder(String url, String findNewDeviceLocation, TransOrder transOrder);

	List<DeviceLocation> getLocationsByOrder(String url, String findDeviceLocationByOrderId, Long orderId);

}
