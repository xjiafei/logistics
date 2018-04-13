package com.winterframework.logistics.common.dao;

import java.util.List;

import com.winterframework.logistics.base.dao.IBaseDao;
import com.winterframework.logistics.common.entity.Company;

public interface ICompanyDao extends IBaseDao<Company>{
    List<Company> findListByIds(List<Long> ids);

	Long insertCompany(Company company);

}