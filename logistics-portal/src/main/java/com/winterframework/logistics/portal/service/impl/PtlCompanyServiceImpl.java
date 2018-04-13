package com.winterframework.logistics.portal.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winterframework.logistics.base.enums.StatusCode;
import com.winterframework.logistics.base.service.BaseServiceImpl;
import com.winterframework.logistics.portal.dao.IPtlCompanyDao;
import com.winterframework.logistics.portal.entity.Company;
import com.winterframework.logistics.portal.exception.PortalException;
import com.winterframework.logistics.portal.service.IPtlCompanyService;

@Service("ptlCompanyServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class PtlCompanyServiceImpl extends BaseServiceImpl<IPtlCompanyDao,Company> implements IPtlCompanyService {
	
	@Resource(name="ptlCompanyDaoImpl")
	private IPtlCompanyDao ptlCompanyDao;
	
	@Override
	protected IPtlCompanyDao getEntityDao() {
		// TODO Auto-generated method stub
		return ptlCompanyDao;
	}

	@Override
	public Company getEntity() throws PortalException{
		// TODO Auto-generated method stub
		try {
			return ptlCompanyDao.getById(1L);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new PortalException(StatusCode.DAO_ERROR,e);
		}
	}

}
