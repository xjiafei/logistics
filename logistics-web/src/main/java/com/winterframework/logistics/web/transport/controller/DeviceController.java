package com.winterframework.logistics.web.transport.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.type.TypeReference;
import com.winterframework.logistics.base.http.IHttpClient;
import com.winterframework.logistics.base.http.RequestContext;
import com.winterframework.logistics.base.model.Request;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.common.entity.Company;
import com.winterframework.logistics.common.entity.Device;
import com.winterframework.logistics.common.entity.DeviceChangeStatus;
import com.winterframework.logistics.common.enums.HttpStatusCode;
import com.winterframework.logistics.web.service.IDeviceChangeStatusWebService;
import com.winterframework.logistics.web.service.IDeviceWebService;
import com.winterframework.logistics.web.service.IUserWebService;
import com.winterframework.logistics.web.transport.controller.dto.DeviceManageWebResponse;
import com.winterframework.logistics.web.transport.controller.dto.DeviceResponse;
import com.winterframework.logistics.web.transport.controller.dto.DeviceStatusSetRequest;
import com.winterframework.modules.spring.exetend.PropertyConfig;
import com.winterframework.modules.web.jsonresult.Pager;

@Controller("deviceController")
@RequestMapping("/company/device")
public class DeviceController {
	@Resource(name = "httpClientImpl")
	private IHttpClient iHttpClient;

	@Resource(name = "deviceChangeStatusWebServiceImpl")
	private IDeviceChangeStatusWebService deviceChangeStatusWebService;

	@Resource(name="deviceWebServiceImpl")
	private IDeviceWebService deviceWebService;
	
	@PropertyConfig(value = "server.url.system")
	private String systemUrl;

	@Resource(name="userWebServiceImpl")
	private IUserWebService userWebService;
	
	@PropertyConfig(value="logistics.system.user.queryUser")
	private String queryUser;
	
	@PropertyConfig(value = "logistics.system.company.findByAttribute")
	private String companyFindByAttributeUrl;

	@PropertyConfig(value = "logistics.system.device.findByAttribute")
	private String deviceFindByAttributeUrl;
	
	@PropertyConfig(value="logistics.system.device.findByNumberCode")
	private String findDeviceByNumberCode;

	@PropertyConfig(value = "logistics.system.company.findListById")
	private String findCompanyListByIdUrl;

	@PropertyConfig(value = "logistics.system.device.updateDeviceStatus")
	private String updateDeviceStatusUrl;

	@PropertyConfig(value = "logistics.system.deviceManage.findList")
	private String deviceMangeFindListUrl;

	@PropertyConfig(value = "logistics.system.deviceManage.insert")
	private String deviceManageInsertUrl;

	/**
	 * 设备列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/deviceList")
	public @ResponseBody Response<List<DeviceResponse>> showList(@RequestBody Request<Map<String, String>> request) {
		Long companyId=userWebService.findCompanyIdByUser(systemUrl,queryUser,RequestContext.getUserId());
		Response<List<DeviceResponse>> response = new Response<List<DeviceResponse>>();
		response.setMessage(HttpStatusCode.SUCCESS.getMessage());
		response.setStatus(HttpStatusCode.SUCCESS.getCode());
		Response<List<Company>> companyListResponse = new Response<List<Company>>();
		Response<List<Device>> deviceResponse = null;
		
		String deviceNumber = null;
		Pager pager = request.getPager();
		int count = 0;
		// 判断是否存在companyName,如果存在就获取companyId，
		if (request.getData() != null) {
			
			deviceNumber = request.getData().get("deviceNumber");
		}
		Device device = new Device();
		device.setCompanyId(companyId);
		device.setNumber(deviceNumber);
		try {
			deviceResponse = iHttpClient.invokeHttp(systemUrl + findDeviceByNumberCode, device, pager,
					new TypeReference<Response<List<Device>>>() {
					});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<DeviceResponse> deviceResponses = new ArrayList<DeviceResponse>();
		if (deviceResponse != null) {
			List<Long> companyIdList = new ArrayList<Long>();
			count = deviceResponse.getCount();
			for (Device device2 : deviceResponse.getData()) {
				companyIdList.add(device2.getCompanyId());
			}
			companyIdList = removeDuplicate(companyIdList);
			try {
				companyListResponse = iHttpClient.invokeHttp(systemUrl + findCompanyListByIdUrl, companyIdList,
						new TypeReference<Response<List<Company>>>() {
						});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<Device> deviceList = deviceResponse.getData();
			Map<Long, String> companyMap = convertToMap(companyListResponse.getData());
			if (deviceList != null) {
				for (int i = 0; i < deviceList.size(); i++) {
					DeviceResponse deviceResponseEntity = new DeviceResponse();
					deviceResponseEntity.setId(deviceList.get(i).getId());
					deviceResponseEntity.setDeviceNumber(deviceList.get(i).getNumber());
					deviceResponseEntity.setBattery(deviceList.get(i).getBattery());
					deviceResponseEntity.setCompany(companyMap.get(deviceList.get(i).getCompanyId()));
					deviceResponseEntity.setDeviceStatus(deviceList.get(i).getOnff());
					deviceResponseEntity.setModel(deviceList.get(i).getModel());
					deviceResponseEntity.setUsageStatus(deviceList.get(i).getStatus());
					deviceResponseEntity.setLocation(deviceList.get(i).getLocation());
					deviceResponses.add(deviceResponseEntity);
				}
			}
		}
		response.setData(deviceResponses);
		response.setCount(count);
		return response;
	}

	private Map<Long, String> convertToMap(List<Company> companyList) {
		// TODO Auto-generated method stub
		Map<Long, String> map = new HashMap<Long, String>();
		if (companyList != null) {
			for (Company company : companyList) {
				map.put(company.getId(), company.getName());
			}
		}
		return map;
	}

	// 去重复
	private static <T> List<T> removeDuplicate(List<T> list) {
		// TODO Auto-generated method stub
		HashSet<T> h = new HashSet<T>(list);
		list.clear();
		list.addAll(h);
		return list;
	}

	/**
	 * 标记设备使用情况
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateDeviceUsageStatus")
	public @ResponseBody Response<String> updateDeviceUsageStatus(
			@RequestBody Request<DeviceStatusSetRequest> request) {
		Response<String> response = new Response<String>();
		response.setMessage(HttpStatusCode.SUCCESS.getMessage());
		response.setStatus(HttpStatusCode.SUCCESS.getCode());
		if (request.getData() != null) {
			Device device = new Device();
			DeviceStatusSetRequest deviceStatusSetRequest = request.getData();
			device.setId(deviceStatusSetRequest.getDeviceId());
			device.setStatus(deviceStatusSetRequest.getUsageStatus());
			try {
				iHttpClient.invokeHttpWithoutResultType(systemUrl + updateDeviceStatusUrl, device);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return response;
	}

	@RequestMapping("/showManages")
	public @ResponseBody Response<List<DeviceChangeStatus>> showDeviceHistory(
			@RequestBody Request<Map<String, Long>> request) {
		Response<List<DeviceChangeStatus>> response = new Response<List<DeviceChangeStatus>>();
		Pager pager = request.getPager();
		if (request != null) {
			Long deviceId = request.getData() == null ? null : request.getData().get("deviceId");
			DeviceChangeStatus deviceManage = new DeviceChangeStatus();
			deviceManage.setDeviceId(deviceId);
			response = deviceChangeStatusWebService.showList(systemUrl, deviceMangeFindListUrl, deviceManage, pager);
		}
		return response;
	}

	@RequestMapping("/addDeviceRecord")
	public @ResponseBody Response<Integer> addDeviceRecord(@RequestBody Request<DeviceManageWebResponse> request) {
		Response<Integer> response = new Response<Integer>();
		int status = 0;	
		if (request != null) {
			DeviceManageWebResponse deviceManage = request.getData();
			Device device = new Device();
			device.setId(deviceManage.getDeviceId());
			device.setStatus(deviceManage.getStatus());
			status = deviceChangeStatusWebService.addDeviceRecord(systemUrl, deviceManageInsertUrl, deviceManage);
			deviceWebService.updateDeviceStatus(systemUrl,updateDeviceStatusUrl,device);
		}
		response.setStatus(status);
		return response;
	}

}
