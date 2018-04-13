package com.winterframework.logistics.app.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.winterframework.logistics.app.service.IDeviceChangeStatusAppService;
import com.winterframework.logistics.app.web.controller.dto.DeviceManageAppResponse;
import com.winterframework.logistics.base.http.IHttpClient;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.common.entity.DeviceChangeStatus;

@Service("deviceChangeStatusAppServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class DeviceChangeStatusAppServiceImpl implements IDeviceChangeStatusAppService{
	@Resource(name = "httpClientImpl")
	private IHttpClient iHttpClient;
	
	@Override
	public int addDeviceRecord(String systemUrl, String deviceManageInsertUrl, DeviceManageAppResponse deviceManage) {
		// TODO Auto-generated method stub
		DeviceChangeStatus dManage=new DeviceChangeStatus();
		dManage.setDeviceId(deviceManage.getDeviceId());
		dManage.setCreatorId(0L);
		dManage.setCreateTime(System.currentTimeMillis());
		dManage.setRemark(deviceManage.getLocation());
		dManage.setToStatus(deviceManage.getStatus());
		dManage.setFromStatus(0);
		Response<Integer> response=null;
		try {
			response=iHttpClient.invokeHttp(systemUrl+deviceManageInsertUrl,dManage,
					new TypeReference<Response<Integer>>() {
							} );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(response!=null) {
			return response.getData();
		}
		return 0;
	}

}
