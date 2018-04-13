package com.winterframework.logistics.common.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.winterframework.logistics.base.service.BaseServiceImpl;
import com.winterframework.logistics.common.dao.IRolePermissionDao;
import com.winterframework.logistics.common.entity.RolePermission;
import com.winterframework.logistics.common.service.IRolePermissionService;

@Service("rolePermissionServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class RolePermissionServiceImpl extends BaseServiceImpl<IRolePermissionDao,RolePermission> implements IRolePermissionService {
	@Resource(name="rolePermissionDaoImpl")
	IRolePermissionDao rolePermissionDao;
	
	@Override
	protected IRolePermissionDao getEntityDao() {
		// TODO Auto-generated method stub
		return rolePermissionDao;
	}

}
