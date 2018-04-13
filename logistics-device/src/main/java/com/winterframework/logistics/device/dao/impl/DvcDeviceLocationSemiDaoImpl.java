package com.winterframework.logistics.device.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winterframework.logistics.common.dao.impl.DeviceLocationSemiDaoImpl;
import com.winterframework.logistics.common.entity.DeviceLocationSemi;
import com.winterframework.logistics.common.entity.DeviceLocationSemi.HandleStatus;
import com.winterframework.logistics.device.dao.IDvcDeviceLocationSemiDao;

@Repository("dvcDeviceLocationSemiDaoImpl")
public class DvcDeviceLocationSemiDaoImpl extends DeviceLocationSemiDaoImpl<DeviceLocationSemi> implements IDvcDeviceLocationSemiDao {
	@Override
	public List<String> getImeisByHandleStatus(List<HandleStatus> handleStatusList) throws Exception {
		return this.sqlSessionTemplate.selectList(getQueryPath("getImeisByHandleStatus"), getHandleStatusValueList(handleStatusList));
	}
	private List<Integer> getHandleStatusValueList(List<HandleStatus> statusList){
		List<Integer> list=new ArrayList<Integer>();
		for(HandleStatus status:statusList){
			list.add(status.getValue());
		}
		return list;
	}
	@Override
	public List<DeviceLocationSemi> getUnhandleListByImeiAndTimes(String imei, Long fromTime, Long toTime)
			throws Exception {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("imei", imei);
		map.put("fromTime", fromTime);
		map.put("toTime", toTime);
		return this.sqlSessionTemplate.selectList(getQueryPath("getUnhandleListByImeiAndTimes"), map);
	}
	@Override
	public DeviceLocationSemi getPrevious(Long id) throws Exception {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("id", id);
		return this.sqlSessionTemplate.selectOne(getQueryPath("getPrevious"), map);
	}
	
}
