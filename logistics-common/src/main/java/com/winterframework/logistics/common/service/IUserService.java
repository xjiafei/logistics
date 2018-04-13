package com.winterframework.logistics.common.service;

import java.util.List;

import com.winterframework.logistics.base.service.IBaseService;
import com.winterframework.logistics.common.entity.User;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.modules.web.jsonresult.Pager;

public interface IUserService extends IBaseService<User> {

	public User showUsers(User u);

	public User getByUserName(String userName) throws LmException;

	public List<User> getList(User u, Pager pager, int count);

	public int addUser(User u);

	public int delete(Long id);

	public int updateByAttribute(User data);

	public User findByAccount(String userName);

	public int getCountByAttribute(User u);

	public User getEntityByAttribute(User u);

}
