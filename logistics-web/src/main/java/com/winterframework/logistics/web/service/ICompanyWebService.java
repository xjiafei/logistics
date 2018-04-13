package com.winterframework.logistics.web.service;

import java.util.List;

import com.winterframework.logistics.common.entity.Company;

public interface ICompanyWebService {
	Company getCompanyByAttribute(String transportUrl, String findCompanyIdByNameUrl, Company company);

	List<Company> getCompanyByIds(String systemUrl, String findCompanysByIds, List<Long> companyIds);

}
