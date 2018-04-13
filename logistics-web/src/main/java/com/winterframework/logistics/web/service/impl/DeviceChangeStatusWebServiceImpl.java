package com.winterframework.logistics.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.core.type.TypeReference;
import com.winterframework.logistics.base.http.IHttpClient;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.common.entity.DeviceChangeStatus;
import com.winterframework.logistics.web.service.IDeviceChangeStatusWebService;
import com.winterframework.logistics.web.transport.controller.dto.DeviceManageWebResponse;
import com.winterframework.modules.web.jsonresult.Pager;

@Service("deviceChangeStatusWebServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class DeviceChangeStatusWebServiceImpl implements IDeviceChangeStatusWebService{
	@Resource(name = "httpClientImpl")
	private IHttpClient iHttpClient;
	@Override
	public Response<List<DeviceChangeStatus>> showList(String systemUrl, String deviceMangeFindListUrl, DeviceChangeStatus deviceManage,Pager pager) {
		// TODO Auto-generated method stub
		Response<List<DeviceChangeStatus>> res=null;
		try {
			res=iHttpClient.invokeHttp(systemUrl+deviceMangeFindListUrl, deviceManage,pager,
					new TypeReference<Response<List<DeviceChangeStatus>>>() {
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	@Override
	public int addDeviceRecord(String systemUrl, String deviceManageInsertUrl, DeviceManageWebResponse deviceManage) {
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
