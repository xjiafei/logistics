package com.winterframework.logistics.common.dao;


import java.util.List;

import com.winterframework.logistics.base.dao.IBaseDao;
import com.winterframework.logistics.common.entity.CompanyCity;

public interface ICompanyCityDao extends IBaseDao<CompanyCity>{

	int insertEntitys(List<CompanyCity> companyCitys);

	int removeCitys(Long companyId);
}