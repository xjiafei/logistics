package com.winterframework.logistics.portal.service;

import java.util.List;

import com.winterframework.logistics.base.service.IBaseService;
import com.winterframework.logistics.portal.entity.Biz;
import com.winterframework.logistics.portal.entity.BizType;
import com.winterframework.logistics.portal.exception.PortalException;

public interface IPtlBizService extends IBaseService<Biz> {

	Biz getEntity(Long id) throws PortalException;

	List<Biz> getEntitysByBizTypes(List<BizType> bizTypes) throws PortalException;

	int addBiz(Biz biz) throws PortalException;

}
