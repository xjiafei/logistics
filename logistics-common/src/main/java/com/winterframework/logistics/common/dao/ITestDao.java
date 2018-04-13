package com.winterframework.logistics.common.dao;

import com.winterframework.logistics.base.dao.IBaseDao;
import com.winterframework.logistics.common.entity.Test;

public interface ITestDao  extends IBaseDao<Test>{ 
	void test(Long userId);
}
