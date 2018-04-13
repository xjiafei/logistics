package com.winterframework.logistics.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.winterframework.logistics.base.dao.BaseDaoImpl;
import com.winterframework.logistics.common.dao.ICompanyCityDao;
import com.winterframework.logistics.common.entity.CompanyCity;
@Repository("companyCityDaoImpl")
public class CompanyCityDaoImpl <E extends CompanyCity> extends BaseDaoImpl<CompanyCity> implements ICompanyCityDao{

	@Override
	public int insertEntitys(List<CompanyCity> companyCitys) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.insert(getQueryPath("insertLists"), companyCitys);
	}

	@Override
	public int removeCitys(Long companyId) {
		// TODO Auto-generated method stub
		if(companyId!=null) {
			return sqlSessionTemplate.delete(getQueryPath("deleteCity"), companyId);
		}
		return 0;
	}

}
