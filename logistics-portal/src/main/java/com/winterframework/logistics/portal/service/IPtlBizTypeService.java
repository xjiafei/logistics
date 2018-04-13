package com.winterframework.logistics.portal.service;

import java.util.List;

import com.winterframework.logistics.base.service.IBaseService;
import com.winterframework.logistics.portal.entity.BizType;
import com.winterframework.logistics.portal.exception.PortalException;

public interface IPtlBizTypeService extends IBaseService<BizType> {

	List<BizType> getEntitys(int status) throws PortalException;

	BizType getEntity(Long id) throws PortalException;

	BizType addBizType(BizType bizType)throws PortalException;

}
