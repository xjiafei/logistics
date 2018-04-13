package com.winterframework.logistics.common.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.winterframework.logistics.base.service.BaseServiceImpl;
import com.winterframework.logistics.common.dao.ICompanyDao;
import com.winterframework.logistics.common.entity.Company;
import com.winterframework.logistics.common.service.ICompanyService;
@Service("companyServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class CompanyServiceImpl extends BaseServiceImpl<ICompanyDao,Company> implements ICompanyService {
	@Resource(name="companyDaoImpl")
	ICompanyDao iCompanyDao;
	@Override
	protected ICompanyDao getEntityDao() {
		// TODO Auto-generated method stub
		return iCompanyDao;
	}
	
	@Override
	public Company findCompanyByAttribute(Company company) {
		// TODO Auto-generated method stub
		try {
			return iCompanyDao.selectByAttribute(company);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return company;
	}

	@Override
	public List<Company> findCompanyListByIds(List<Long> ids) {
		// TODO Auto-generated method stub
		return iCompanyDao.findListByIds(ids);
	}

	@Override
	public Long addCompany(Company company) {
		// TODO Auto-generated method stub
		return iCompanyDao.insertCompany(company);
	}

	@Override
	public List<Company> findCompanyByAttributes(Company data) {
		// TODO Auto-generated method stub
		try {
			return iCompanyDao.selectListByAttribute(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	

}
