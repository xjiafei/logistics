package com.winterframework.logistics.common.service;

import java.util.List;

import com.winterframework.logistics.base.service.IBaseService;
import com.winterframework.logistics.common.entity.DeviceChangeStatus;
import com.winterframework.modules.web.jsonresult.Pager;

public interface IDeviceChangeStatusService extends IBaseService<DeviceChangeStatus>  {

	List<DeviceChangeStatus> findListByAttribute(DeviceChangeStatus data, Pager pager, int count);

	int insert(DeviceChangeStatus data);

	int getCountByattribute(DeviceChangeStatus data);

}
