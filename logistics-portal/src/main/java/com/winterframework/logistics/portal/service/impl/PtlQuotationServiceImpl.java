package com.winterframework.logistics.portal.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.winterframework.logistics.base.service.BaseServiceImpl;
import com.winterframework.logistics.portal.dao.IPtlQuotationDao;
import com.winterframework.logistics.portal.entity.Quotation;
import com.winterframework.logistics.portal.service.IPtlQuotationService;

@Service("ptlQuotationServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class PtlQuotationServiceImpl  extends BaseServiceImpl<IPtlQuotationDao,Quotation> implements IPtlQuotationService{
	
	@Resource(name="ptlQuotationDaoImpl")
	private IPtlQuotationDao ptlQuotationDao;

	@Override
	protected IPtlQuotationDao getEntityDao() {
		// TODO Auto-generated method stub
		return ptlQuotationDao;
	}

}
