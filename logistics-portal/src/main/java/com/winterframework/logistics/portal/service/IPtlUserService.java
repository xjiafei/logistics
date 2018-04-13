package com.winterframework.logistics.portal.service;

import com.winterframework.logistics.base.service.IBaseService;
import com.winterframework.logistics.portal.entity.User;
import com.winterframework.logistics.portal.exception.PortalException;

public interface IPtlUserService  extends IBaseService<User>{
	User getByUserName(String userName) throws PortalException;
}
