package com.winterframework.logistics.portal.dao.impl;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winterframework.logistics.base.dao.BaseDaoImpl;
import com.winterframework.logistics.portal.dao.IPtlTestDao;
import com.winterframework.logistics.portal.entity.Test;
@Repository("ptlTestDaoImpl")
public class PtlTestDaoImpl<E extends Test>   extends BaseDaoImpl<Test> implements IPtlTestDao{

	@Override
	public void test(Long id) throws Exception {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("id", id);
		this.sqlSessionTemplate.insert(getQueryPath("test"), map);
	}
}
