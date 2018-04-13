package com.winterframework.logistics.portal.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winterframework.logistics.base.enums.StatusCode;
import com.winterframework.logistics.base.model.Context;
import com.winterframework.logistics.base.service.BaseServiceImpl;
import com.winterframework.logistics.portal.dao.IPtlTestDao;
import com.winterframework.logistics.portal.entity.Test;
import com.winterframework.logistics.portal.exception.PortalException;
import com.winterframework.logistics.portal.service.IPtlTestService;

@Service("ptlTestServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class PtlTestServiceImpl extends BaseServiceImpl<IPtlTestDao,Test> implements IPtlTestService {
	@Resource(name="ptlTestDaoImpl")
	private IPtlTestDao ptlTestDao;
	
	@Override
	protected IPtlTestDao getEntityDao() {
		// TODO Auto-generated method stub
		return ptlTestDao;
	}
	@Override
	public void test(Context ctx) throws PortalException {
		try {
			getEntityDao().test(0L);
		}catch(Exception e){
			log.error("test error. id="+0L);
			throw new PortalException(StatusCode.DAO_ERROR,e);
		}		
	}

}
