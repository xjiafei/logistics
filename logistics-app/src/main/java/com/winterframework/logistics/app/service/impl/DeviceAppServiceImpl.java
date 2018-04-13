package com.winterframework.logistics.app.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.winterframework.logistics.app.service.IDeviceAppService;
import com.winterframework.logistics.base.http.IHttpClient;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.common.entity.Device;
@Service("deviceAppServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class DeviceAppServiceImpl implements IDeviceAppService{
	@Resource(name = "httpClientImpl")
	private IHttpClient iHttpClient;
	@Override
	public Device findByNumber(String url, String findDeviceByNumberUrl, String deviceNumber) {
		// TODO Auto-generated method stub
		Response<Device> res=null;
		Device device=new Device();
		device.setNumber(deviceNumber);
		try {
			res = iHttpClient.invokeHttp(url + findDeviceByNumberUrl, device,
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

}
