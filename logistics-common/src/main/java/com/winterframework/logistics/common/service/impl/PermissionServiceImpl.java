package com.winterframework.logistics.common.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.winterframework.logistics.base.service.BaseServiceImpl;
import com.winterframework.logistics.common.dao.IPermissionDao;
import com.winterframework.logistics.common.entity.Permission;
import com.winterframework.logistics.common.service.IPermissionService;

@Service("permissionServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class PermissionServiceImpl extends BaseServiceImpl<IPermissionDao,Permission> implements IPermissionService {
	@Resource(name="permissionDaoImpl")
	IPermissionDao permissionDao;
	
	@Override
	protected IPermissionDao getEntityDao() {
		// TODO Auto-generated method stub
		return permissionDao;
	}

}
