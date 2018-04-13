package com.winterframework.logistics.app.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.winterframework.logistics.app.web.controller.dto.DeviceLocationFindByIdResponse;
import com.winterframework.logistics.base.http.IHttpClient;
import com.winterframework.logistics.base.model.Request;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.base.utils.JsonUtils;
import com.winterframework.logistics.common.entity.Address;
import com.winterframework.logistics.common.entity.DeviceLocation;
import com.winterframework.logistics.common.entity.Location;
import com.winterframework.logistics.common.entity.TransOrder;
import com.winterframework.logistics.common.enums.HttpStatusCode;
import com.winterframework.modules.spring.exetend.PropertyConfig;

@Controller("DeviceLocationController")
@RequestMapping("/logistics/order")
public class DeviceLocationController {
	@Resource(name = "httpClientImpl")
	private IHttpClient iHttpClient;

	@PropertyConfig(value = "url.transport.connect")
	private String url;

	@PropertyConfig(value = "logistics.transport.findOrderByAttribute")
	private String findOrderByAttributeUrl;

	@PropertyConfig(value = "logistics.transport.deviceLocation.findByAttribute")
	private String findDeviceLocationByAttributeUrl;

	@PropertyConfig(value = "logistics.transport.device.findByAttribute")
	private String findDeviceByAttributeUrl;
	
	@PropertyConfig(value="logistics.transport.deviceLocation.findByOrderId")
	private String findDeviceLocationByOrderIdUrl;
	/**
	 * 查询运输轨迹
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/trackQuery")
	public @ResponseBody Response<Object> searchTrack(@RequestBody Request<Map<String, String>> request) {
		List<Location> locationList = new ArrayList<Location>();
		DeviceLocationFindByIdResponse deviceLocationFindByIdResponse = new DeviceLocationFindByIdResponse();
		Response<Object> response = new Response<Object>();
		response.setMessage(HttpStatusCode.SUCCESS.getMessage());
		response.setStatus(HttpStatusCode.SUCCESS.getCode());
		Response<List<DeviceLocation>> deviceLocationResponse = null;
		Response<TransOrder> transOrderResponse = null;
		TransOrder transOrder = null;
		// 查询订单信息
		if (request != null && request.getData() != null) {
			TransOrder transOrder2 = new TransOrder();
			Long orderId=Long.parseLong(request.getData().get("id"));
			transOrder2.setId(orderId);
			try {
				transOrderResponse = iHttpClient.invokeHttp(url + findOrderByAttributeUrl, transOrder2,
						new TypeReference<Response<TransOrder>>() {
						});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				deviceLocationResponse = iHttpClient.invokeHttp(url + findDeviceLocationByOrderIdUrl, orderId,
						new TypeReference<Response<List<DeviceLocation>>>() {
						});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 对查找的信息进行处理
		if (deviceLocationResponse != null && deviceLocationResponse.getData() != null) {
			for (DeviceLocation deviceLocation : deviceLocationResponse.getData()) {
				Location location = new Location();
				location.setLatitude(deviceLocation.getLatitude());
				location.setLongitude(deviceLocation.getLongitude());
				location.setTime(deviceLocation.getTime());
				location.setLocation(deviceLocation.getAddress());
				locationList.add(location);
			}
		}
		if (transOrderResponse != null && transOrderResponse.getData()!=null) {
			transOrder = transOrderResponse.getData();
			deviceLocationFindByIdResponse.setTracks(locationList);
			deviceLocationFindByIdResponse.setContainerId(transOrder.getContainerId());
			deviceLocationFindByIdResponse.setLadingId(transOrder.getBillNumber());
			deviceLocationFindByIdResponse.setDeviceId(transOrder.getDeviceNumber());
			deviceLocationFindByIdResponse.setStartPosition(JsonUtils.fromJson(transOrder.getOrigin(), Address.class));
			deviceLocationFindByIdResponse
					.setEndPosition(JsonUtils.fromJson(transOrder.getDestination(), Address.class));
			deviceLocationFindByIdResponse.setTransportStatus(transOrder.getTransStatus());
			deviceLocationFindByIdResponse.setTransportType(transOrder.getTransMode());
			deviceLocationFindByIdResponse.setDeviceId(transOrder.getDeviceNumber());
		}
		response.setData(deviceLocationFindByIdResponse == null ? new JSONObject() : deviceLocationFindByIdResponse);
		return response;
	}
}
