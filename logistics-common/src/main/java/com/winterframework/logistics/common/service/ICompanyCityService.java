package com.winterframework.logistics.common.service;

import java.util.List;

import com.winterframework.logistics.base.service.IBaseService;
import com.winterframework.logistics.common.entity.CompanyCity;

public interface ICompanyCityService extends IBaseService<CompanyCity>{
	
	public CompanyCity findCompanyCityByAttribute(CompanyCity companyCity);
	
	public List<CompanyCity> findCompanyCityListByAttribute(CompanyCity companyCity);

	public int insertEntitys(List<CompanyCity> data);

	public int removeCitys(Long companyId);
}
