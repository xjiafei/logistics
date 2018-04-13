package com.winterframework.logistics.web.service;

import java.util.List;

import com.winterframework.logistics.common.entity.CompanyCity;

public interface ICompanyCityWebService {

	List<CompanyCity> getCompanyCityListByCompanys(String transportUrl, String findCompanyCityByIdUrl, Long companyId);

}
