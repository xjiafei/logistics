package com.winterframework.logistics.portal.service;

import java.util.List;

import com.winterframework.logistics.base.service.IBaseService;
import com.winterframework.logistics.portal.entity.News;
import com.winterframework.logistics.portal.exception.PortalException;
import com.winterframework.modules.web.jsonresult.Pager;

public interface IPtlNewsService extends IBaseService<News> {

	List<News> getEntitys(int status, Pager pager, int count) throws PortalException;

	int getCount(int status) throws PortalException;

	News getEntity(Long id) throws PortalException;

	int addNews(News news) throws PortalException;

	int updateNews(News news) throws PortalException;

}
