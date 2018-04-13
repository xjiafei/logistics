package com.winterframework.logistics.app.service;

import java.util.List;

import com.winterframework.logistics.common.entity.DeviceLocation;
import com.winterframework.logistics.common.entity.TransOrder;

public interface IDeviceLocationAppService {

	List<DeviceLocation> findNewLocationListByOrders(String url, String findNewLocationList, List<TransOrder> data);

}
