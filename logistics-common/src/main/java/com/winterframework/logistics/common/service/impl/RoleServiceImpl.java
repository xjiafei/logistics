package com.winterframework.logistics.common.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.winterframework.logistics.base.service.BaseServiceImpl;
import com.winterframework.logistics.common.dao.IRoleDao;
import com.winterframework.logistics.common.entity.Role;
import com.winterframework.logistics.common.service.IRoleService;

@Service("roleServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl extends BaseServiceImpl<IRoleDao,Role> implements IRoleService {
	@Resource(name="roleDaoImpl")
	IRoleDao roleDao;
	
	@Override
	protected IRoleDao getEntityDao() {
		// TODO Auto-generated method stub
		return roleDao;
	}

}
