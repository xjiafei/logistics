package com.winterframework.logistics.portal.dao;

import com.winterframework.logistics.base.dao.IBaseDao;
import com.winterframework.logistics.portal.entity.Test;

public interface IPtlTestDao  extends IBaseDao<Test> {
	void test(Long id) throws Exception;
	
}