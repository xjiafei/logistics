package com.winterframework.logistics.portal.dao;

import java.util.List;

import com.winterframework.logistics.base.dao.IBaseDao;
import com.winterframework.logistics.portal.entity.News;
import com.winterframework.modules.web.jsonresult.Pager;

public interface IPtlNewsDao extends IBaseDao<News> {

	List<News> selectListByAttribute(News news, Pager pager, int count) throws Exception;

	int getCount(News news) throws Exception;

}
