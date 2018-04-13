package com.winterframework.logistics.common.dao.impl;

import org.springframework.stereotype.Repository;

import com.winterframework.logistics.base.dao.BaseDaoImpl;
import com.winterframework.logistics.common.dao.IDeviceLocationGpsDao;
import com.winterframework.logistics.common.entity.DeviceLocationGps;
@Repository("deviceLocationGpsDaoImpl")
public class DeviceLocationGpsDaoImpl<E extends DeviceLocationGps>  extends BaseDaoImpl<DeviceLocationGps> implements IDeviceLocationGpsDao{




}
