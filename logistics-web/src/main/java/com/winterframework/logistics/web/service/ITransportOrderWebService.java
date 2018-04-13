package com.winterframework.logistics.web.service;

import java.util.List;
import java.util.Map;

import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.common.entity.TransOrder;
import com.winterframework.modules.web.jsonresult.Pager;

public interface ITransportOrderWebService {

	Response<List<TransOrder>> findOrderList(String url, String findOrderListUrl, TransOrder transOrder, Pager pager);

	int updateTransportStatus(String url, String updateTansportStatusUrl, TransOrder transOrder);

	TransOrder findOrderByAttribute(String url, String findOrderByAttributeUrl, TransOrder transOrder);

	Map<String, Integer> removeTransportOrder(String url, String deleteTranportOrderUrl, Long id);

	Map<String, Long> addOrder(String url, String addTransportOrderUrl, TransOrder transOrder);

	int checkContainerIsNull(String url, String findTransportCountByTransUrl, String containerId);

}
