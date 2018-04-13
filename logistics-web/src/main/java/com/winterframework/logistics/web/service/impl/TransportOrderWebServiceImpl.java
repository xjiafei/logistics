package com.winterframework.logistics.web.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.winterframework.logistics.base.http.IHttpClient;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.common.entity.TransOrder;
import com.winterframework.logistics.common.enums.ContainerStatus;
import com.winterframework.logistics.web.service.ITransportOrderWebService;
import com.winterframework.modules.web.jsonresult.Pager;

@Service("transportOrderWebServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class TransportOrderWebServiceImpl implements ITransportOrderWebService {
	@Resource(name = "httpClientImpl")
	private IHttpClient iHttpClient;

	@Override
	public Response<List<TransOrder>> findOrderList(String url, String findOrderListUrl, TransOrder transOrder,
			Pager pager) {
		// TODO Auto-generated method stub
		Response<List<TransOrder>> response = null;
		try {
			response = iHttpClient.invokeHttp(url + findOrderListUrl, transOrder, pager,
					new TypeReference<Response<List<TransOrder>>>() {
					});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public int updateTransportStatus(String url, String updateTansportStatusUrl, TransOrder transOrder) {
		// TODO Auto-generated method stub
		Response<Object> response = null;
		TransOrder tOrder = new TransOrder();
		tOrder.setId(transOrder.getId());
		tOrder.setTransStatus(transOrder.getTransStatus());
		tOrder.setFinishTime(transOrder.getFinishTime());
		try {
			response = iHttpClient.invokeHttp(url + updateTansportStatusUrl, tOrder,
					new TypeReference<Response<Object>>() {
					});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (response != null) {
			return response.getStatus();
		}
		return 0;
	}

	@Override
	public TransOrder findOrderByAttribute(String url, String findOrderByAttributeUrl, TransOrder transOrder) {
		// TODO Auto-generated method stub
		Response<TransOrder> response = null;
		try {
			response = iHttpClient.invokeHttp(url + findOrderByAttributeUrl, transOrder,
					new TypeReference<Response<TransOrder>>() {
					});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (response != null && response.getData() != null) {
			return response.getData();
		}
		return null;
	}

	@Override
	public Map<String, Integer> removeTransportOrder(String url, String deleteTranportOrderUrl, Long id) {
		// TODO Auto-generated method stub
		Response<Map<String, Integer>> response = null;
		TransOrder transOrder = new TransOrder();
		transOrder.setId(id);
		try {
			response = iHttpClient.invokeHttp(url + deleteTranportOrderUrl, transOrder,
					new TypeReference<Response<Map<String, Integer>>>() {
					});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (response != null && response.getData() != null) {
			return response.getData();
		}
		return null;
	}

	@Override
	public Map<String, Long> addOrder(String url, String addTransportOrderUrl, TransOrder transOrder) {
		// TODO Auto-generated method stub
		Response<Map<String, Long>> response =null;
		try {
			response = iHttpClient.invokeHttp(url + addTransportOrderUrl, transOrder,
					new TypeReference<Response<Map<String, Long>>>() {
					});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (response != null && response.getData()!=null) {
			return response.getData();
		}
		return null;
	}

	@Override
	public int checkContainerIsNull(String url, String findTransportCountByTransUrl, String containerId) {
		// TODO Auto-generated method stub
		Response<Integer> response=null;
		TransOrder transOrder=new TransOrder();
		transOrder.setContainerId(containerId);
		try {
			response = iHttpClient.invokeHttp(url + findTransportCountByTransUrl, transOrder,
					new TypeReference<Response<Integer>>() {
					});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(response!=null) {
			if(response.getStatus()!=0) {
				return ContainerStatus.USING_CONTAINER.getKey();
			}else {
				return ContainerStatus.NULL_CONTAINER.getKey();
			}
		}
		return 0;
	}

}
