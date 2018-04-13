package com.winterframework.logistics.portal.dao.impl;

import org.springframework.stereotype.Repository;

import com.winterframework.logistics.base.dao.BaseDaoImpl;
import com.winterframework.logistics.portal.dao.IPtlBizTypeDao;
import com.winterframework.logistics.portal.entity.BizType;
@Repository("ptlBizTypeDaoImpl")
public class PtlBizTypeDaoImpl <E extends BizType>   extends BaseDaoImpl<BizType> implements IPtlBizTypeDao{

	@Override
	public BizType insertEntity(BizType bizType) throws Exception {
		// TODO Auto-generated method stub
		this.sqlSessionTemplate.insert(getQueryPath("insert"), bizType);
		return bizType;
	}

}
