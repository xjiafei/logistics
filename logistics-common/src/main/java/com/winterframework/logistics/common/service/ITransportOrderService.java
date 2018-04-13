package com.winterframework.logistics.common.service;

import java.util.List;

import com.winterframework.logistics.base.service.IBaseService;
import com.winterframework.logistics.common.entity.SearchAttribute;
import com.winterframework.logistics.common.entity.TransOrder;
import com.winterframework.modules.web.jsonresult.Pager;


public interface ITransportOrderService extends IBaseService<TransOrder> {
	
	public Long addTransportOrder(TransOrder transOrderList);
	
	public List<TransOrder> findAllTransportOrder(TransOrder transOrder);
	
	public List<TransOrder> findListTransportOrderByPager(TransOrder transOrder,Pager pager,int count);
	
	public int updateTransportStatus(TransOrder transOrder);
	
	public TransOrder findByAttribute(TransOrder transOrder);
	
	public int deleteTransport(TransOrder transOrder);
	
	public List<TransOrder> searchByCode(String code);
	
	public List<TransOrder> searchByAttribute(SearchAttribute searchAttribute,Pager pager,int count);
	
	public int getCount(TransOrder transOrder);
	
	public int searchListCount(SearchAttribute searchAttribute);
	
	public List<TransOrder> searchTransportOrderByNumber(String code);
	
	public int searchTransportCountByNumber(String code);

	public List<TransOrder> getHistoryByAttribute(TransOrder transOrder, Pager pager, int count);
}
