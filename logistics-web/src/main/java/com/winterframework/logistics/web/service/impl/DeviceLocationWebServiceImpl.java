package com.winterframework.logistics.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.winterframework.logistics.base.http.IHttpClient;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.common.entity.DeviceLocation;
import com.winterframework.logistics.common.entity.TransOrder;
import com.winterframework.logistics.web.service.IDeviceLocationWebService;

@Service("deviceLocationWebServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class DeviceLocationWebServiceImpl implements IDeviceLocationWebService{
	@Resource(name = "httpClientImpl")
	private IHttpClient iHttpClient;

	@Override
	public List<DeviceLocation> findNewLocationListByOrders(String url, String findNewLocationList,
			List<TransOrder> transOrders) {
		// TODO Auto-generated method stub
		Response<List<DeviceLocation>> response=null;
		List<Long> orderIdList = new ArrayList<Long>();
		for (TransOrder transOrder : transOrders) {
			orderIdList.add(transOrder.getId());
		}
		try {
			response = iHttpClient.invokeHttp(url + findNewLocationList, orderIdList,
					new TypeReference<Response<List<DeviceLocation>>>() {
					});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(response!=null && response.getData()!=null) {
			return response.getData();
		}
		return null;
	}

	@Override
	public DeviceLocation findNewLocationByOrder(String url, String findNewDeviceLocation, TransOrder transOrder) {
		// TODO Auto-generated method stub
		Response<DeviceLocation> response=null;
		try {
			response = iHttpClient.invokeHttp(url + findNewDeviceLocation, transOrder.getId(),
					new TypeReference<Response<DeviceLocation>>() {
					});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(response!=null && response.getData()!=null) {
			return response.getData();
		}
		return null;
	}

	@Override
	public List<DeviceLocation> getLocationsByOrder(String url, String findDeviceLocationByOrderId, Long orderId) {
		// TODO Auto-generated method stub
		Response<List<DeviceLocation>> response=new Response<List<DeviceLocation>>();
		try {
			response = iHttpClient.invokeHttp(url + findDeviceLocationByOrderId, orderId,
					new TypeReference<Response<List<DeviceLocation>>>() {
					});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(response!=null && response.getData()!=null) {
			return response.getData();
		}
		return null;
	}
}
