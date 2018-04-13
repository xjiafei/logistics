package com.winterframework.logistics.common.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winterframework.logistics.base.service.BaseServiceImpl;
import com.winterframework.logistics.common.dao.IDeviceLocationBtsDao;
import com.winterframework.logistics.common.entity.DeviceLocationBts;
import com.winterframework.logistics.common.service.IDeviceLocationBtsService;

@Service("deviceLocationLbsServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class DeviceLocationBtsServiceImpl extends BaseServiceImpl<IDeviceLocationBtsDao,DeviceLocationBts> implements IDeviceLocationBtsService {
	@Resource(name="deviceLocationLbsDaoImpl")
	IDeviceLocationBtsDao deviceLocationLbsDao;
	
	@Override
	protected IDeviceLocationBtsDao getEntityDao() {
		// TODO Auto-generated method stub
		return deviceLocationLbsDao;
	}


}
