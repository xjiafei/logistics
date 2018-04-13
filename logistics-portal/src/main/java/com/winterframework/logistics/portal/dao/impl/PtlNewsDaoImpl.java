package com.winterframework.logistics.portal.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.winterframework.logistics.base.dao.BaseDaoImpl;
import com.winterframework.logistics.portal.dao.IPtlNewsDao;
import com.winterframework.logistics.portal.entity.News;
import com.winterframework.modules.page.Page;
import com.winterframework.modules.web.jsonresult.Pager;
@Repository("ptlNewsDaoImpl")
public class PtlNewsDaoImpl <E extends News>   extends BaseDaoImpl<News> implements IPtlNewsDao{

	@Override
	public List<News> selectListByAttribute(News news, Pager pager,int count) throws Exception {
		// TODO Auto-generated method stub
		List<News> nList=null;
		if(count==0 || count<pager.getEndNo()) {
			nList=sqlSessionTemplate.selectList(getQueryPath("getByAttribute"), news);
		}else {
			RowBounds rowBounds=dealWithRowBounds(pager,count);
			nList=sqlSessionTemplate.selectList(getQueryPath("getByAttribute"), news,rowBounds);
		}
		return nList;
	
	}
	
	public RowBounds dealWithRowBounds(Pager pager,int count) {
		Page<News> page=new Page<News>(pager.getStartNo(),pager.getEndNo(),count);
		return new RowBounds(page.getFirstResult(), page.getPageSize());
	}

	@Override
	public int getCount(News news) throws Exception{
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(getQueryPath("getCount"), news);
	}

}
