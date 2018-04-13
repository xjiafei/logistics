package com.winterframework.logistics.device.service;

import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.common.service.IDeviceService;

public interface IDvcDeviceService extends IDeviceService{
	/**
	 * 设备离线状态更新
	 * @param imei
	 * @throws LmException
	 */
	void onffRefresh(String imei) throws LmException;

}
