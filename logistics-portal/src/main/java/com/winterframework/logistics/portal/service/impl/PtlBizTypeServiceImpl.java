package com.winterframework.logistics.portal.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winterframework.logistics.base.enums.StatusCode;
import com.winterframework.logistics.base.service.BaseServiceImpl;
import com.winterframework.logistics.portal.dao.IPtlBizTypeDao;
import com.winterframework.logistics.portal.entity.BizType;
import com.winterframework.logistics.portal.exception.PortalException;
import com.winterframework.logistics.portal.service.IPtlBizTypeService;

@Service("ptlBizTypeServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class PtlBizTypeServiceImpl extends BaseServiceImpl<IPtlBizTypeDao,BizType> implements IPtlBizTypeService {
	
	@Resource(name="ptlBizTypeDaoImpl")
	private IPtlBizTypeDao ptlBizTypeDao;
	
	@Override
	protected IPtlBizTypeDao getEntityDao() {
		// TODO Auto-generated method stub
		return ptlBizTypeDao;
	}

	@Override
	public List<BizType> getEntitys(int status) throws PortalException {
		// TODO Auto-generated method stub
		BizType bizType=new BizType();
		bizType.setStatus(status);
		try {
			return ptlBizTypeDao.selectListByAttribute(bizType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new PortalException(StatusCode.DAO_ERROR, e);
		}
	}

	@Override
	public BizType getEntity(Long id) throws PortalException {
		// TODO Auto-generated method stub
		try {
			return ptlBizTypeDao.getById(id);
		} catch (Exception e) {
			// TODO: handle exception
			throw new PortalException(StatusCode.DAO_ERROR, e);
		}
		
	}

	@Override
	public BizType addBizType(BizType bizType) throws PortalException {
		// TODO Auto-generated method stub
		try {
			return ptlBizTypeDao.insertEntity(bizType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new PortalException(StatusCode.DAO_ERROR, e);
		}
	}

}
