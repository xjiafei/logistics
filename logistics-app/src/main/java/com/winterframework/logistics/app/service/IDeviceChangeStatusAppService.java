package com.winterframework.logistics.app.service;

import com.winterframework.logistics.app.web.controller.dto.DeviceManageAppResponse;

public interface IDeviceChangeStatusAppService {

	int addDeviceRecord(String systemUrl, String deviceManageInsertUrl, DeviceManageAppResponse deviceManage);

}
