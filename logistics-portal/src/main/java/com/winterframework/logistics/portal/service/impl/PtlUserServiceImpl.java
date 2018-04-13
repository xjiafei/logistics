package com.winterframework.logistics.portal.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winterframework.logistics.base.enums.StatusCode;
import com.winterframework.logistics.base.service.BaseServiceImpl;
import com.winterframework.logistics.portal.dao.IPtlUserDao;
import com.winterframework.logistics.portal.entity.User;
import com.winterframework.logistics.portal.exception.PortalException;
import com.winterframework.logistics.portal.service.IPtlUserService;

@Service("ptlUserServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class PtlUserServiceImpl  extends BaseServiceImpl<IPtlUserDao,User> implements IPtlUserService{
	
	@Resource(name="ptlUserDaoImpl")
	private IPtlUserDao ptlUserDao;
	
	@Override
	protected IPtlUserDao getEntityDao() {
		return ptlUserDao;
	}
	@Override
	public User getByUserName(String userName) throws PortalException {
		try {
			return ptlUserDao.getByUserName(userName);
		} catch (Exception e) {
			throw new PortalException(StatusCode.DAO_ERROR, e);
		}
	}
}
