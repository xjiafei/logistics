package com.winterframework.logistics.common.service;

import com.winterframework.logistics.base.service.IBaseService;
import com.winterframework.logistics.common.entity.Test;

public interface ITestService extends IBaseService<Test> {
	void test(Long userId);
}
