package com.winterframework.logistics.portal.dao.impl;

import org.springframework.stereotype.Repository;

import com.winterframework.logistics.base.dao.BaseDaoImpl;
import com.winterframework.logistics.portal.dao.IPtlCompanyDao;
import com.winterframework.logistics.portal.entity.Company;
@Repository("ptlCompanyDaoImpl")
public class PtlCompanyDaoImpl  <E extends Company>   extends BaseDaoImpl<Company> implements IPtlCompanyDao{

}
