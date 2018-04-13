package com.winterframework.logistics.common.dao;

import java.util.List;

import com.winterframework.logistics.base.dao.IBaseDao;
import com.winterframework.logistics.common.entity.DeviceChangeStatus;
import com.winterframework.modules.web.jsonresult.Pager;

public interface IDeviceChangeStatusDao  extends IBaseDao<DeviceChangeStatus>{

	int insertOne(DeviceChangeStatus data);

	int getCountByAttribute(DeviceChangeStatus data);

	List<DeviceChangeStatus> selectListByAttribute(DeviceChangeStatus deviceManage, Pager pager, int count);

}
