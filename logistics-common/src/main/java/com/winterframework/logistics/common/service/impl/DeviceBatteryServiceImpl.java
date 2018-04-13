package com.winterframework.logistics.common.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.winterframework.logistics.base.service.BaseServiceImpl;
import com.winterframework.logistics.common.dao.IDeviceBatteryDao;
import com.winterframework.logistics.common.entity.DeviceBattery;
import com.winterframework.logistics.common.service.IDeviceBatteryService;

@Service("deviceBatteryServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class DeviceBatteryServiceImpl extends BaseServiceImpl<IDeviceBatteryDao,DeviceBattery> implements IDeviceBatteryService {
	@Resource(name="deviceBatteryDaoImpl")
	IDeviceBatteryDao deviceBatteryDao;
	
	@Override
	protected IDeviceBatteryDao getEntityDao() {
		// TODO Auto-generated method stub
		return deviceBatteryDao;
	}

}
