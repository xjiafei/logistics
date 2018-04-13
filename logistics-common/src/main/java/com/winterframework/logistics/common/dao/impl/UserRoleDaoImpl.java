package com.winterframework.logistics.common.dao.impl;

import org.springframework.stereotype.Repository;

import com.winterframework.logistics.base.dao.BaseDaoImpl;
import com.winterframework.logistics.common.dao.IUserRoleDao;
import com.winterframework.logistics.common.entity.UserRole;
@Repository("userRoleDaoImpl")
public class UserRoleDaoImpl<E extends UserRole>  extends BaseDaoImpl<UserRole> implements IUserRoleDao{


}
