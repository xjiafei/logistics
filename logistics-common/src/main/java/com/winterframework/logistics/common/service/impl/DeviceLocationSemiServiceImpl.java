package com.winterframework.logistics.common.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winterframework.logistics.base.service.BaseServiceImpl;
import com.winterframework.logistics.common.dao.IDeviceLocationSemiDao;
import com.winterframework.logistics.common.entity.DeviceLocationSemi;
import com.winterframework.logistics.common.service.IDeviceLocationSemiService;

@Service("deviceLocationSemiServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class DeviceLocationSemiServiceImpl extends BaseServiceImpl<IDeviceLocationSemiDao,DeviceLocationSemi> implements IDeviceLocationSemiService {
	@Resource(name="deviceLocationSemiDaoImpl")
	IDeviceLocationSemiDao deviceLocationSemiDao;
	
	@Override
	protected IDeviceLocationSemiDao getEntityDao() {
		// TODO Auto-generated method stub
		return deviceLocationSemiDao;
	}


}
