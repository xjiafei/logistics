package com.winterframework.logistics.common.dao;

import java.util.List;

import com.winterframework.logistics.base.dao.IBaseDao;
import com.winterframework.logistics.common.entity.User;
import com.winterframework.modules.web.jsonresult.Pager;

public interface IUserDao  extends IBaseDao<User>{
	User getByUserName(String userName) throws Exception;

	int insertUser(User u);

	int getCountByAttribute(User u);

	List<User> selectListByAttribute(User u, Pager pager, int count);

}