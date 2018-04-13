package com.winterframework.logistics.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winterframework.logistics.base.enums.StatusCode;
import com.winterframework.logistics.base.service.BaseServiceImpl;
import com.winterframework.logistics.portal.dao.IPtlBizDao;
import com.winterframework.logistics.portal.entity.Biz;
import com.winterframework.logistics.portal.entity.BizType;
import com.winterframework.logistics.portal.exception.PortalException;
import com.winterframework.logistics.portal.service.IPtlBizService;

@Service("ptlBizServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class PtlBizServiceImpl extends BaseServiceImpl<IPtlBizDao,Biz> implements IPtlBizService {
	
	@Resource(name="ptlBizDaoImpl")
	private IPtlBizDao ptlBizDao;
	
	@Override
	protected IPtlBizDao getEntityDao() {
		// TODO Auto-generated method stub
		return ptlBizDao;
	}

	@Override
	public Biz getEntity(Long id) throws PortalException {
		// TODO Auto-generated method stub
		try {
			return ptlBizDao.getById(id);
		} catch (Exception e) {
			// TODO: handle exception
			throw new PortalException(StatusCode.DAO_ERROR, e);
		}
		
	}

	@Override
	public List<Biz> getEntitysByBizTypes(List<BizType> bizTypes) throws PortalException {
		// TODO Auto-generated method stub
		List<Long> bizTypeIds=new ArrayList<Long>();
		for (BizType bizType : bizTypes) {
			bizTypeIds.add(bizType.getId());
		}
		try {
			return ptlBizDao.getByIds(bizTypeIds);
		} catch (Exception e) {
			// TODO: handle exception
			throw new PortalException(StatusCode.DAO_ERROR, e);
		}
		
	}

	@Override
	public int addBiz(Biz biz) throws PortalException {
		// TODO Auto-generated method stub
		try {
			return ptlBizDao.insert(biz);
		} catch (Exception e) {
			// TODO: handle exception
			throw new PortalException(StatusCode.DAO_ERROR, e);
		}
		
	}

}
