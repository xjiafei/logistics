package com.winterframework.logistics.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.winterframework.logistics.base.service.BaseServiceImpl;
import com.winterframework.logistics.common.dao.IDeviceLocationDao;
import com.winterframework.logistics.common.entity.DeviceLocation;
import com.winterframework.logistics.common.service.IDeviceLocationService;

@Service("deviceLocationServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class DeviceLocationServiceImpl extends BaseServiceImpl<IDeviceLocationDao,DeviceLocation> implements IDeviceLocationService {
	@Resource(name="deviceLocationDaoImpl")
	IDeviceLocationDao deviceLocationDao;
	
	@Override
	protected IDeviceLocationDao getEntityDao() {
		// TODO Auto-generated method stub
		return deviceLocationDao;
	}

	@Override
	public Long addDeviceLocation(DeviceLocation deviceLocation) {
		// TODO Auto-generated method stub
		return deviceLocationDao.insertOrder(deviceLocation);
	}
	
	@Override
	public List<DeviceLocation> findByAttribute(List<Long> deviceIdList) {
		// TODO Auto-generated method stub
		List<DeviceLocation> deviceLocation=null;
		try {
			return deviceLocationDao.findListByAttribute(deviceIdList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deviceLocation;
	}
	
	@Override
	public List<DeviceLocation> findByOrderId(Long orderId) {
		// TODO Auto-generated method stub
		List<DeviceLocation> deviceLocations=null;
		try {
			return deviceLocationDao.getListByOrderId(orderId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deviceLocations;
	}

	@Override
	public DeviceLocation findNewDeviceLocationById(Long orderId) {
		// TODO Auto-generated method stub
		return deviceLocationDao.findNewDeviceLocation(orderId);
	}

	@Override
	public List<DeviceLocation> findNewDeviceLocations(List<Long> orderIds) {
		// TODO Auto-generated method stub
		return deviceLocationDao.findNewDeviceLocations(orderIds);
	}
	

}
