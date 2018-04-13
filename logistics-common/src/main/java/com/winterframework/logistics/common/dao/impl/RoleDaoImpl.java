package com.winterframework.logistics.common.dao.impl;

import org.springframework.stereotype.Repository;

import com.winterframework.logistics.base.dao.BaseDaoImpl;
import com.winterframework.logistics.common.dao.IRoleDao;
import com.winterframework.logistics.common.entity.Role;
@Repository("roleDaoImpl")
public class RoleDaoImpl<E extends Role> extends BaseDaoImpl<Role> implements IRoleDao{


}
