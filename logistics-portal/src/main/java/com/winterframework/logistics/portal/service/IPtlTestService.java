package com.winterframework.logistics.portal.service;


import com.winterframework.logistics.base.model.Context;
import com.winterframework.logistics.base.service.IBaseService;
import com.winterframework.logistics.portal.entity.Test;
import com.winterframework.logistics.portal.exception.PortalException;

public interface IPtlTestService extends IBaseService<Test> {
	void test(Context ctx) throws PortalException;
}
