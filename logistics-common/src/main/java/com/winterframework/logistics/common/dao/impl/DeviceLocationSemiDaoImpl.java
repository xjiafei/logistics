package com.winterframework.logistics.common.dao.impl;

import org.springframework.stereotype.Repository;

import com.winterframework.logistics.base.dao.BaseDaoImpl;
import com.winterframework.logistics.common.dao.IDeviceLocationSemiDao;
import com.winterframework.logistics.common.entity.DeviceLocationSemi;
@Repository("deviceLocationSemiDaoImpl")
public class DeviceLocationSemiDaoImpl<E extends DeviceLocationSemi>  extends BaseDaoImpl<DeviceLocationSemi> implements IDeviceLocationSemiDao{




}
