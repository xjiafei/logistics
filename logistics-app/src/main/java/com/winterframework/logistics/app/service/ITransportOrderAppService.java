package com.winterframework.logistics.app.service;

import java.util.List;
import java.util.Map;

import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.common.entity.TransOrder;
import com.winterframework.modules.web.jsonresult.Pager;

public interface ITransportOrderAppService {

	Response<List<TransOrder>> findOrderList(String url, String findOrderListUrl, TransOrder transOrder, Pager pager);

	int updateTransportStatus(String url, String updateTansportStatusUrl, TransOrder transOrder);

	int checkContainerIsNull(String url, String findTransportCountByTransUrl, String containerId);

	Map<String, Long> addOrder(String url, String addTransportOrderUrl, TransOrder transOrder);

}
