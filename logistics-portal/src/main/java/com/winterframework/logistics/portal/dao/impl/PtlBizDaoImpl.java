package com.winterframework.logistics.portal.dao.impl;

import org.springframework.stereotype.Repository;

import com.winterframework.logistics.base.dao.BaseDaoImpl;
import com.winterframework.logistics.portal.dao.IPtlBizDao;
import com.winterframework.logistics.portal.entity.Biz;
@Repository("ptlBizDaoImpl")
public class PtlBizDaoImpl <E extends Biz>   extends BaseDaoImpl<Biz> implements IPtlBizDao{

}
