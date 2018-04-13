package com.winterframework.logistics.web.service;

import java.util.List;

import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.common.entity.DeviceChangeStatus;
import com.winterframework.logistics.web.transport.controller.dto.DeviceManageWebResponse;
import com.winterframework.modules.web.jsonresult.Pager;

public interface IDeviceChangeStatusWebService {

	Response<List<DeviceChangeStatus>> showList(String systemUrl, String deviceMangeFindListUrl,
			DeviceChangeStatus deviceManage, Pager pager);

	int addDeviceRecord(String systemUrl, String deviceManageInsertUrl, DeviceManageWebResponse deviceManage);

}
