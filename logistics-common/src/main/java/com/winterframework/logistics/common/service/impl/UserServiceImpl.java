package com.winterframework.logistics.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.winterframework.logistics.base.enums.StatusCode;
import com.winterframework.logistics.base.service.BaseServiceImpl;
import com.winterframework.logistics.common.dao.IUserDao;
import com.winterframework.logistics.common.entity.User;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.common.service.IUserService;
import com.winterframework.modules.web.jsonresult.Pager;

@Service("userServiceImpl")
public class UserServiceImpl extends BaseServiceImpl<IUserDao, User> implements IUserService {
	@Resource(name = "userDaoImpl")
	private IUserDao userDao;

	@Override
	public User showUsers(User u) {
		// TODO Auto-generated method stub
		try {
			return userDao.selectByAttribute(u);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}

	@Override
	protected IUserDao getEntityDao() {
		// TODO Auto-generated method stub
		return userDao;
	}

	@Override
	public User getByUserName(String userName) throws LmException {
		try {
			return userDao.getByUserName(userName);
		} catch (Exception e) {
			throw new LmException(StatusCode.DAO_ERROR, e);
		}
	}

	@Override
	public List<User> getList(User u,Pager pager, int count) {
		// TODO Auto-generated method stub
		try {
			return userDao.selectListByAttribute(u,pager,count);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int addUser(User u) {
		// TODO Auto-generated method stub
		return userDao.insertUser(u);
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return userDao.delete(id);
	}

	@Override
	public int updateByAttribute(User user) {
		// TODO Auto-generated method stub
		return userDao.update(user);
	}

	@Override
	public User findByAccount(String userName) {
		// TODO Auto-generated method stub
		User u=new User();
		u.setUserName(userName);
		try {
			return userDao.selectByAttribute(u);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getCountByAttribute(User u) {
		// TODO Auto-generated method stub
		return userDao.getCountByAttribute(u);
	}

	@Override
	public User getEntityByAttribute(User u) {
		// TODO Auto-generated method stub
		try {
			return userDao.selectByAttribute(u);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}


}
