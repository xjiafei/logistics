package com.winterframework.logistics.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.winterframework.logistics.base.dao.BaseDaoImpl;
import com.winterframework.logistics.common.dao.IDeviceLocationDao;
import com.winterframework.logistics.common.entity.DeviceLocation;
@Repository("deviceLocationDaoImpl")
public class DeviceLocationDaoImpl<E extends DeviceLocation>  extends BaseDaoImpl<DeviceLocation> implements IDeviceLocationDao{

	@Override
	public Long insertOrder(DeviceLocation deviceLocation) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.insert(getQueryPath("insert"),deviceLocation);
		return deviceLocation.getId();
	}
	

	@Override
	public List<DeviceLocation> findListByAttribute(List<Long> deviceIdList) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(getQueryPath("getListByArrtibute"), deviceIdList);
	}

	public List<DeviceLocation> findNewDeviceLocations(List<Long> orderIds){
		return sqlSessionTemplate.selectList(getQueryPath("getNewLocationList"), orderIds);
	}
	
	@Override
	public DeviceLocation findNewDeviceLocation(Long orderId) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(getQueryPath("getNewLocation"), orderId);
	}


	@Override
	public List<DeviceLocation> getListByOrderId(Long orderId) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(getQueryPath("getListByOrderId"), orderId);
	}


	



}
