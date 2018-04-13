package com.winterframework.logistics.common.dao.impl;


import org.springframework.stereotype.Repository;

import com.winterframework.logistics.base.dao.BaseDaoImpl;
import com.winterframework.logistics.common.dao.IDeviceBatteryDao;
import com.winterframework.logistics.common.entity.DeviceBattery;
@Repository("deviceBatteryDaoImpl")
public class DeviceBatteryDaoImpl<E extends DeviceBattery>   extends BaseDaoImpl<DeviceBattery> implements IDeviceBatteryDao{


	

}
