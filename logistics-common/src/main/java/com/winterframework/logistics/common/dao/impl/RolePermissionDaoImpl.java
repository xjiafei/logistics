package com.winterframework.logistics.common.dao.impl;


import org.springframework.stereotype.Repository;

import com.winterframework.logistics.base.dao.BaseDaoImpl;
import com.winterframework.logistics.common.dao.IRolePermissionDao;
import com.winterframework.logistics.common.entity.RolePermission;
@Repository("rolePermissionDaoImpl")
public class RolePermissionDaoImpl<E extends RolePermission>  extends BaseDaoImpl<RolePermission> implements IRolePermissionDao{

}
