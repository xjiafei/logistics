package com.winterframework.logistics.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.winterframework.logistics.base.dao.BaseDaoImpl;
import com.winterframework.logistics.common.dao.ICompanyDao;
import com.winterframework.logistics.common.entity.Company;
@Repository("companyDaoImpl")
public class CompanyDaoImpl  <E extends Company> extends BaseDaoImpl<Company> implements ICompanyDao{

	@Override
	public List<Company> findListByIds(List<Long> ids) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(getQueryPath("getByIds"),ids);
	}

	@Override
	public Long insertCompany(Company company) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.insert(getQueryPath("insert"), company);
		return company.getId();
	}



}
