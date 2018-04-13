package com.winterframework.logistics.common.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.winterframework.logistics.base.service.BaseServiceImpl;
import com.winterframework.logistics.common.dao.IUserRoleDao;
import com.winterframework.logistics.common.entity.UserRole;
import com.winterframework.logistics.common.service.IUserRoleService;

@Service("userRoleServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class UserRoleServiceImpl extends BaseServiceImpl<IUserRoleDao,UserRole> implements IUserRoleService {
	@Resource(name="userRoleDaoImpl")
	IUserRoleDao userRoleDao;
	
	@Override
	protected IUserRoleDao getEntityDao() {
		// TODO Auto-generated method stub
		return userRoleDao;
	}

}
