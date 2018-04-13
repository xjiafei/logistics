package com.winterframework.logistics.device.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winterframework.logistics.common.dao.impl.TransOrderDaoImpl;
import com.winterframework.logistics.common.entity.TransOrder;
import com.winterframework.logistics.device.dao.IDvcTransOrderDao;

@Repository("dvcTransOrderDaoImpl")
public class DvcTransOrderDaoImpl extends TransOrderDaoImpl<TransOrder> implements IDvcTransOrderDao {
	
	@Override
	public TransOrder getByDeviceNumberAndTime(String deviceNumber, Long time) throws Exception {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("deviceNumber", deviceNumber);
		map.put("time", time);
		return this.sqlSessionTemplate.selectOne(getQueryPath("getByDeviceNumberAndTime"), map);
	}
	
}
