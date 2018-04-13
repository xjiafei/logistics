package com.winterframework.logistics.web.service;

import java.util.List;

import com.winterframework.logistics.common.entity.Device;
import com.winterframework.logistics.common.entity.TransOrder;

public interface IDeviceWebService {
	Device findByNumber(String url, String findDeviceByNumber, String number);

	int updateDeviceStatus(String systemUrl, String updateDeviceStatusUrl, Device device);

	List<Device> findDeviceListByNumbers(String url, String findDeviceListByNumber, List<TransOrder> transOrders);

	Device findDeviceByNumber(String url, String findDeviceByNumber, String deviceNumber);
	
}
