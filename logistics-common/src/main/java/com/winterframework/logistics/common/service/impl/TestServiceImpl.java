package com.winterframework.logistics.common.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winterframework.logistics.base.service.BaseServiceImpl;
import com.winterframework.logistics.common.dao.ITestDao;
import com.winterframework.logistics.common.entity.Test;
import com.winterframework.logistics.common.service.ITestService;

@Service("testServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class TestServiceImpl  extends BaseServiceImpl<ITestDao,Test> implements ITestService {
	@Resource(name="testDaoImpl")
	private ITestDao testDao;
	
	@Override
	protected ITestDao getEntityDao() {
		// TODO Auto-generated method stub
		return testDao;
	}
	public void test(Long userId) {
		testDao.test(userId);
	}
}
