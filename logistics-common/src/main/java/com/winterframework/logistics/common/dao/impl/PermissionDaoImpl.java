package com.winterframework.logistics.common.dao.impl;

import org.springframework.stereotype.Repository;

import com.winterframework.logistics.base.dao.BaseDaoImpl;
import com.winterframework.logistics.common.dao.IPermissionDao;
import com.winterframework.logistics.common.entity.Permission;
@Repository("permissionDaoImpl")
public class PermissionDaoImpl<E extends Permission>  extends BaseDaoImpl<Permission> implements IPermissionDao{
	
}
