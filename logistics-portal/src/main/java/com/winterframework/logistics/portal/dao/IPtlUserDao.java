package com.winterframework.logistics.portal.dao;

import com.winterframework.logistics.base.dao.IBaseDao;
import com.winterframework.logistics.portal.entity.User;

public interface IPtlUserDao extends IBaseDao<User>  {
	User getByUserName(String userName) throws Exception;
}