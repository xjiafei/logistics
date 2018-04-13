package com.winterframework.logistics.common.dao.impl;


import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.winterframework.logistics.base.dao.BaseDaoImpl;
import com.winterframework.logistics.common.dao.ITransOrderDao;
import com.winterframework.modules.page.Page;
import com.winterframework.modules.web.jsonresult.Pager;
import com.winterframework.logistics.common.entity.SearchAttribute;
import com.winterframework.logistics.common.entity.TransOrder;

@Repository("transOrderDaoImpl")
public class TransOrderDaoImpl<E extends TransOrder>  extends BaseDaoImpl<TransOrder> implements ITransOrderDao{

	@Override
	public Long insertOrder(TransOrder transOrder) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.insert(getQueryPath("insert"),transOrder);
		return transOrder.getId();
	}

	@Override
	public int updateOrder(TransOrder transOrder) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.update(getQueryPath("update"), transOrder);
	}
	
	@Override
	public int deleteOrder(TransOrder transOrder) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.delete(getQueryPath("delete"), transOrder);
	}

	@Override
	public List<TransOrder> searchTransportOrderByCode(String code) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(getQueryPath("searchIdByAttribute"), code);
	}
	
	@Override
	public List<TransOrder> searchTransportOrderByAttribute(SearchAttribute searchAttribute,Pager pager,int count) {
		// TODO Auto-generated method stub
		List<TransOrder> taOrders=null;
		if(count==0 || count<pager.getEndNo()) {
			taOrders=sqlSessionTemplate.selectList(getQueryPath("searchListByAttribute"), searchAttribute);
		}else {
			RowBounds rowBounds=dealWithRowBounds(pager,count);
			taOrders=sqlSessionTemplate.selectList(getQueryPath("searchListByAttribute"), searchAttribute,rowBounds);
		}
		return taOrders;
	}

	@Override
	public int searchListCount(SearchAttribute searchAttribute) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(getQueryPath("searchListCount"), searchAttribute);
	}

	@Override
	public List<TransOrder> searchTransportOrderByAttributeAndPager(TransOrder transOrder, Pager pager,int count) {
		// TODO Auto-generated method stub
		List<TransOrder> taOrders=null;
		if(count==0 || count<pager.getEndNo()) {
			taOrders=sqlSessionTemplate.selectList(getQueryPath("getByAttribute"), transOrder);
		}else {
			RowBounds rowBounds=dealWithRowBounds(pager,count);
			taOrders=sqlSessionTemplate.selectList(getQueryPath("getByAttribute"), transOrder, rowBounds);
		}
		return taOrders;
	}

	@Override
	public int getCount(TransOrder transOrder) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(getQueryPath("getCount"), transOrder);
	}

	public RowBounds dealWithRowBounds(Pager pager,int count) {
		Page<TransOrder> page=new Page<TransOrder>(pager.getStartNo(),pager.getEndNo(),count);
		return new RowBounds(page.getFirstResult(), page.getPageSize());
	}

	@Override
	public List<TransOrder> searchTransportOrderByNumber(String code) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(getQueryPath("searchIdByNumber"), code);
	}

	@Override
	public int searchTransportCountByNumber(String code) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(getQueryPath("searchIdCountByNumber"), code);
	}

	@Override
	public List<TransOrder> searchTransportOrderHistoryByAttributeAndPager(TransOrder transOrder, Pager pager,
			int count) {
		// TODO Auto-generated method stub
		List<TransOrder> taOrders=null;
		if(count==0 || count<pager.getEndNo()) {
			taOrders=sqlSessionTemplate.selectList(getQueryPath("getHistoryByAttribute"), transOrder);
		}else {
			RowBounds rowBounds=dealWithRowBounds(pager,count);
			taOrders=sqlSessionTemplate.selectList(getQueryPath("getHistoryByAttribute"), transOrder, rowBounds);
		}
		return taOrders;
	}

}
