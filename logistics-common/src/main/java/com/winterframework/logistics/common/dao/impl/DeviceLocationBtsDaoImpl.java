package com.winterframework.logistics.common.dao.impl;

import org.springframework.stereotype.Repository;

import com.winterframework.logistics.base.dao.BaseDaoImpl;
import com.winterframework.logistics.common.dao.IDeviceLocationBtsDao;
import com.winterframework.logistics.common.entity.DeviceLocationBts;
@Repository("deviceLocationLbsDaoImpl")
public class DeviceLocationBtsDaoImpl<E extends DeviceLocationBts>  extends BaseDaoImpl<DeviceLocationBts> implements IDeviceLocationBtsDao{




}
