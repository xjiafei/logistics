package com.winterframework.logistics.common.dao;


import java.util.List;

import com.winterframework.logistics.base.dao.IBaseDao;
import com.winterframework.logistics.common.entity.SearchAttribute;
import com.winterframework.logistics.common.entity.TransOrder;
import com.winterframework.modules.web.jsonresult.Pager;

public interface ITransOrderDao  extends IBaseDao<TransOrder>{
   Long insertOrder(TransOrder transOrder);
   
   int updateOrder(TransOrder transOrder);
   
   int deleteOrder(TransOrder transOrder);
   
   List<TransOrder> searchTransportOrderByCode(String code);
   
   List<TransOrder> searchTransportOrderByAttributeAndPager(TransOrder transOrder,Pager pager,int count);
   
   List<TransOrder> searchTransportOrderByAttribute(SearchAttribute searchAttribute,Pager pager,int count);
   
   int getCount(TransOrder transOrder);
   
   int searchListCount(SearchAttribute searchAttribute);
   
   List<TransOrder> searchTransportOrderByNumber(String code);
   
   int searchTransportCountByNumber(String code);
   
   List<TransOrder> searchTransportOrderHistoryByAttributeAndPager(TransOrder transOrder,Pager pager,int count);
   
}