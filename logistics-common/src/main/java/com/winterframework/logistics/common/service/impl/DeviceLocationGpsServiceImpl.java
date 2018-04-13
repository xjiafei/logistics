package com.winterframework.logistics.common.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winterframework.logistics.base.service.BaseServiceImpl;
import com.winterframework.logistics.common.dao.IDeviceLocationGpsDao;
import com.winterframework.logistics.common.entity.DeviceLocationGps;
import com.winterframework.logistics.common.service.IDeviceLocationGpsService;

@Service("deviceLocationGpsServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class DeviceLocationGpsServiceImpl extends BaseServiceImpl<IDeviceLocationGpsDao,DeviceLocationGps> implements IDeviceLocationGpsService {
	@Resource(name="deviceLocationGpsDaoImpl")
	IDeviceLocationGpsDao deviceLocationGpsDao;
	
	@Override
	protected IDeviceLocationGpsDao getEntityDao() {
		// TODO Auto-generated method stub
		return deviceLocationGpsDao;
	}


}
