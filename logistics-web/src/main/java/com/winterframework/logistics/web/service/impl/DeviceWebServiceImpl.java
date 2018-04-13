package com.winterframework.logistics.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.winterframework.logistics.base.http.IHttpClient;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.common.entity.Device;
import com.winterframework.logistics.common.entity.TransOrder;
import com.winterframework.logistics.web.service.IDeviceWebService;
@Service("deviceWebServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class DeviceWebServiceImpl implements IDeviceWebService{
	@Resource(name = "httpClientImpl")
	private IHttpClient iHttpClient;

	@Override
	public Device findByNumber(String url, String findDeviceByNumber, String number) {
		// TODO Auto-generated method stub
		Response<Device> res=null;
		Device device=new Device();
		device.setNumber(number);
		try {
			res = iHttpClient.invokeHttp(url + findDeviceByNumber, device,
					new TypeReference<Response<Device>>() {
					});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(res!=null) {
			device=res.getData();
		}
		return device;
	}

	@Override
	public int updateDeviceStatus(String systemUrl, String updateDeviceStatusUrl, Device device) {
		// TODO Auto-generated method stub
		Response<Integer> response=null;
		int status=0;
		try {
			response= iHttpClient.invokeHttp(systemUrl + updateDeviceStatusUrl, device,
					new TypeReference<Response<Integer>>() {
					});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		status=response.getStatus();
		return status;
	}

	@Override
	public List<Device> findDeviceListByNumbers(String url, String findDeviceListByNumber,
			List<TransOrder> transOrders) {
		// TODO Auto-generated method stub
		Response<List<Device>> response=null;
		List<String> deviceNumberList = new ArrayList<String>();
		for (TransOrder transOrder2 : transOrders) {
			deviceNumberList.add(transOrder2.getDeviceNumber());
		}
		try {
			response = iHttpClient.invokeHttp(url + findDeviceListByNumber, deviceNumberList,
					new TypeReference<Response<List<Device>>>() {
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
	public Device findDeviceByNumber(String url, String findDeviceByNumber, String deviceNumber) {
		// TODO Auto-generated method stub
		Response<Device> response=null;
		Device device=new Device();
		device.setNumber(deviceNumber);
		try {
			response = iHttpClient.invokeHttp(url + findDeviceByNumber, device,
					new TypeReference<Response<Device>>() {
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
