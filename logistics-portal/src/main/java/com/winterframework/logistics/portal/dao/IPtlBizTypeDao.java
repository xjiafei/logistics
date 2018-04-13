package com.winterframework.logistics.portal.dao;

import com.winterframework.logistics.base.dao.IBaseDao;
import com.winterframework.logistics.portal.entity.BizType;

public interface IPtlBizTypeDao extends IBaseDao<BizType> {

	BizType insertEntity(BizType bizType)throws Exception;
   
}