package com.winterframework.logistics.web.transport.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.winterframework.logistics.base.http.IHttpClient;
import com.winterframework.logistics.base.model.Request;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.base.utils.JsonUtils;
import com.winterframework.logistics.common.entity.Address;
import com.winterframework.logistics.common.entity.Device;
import com.winterframework.logistics.common.entity.DeviceLocation;
import com.winterframework.logistics.common.entity.Location;
import com.winterframework.logistics.common.entity.TransOrder;
import com.winterframework.logistics.common.enums.HttpStatusCode;
import com.winterframework.logistics.web.service.IDeviceLocationWebService;
import com.winterframework.logistics.web.service.IDeviceWebService;
import com.winterframework.logistics.web.service.ITransportOrderWebService;
import com.winterframework.logistics.web.transport.controller.dto.DeviceLocationFindByIdResponse;
import com.winterframework.modules.spring.exetend.PropertyConfig;

@Controller("deviceLocationController")
@RequestMapping("/logistics/order")
public class DeviceLocationController {
	@Resource(name = "httpClientImpl")
	private IHttpClient iHttpClient;

	@Resource(name = "transportOrderWebServiceImpl")
	private ITransportOrderWebService transportOrderWebService;

	@Resource(name = "deviceLocationWebServiceImpl")
	private IDeviceLocationWebService deviceLocationWebService;
	
	@Resource(name="deviceWebServiceImpl")
	private IDeviceWebService deviceWebService;
	
	@PropertyConfig(value = "url.transport.connect")
	private String url;

	@PropertyConfig(value = "logistics.transport.findOrderByAttribute")
	private String findOrderByAttribute;

	@PropertyConfig(value = "logistics.transport.device.findDeviceByNumber")
	private String findDeviceByNumber;
	
	@PropertyConfig(value = "logistics.transport.deviceLocation.findByAttribute")
	private String findDeviceLocationByAttribute;

	@PropertyConfig(value = "logistics.transport.device.findByAttribute")
	private String findDeviceByAttribute;

	@PropertyConfig(value = "logistics.transport.deviceLocation.findByOrderId")
	private String findDeviceLocationByOrderId;

	/**
	 * 查询轨迹
	 * 
	 * @param request
	 *            订单信息
	 * @return 轨迹列表
	 */
	@RequestMapping("/trackQuery")
	public @ResponseBody Response<DeviceLocationFindByIdResponse> searchTrack(
			@RequestBody Request<Map<String, String>> request) {
		Response<DeviceLocationFindByIdResponse> response = new Response<DeviceLocationFindByIdResponse>();
		response.setMessage(HttpStatusCode.SUCCESS.getMessage());
		response.setStatus(HttpStatusCode.SUCCESS.getCode());
		DeviceLocationFindByIdResponse deviceLocationFindByIdResponse = new DeviceLocationFindByIdResponse();
		List<Location> locationList = new ArrayList<Location>();
		TransOrder transOrder = null;
		Device device=null;
		List<DeviceLocation> deviceLocations = null;
		// 查找运输订单数据
		if (request != null && request.getData() != null) {
			TransOrder transOrder2 = new TransOrder();
			Long orderId = Long.parseLong(request.getData().get("id"));
			transOrder2.setId(orderId);
			transOrder = transportOrderWebService.findOrderByAttribute(url, findOrderByAttribute, transOrder2);
			deviceLocations = deviceLocationWebService.getLocationsByOrder(url, findDeviceLocationByOrderId, orderId);
			device=deviceWebService.findByNumber(url, findDeviceByNumber, transOrder.getDeviceNumber());
		}
		// 对查找的数据进行处理
		if (deviceLocations != null) {
			for (DeviceLocation deviceLocation : deviceLocations) {
				Location location = new Location();
				location.setLatitude(deviceLocation.getLatitude());
				location.setLongitude(deviceLocation.getLongitude());
				location.setTime(deviceLocation.getTime());
				location.setLocation(deviceLocation.getAddress());
				locationList.add(location);
			}
		}
		deviceLocationFindByIdResponse.setTracks(locationList);
		deviceLocationFindByIdResponse.setContainerId(transOrder.getContainerId());
		deviceLocationFindByIdResponse.setStartPosition(JsonUtils.fromJson(transOrder.getOrigin(), Address.class));
		deviceLocationFindByIdResponse.setEndPosition(JsonUtils.fromJson(transOrder.getDestination(), Address.class));
		deviceLocationFindByIdResponse.setTransportStatus(transOrder.getTransStatus());
		deviceLocationFindByIdResponse.setDeviceNumber(transOrder.getDeviceNumber());
		deviceLocationFindByIdResponse.setStartTime(transOrder.getStartTime());
		deviceLocationFindByIdResponse.setDeviceOnff(device.getOnff());
		deviceLocationFindByIdResponse.setTransportType(transOrder.getTransMode());
		response.setData(deviceLocationFindByIdResponse);
		return response;
	}
}
