package com.winterframework.logistics.portal.service.impl;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.winterframework.logistics.base.enums.StatusCode;
import com.winterframework.logistics.base.service.BaseServiceImpl;
import com.winterframework.logistics.portal.dao.IPtlNewsDao;
import com.winterframework.logistics.portal.entity.News;
import com.winterframework.logistics.portal.exception.PortalException;
import com.winterframework.logistics.portal.service.IPtlNewsService;
import com.winterframework.modules.web.jsonresult.Pager;

@Service("ptlNewsServiceImpl")
public class PtlNewsServiceImpl extends BaseServiceImpl<IPtlNewsDao, News> implements IPtlNewsService {

	@Resource(name = "ptlNewsDaoImpl")
	IPtlNewsDao ptlNewsDao;

	@Override
	protected IPtlNewsDao getEntityDao() {
		// TODO Auto-generated method stub
		return ptlNewsDao;
	}

	@Override
	public List<News> getEntitys(int status,Pager pager,int count) throws PortalException {
		// TODO Auto-generated method stub
		News news = new News();
		news.setStatus(status);
		try {
			return ptlNewsDao.selectListByAttribute(news, pager,count);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new PortalException(StatusCode.DAO_ERROR, e);
		}

	}

	@Override
	public int getCount(int status) throws PortalException {
		// TODO Auto-generated method stub
		News news = new News();
		news.setStatus(status);
		try {
			return Optional.ofNullable(ptlNewsDao.getCount(news)).orElse(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new PortalException(StatusCode.DAO_ERROR, e);
		}
		
	}

	@Override
	public News getEntity(Long id) throws PortalException {
		// TODO Auto-generated method stub
		try {
			return ptlNewsDao.getById(id);
		} catch (Exception e) {
			// TODO: handle exception
			throw new PortalException(StatusCode.DAO_ERROR, e);
		}
		
	}

	@Override
	public int addNews(News news) throws PortalException {
		// TODO Auto-generated method stub
		try {
			return ptlNewsDao.insert(news);
		} catch (Exception e) {
			// TODO: handle exception
			throw new PortalException(StatusCode.DAO_ERROR, e);
		}
		
	}

	@Override
	public int updateNews(News news) throws PortalException {
		// TODO Auto-generated method stub
		try {
			return ptlNewsDao.update(news);
		} catch (Exception e) {
			// TODO: handle exception
			throw new PortalException(StatusCode.DAO_ERROR, e);
		}
		
	}



}
