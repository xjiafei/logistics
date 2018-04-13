package com.winterframework.logistics.device.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winterframework.logistics.common.dao.impl.DeviceLocationBtsDaoImpl;
import com.winterframework.logistics.common.entity.DeviceLocationBts;
import com.winterframework.logistics.device.dao.IDvcDeviceLocationBtsDao;

@Repository("dvcDeviceLocationLbsDaoImpl")
public class DvcDeviceLocationBtsDaoImpl extends DeviceLocationBtsDaoImpl<DeviceLocationBts> implements IDvcDeviceLocationBtsDao {
	
	@Override
	public List<DeviceLocationBts> getListByHandleflagAndTimes(Integer handleFlag,Long fromTime, Long toTime) throws Exception {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("handleFlag", handleFlag);
		map.put("fromTime", fromTime);
		map.put("toTime", toTime);
		return this.sqlSessionTemplate.selectList(getQueryPath("getListByHandleflagAndTimes"), map);
	}
	@Override
	public List<DeviceLocationBts> getListByImeiAndTimes(String imei,Long fromTime, Long toTime) throws Exception {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("imei", imei);
		map.put("fromTime", fromTime);
		map.put("toTime", toTime);
		return this.sqlSessionTemplate.selectList(getQueryPath("getListByImeiAndTimes"), map);
	}
	
}
