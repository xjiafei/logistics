package com.winterframework.logistics.app.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.winterframework.logistics.app.service.IDeviceLocationAppService;
import com.winterframework.logistics.base.http.IHttpClient;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.common.entity.DeviceLocation;
import com.winterframework.logistics.common.entity.TransOrder;

@Service("deviceLocationAppServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class DeviceLocationAppServiceImpl implements IDeviceLocationAppService{
	@Resource(name = "httpClientImpl")
	private IHttpClient iHttpClient;
	@Override
	public List<DeviceLocation> findNewLocationListByOrders(String url, String findNewLocationList,
			List<TransOrder> transOrders) {
		// TODO Auto-generated method stub
		Response<List<DeviceLocation>> response=null;
		List<Long> orderIdList = new ArrayList<Long>();
		for (TransOrder transOrder2 : transOrders) {
			orderIdList.add(transOrder2.getId());
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

}
