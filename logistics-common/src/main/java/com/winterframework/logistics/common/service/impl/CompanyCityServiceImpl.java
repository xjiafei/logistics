package com.winterframework.logistics.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winterframework.logistics.base.service.BaseServiceImpl;
import com.winterframework.logistics.common.dao.ICompanyCityDao;
import com.winterframework.logistics.common.entity.CompanyCity;
import com.winterframework.logistics.common.service.ICompanyCityService;
@Service("companyCityServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class CompanyCityServiceImpl extends BaseServiceImpl<ICompanyCityDao,CompanyCity> implements ICompanyCityService {
	
	@Resource(name="companyCityDaoImpl")
	ICompanyCityDao iCompanyCityDao;
	@Override
	protected ICompanyCityDao getEntityDao() {
		// TODO Auto-generated method stub
		return iCompanyCityDao;
	}
	@Override
	public CompanyCity findCompanyCityByAttribute(CompanyCity companyCity) {
		// TODO Auto-generated method stub
		
		try {
			return iCompanyCityDao.selectByAttribute(companyCity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return companyCity;
	}
	@Override
	public List<CompanyCity> findCompanyCityListByAttribute(CompanyCity companyCity) {
		// TODO Auto-generated method stub
		try {
			return iCompanyCityDao.selectListByAttribute(companyCity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public int insertEntitys(List<CompanyCity> companyCitys) {
		// TODO Auto-generated method stub
		return iCompanyCityDao.insertEntitys(companyCitys);
	}
	@Override
	public int removeCitys(Long companyId) {
		// TODO Auto-generated method stub
		return iCompanyCityDao.removeCitys(companyId);
	}

}
