package com.winterframework.logistics.portal.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winterframework.logistics.base.dao.BaseDaoImpl;
import com.winterframework.logistics.portal.dao.IPtlUserDao;
import com.winterframework.logistics.portal.entity.User;
@Repository("ptlUserDaoImpl")
public class PtlUserDaoImpl <E extends User> extends BaseDaoImpl<User> implements IPtlUserDao {
	@Override
	public User getByUserName(String userName) throws Exception {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("userName", userName);
		return sqlSessionTemplate.selectOne(getQueryPath("getByUserName"), map);
	}
}
