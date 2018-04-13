package com.winterframework.logistics.common.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winterframework.logistics.base.dao.BaseDaoImpl;
import com.winterframework.logistics.common.dao.ITestDao;
import com.winterframework.logistics.common.entity.Test;
 
@Repository("testDaoImpl")
public class TestDaoImpl<E extends Test> extends BaseDaoImpl<Test> implements ITestDao{ 
	public void test(Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId); 
		sqlSessionTemplate.selectList(getQueryPath("getTest"), map);
	}
}
