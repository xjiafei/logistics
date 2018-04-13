package com.winterframework.logistics.portal.service;

import com.winterframework.logistics.base.service.IBaseService;
import com.winterframework.logistics.portal.entity.Company;
import com.winterframework.logistics.portal.exception.PortalException;

public interface IPtlCompanyService  extends IBaseService<Company>{

	Company getEntity() throws PortalException;

}
