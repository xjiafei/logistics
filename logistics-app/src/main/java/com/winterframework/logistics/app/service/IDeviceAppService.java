package com.winterframework.logistics.app.service;

import com.winterframework.logistics.common.entity.Device;

public interface IDeviceAppService {

	Device findByNumber(String url, String findDeviceByNumberUrl, String deviceNumber);

}
