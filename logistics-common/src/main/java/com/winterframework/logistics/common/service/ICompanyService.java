package com.winterframework.logistics.common.service;

import java.util.List;

import com.winterframework.logistics.base.service.IBaseService;
import com.winterframework.logistics.common.entity.Company;

public interface ICompanyService extends IBaseService<Company>{
	
	public Company findCompanyByAttribute(Company company);
	
	public List<Company> findCompanyListByIds(List<Long> ids);
	
	public Long addCompany(Company company);

	public List<Company> findCompanyByAttributes(Company data);


}
