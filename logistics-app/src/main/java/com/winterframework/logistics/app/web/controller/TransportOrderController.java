package com.winterframework.logistics.app.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.winterframework.logistics.app.service.IDeviceAppService;
import com.winterframework.logistics.app.service.IDeviceChangeStatusAppService;
import com.winterframework.logistics.app.service.IDeviceLocationAppService;
import com.winterframework.logistics.app.service.ITransportOrderAppService;
import com.winterframework.logistics.app.service.IUserAppService;
import com.winterframework.logistics.app.web.controller.dto.DeviceManageAppResponse;
import com.winterframework.logistics.app.web.controller.dto.HistoryTransportResponse;
import com.winterframework.logistics.app.web.controller.dto.TransportDetailResponse;
import com.winterframework.logistics.app.web.controller.dto.TransportIdResponse;
import com.winterframework.logistics.app.web.controller.dto.TransportOrderRequest;
import com.winterframework.logistics.app.web.controller.dto.TransportOrderResponse;
import com.winterframework.logistics.app.web.controller.dto.UpdateTransLadingIdRequest;
import com.winterframework.logistics.app.web.controller.dto.UpdateTransportStatusRequest;
import com.winterframework.logistics.base.aop.annotation.RequestValidate;
import com.winterframework.logistics.base.enums.StatusCode;
import com.winterframework.logistics.base.enums.YesNo;
import com.winterframework.logistics.base.http.IHttpClient;
import com.winterframework.logistics.base.http.RequestContext;
import com.winterframework.logistics.base.model.Request;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.base.utils.JsonUtils;
import com.winterframework.logistics.common.entity.Address;
import com.winterframework.logistics.common.entity.Device;
import com.winterframework.logistics.common.entity.DeviceLocation;
import com.winterframework.logistics.common.entity.SearchAttribute;
import com.winterframework.logistics.common.entity.TransOrder;
import com.winterframework.logistics.common.enums.ContainerStatus;
import com.winterframework.logistics.common.enums.DeviceStatus;
import com.winterframework.logistics.common.enums.FrequencyType;
import com.winterframework.logistics.common.enums.HttpStatusCode;
import com.winterframework.logistics.common.enums.TransStatus;
import com.winterframework.logistics.dto.device.DeviceLocationFreqSetRequest;
import com.winterframework.logistics.dto.device.DeviceShutdownRequest;
import com.winterframework.modules.spring.exetend.PropertyConfig;
import com.winterframework.modules.web.jsonresult.Pager;

@Controller("transportOrderController")
@RequestMapping("/logistics/order")
public class TransportOrderController {
	@Resource(name = "httpClientImpl")
	private IHttpClient iHttpClient;

	@Resource(name = "deviceAppServiceImpl")
	private IDeviceAppService deviceAppservice;

	@Resource(name = "deviceChangeStatusAppServiceImpl")
	IDeviceChangeStatusAppService deviceChangeStatusAppService;

	@Resource(name = "transportOrderAppServiceImpl")
	ITransportOrderAppService transportOrderAppService;

	@Resource(name = "deviceLocationAppServiceImpl")
	IDeviceLocationAppService deviceLocationAppService;

	@Resource(name = "UserAppServiceImpl")
	IUserAppService userAppService;

	@PropertyConfig(value = "logistics.system.user.queryUser")
	private String queryUser;

	@PropertyConfig(value = "logistics.transport.findOrderList")
	private String findOrderListUrl;

	@PropertyConfig(value = "logistics.transport.addTransportOrder")
	private String addTransportOrderUrl;

	@PropertyConfig(value = "logistics.transport.updateTansportStatus")
	private String updateTansportStatusUrl;
	@PropertyConfig(value = "logistics.transport.findOrderByAttribute")
	private String findOrderByAttributeUrl;

	@PropertyConfig(value = "logistics.transport.deleteTranportOrder")
	private String deleteTranportOrderUrl;

	@PropertyConfig(value = "logistics.transport.findTrandportOrdersByAttribute")
	private String findTrandportOrdersByAttributeUrl;

	@PropertyConfig(value = "logistics.transport.updateLadingId")
	private String updateLadingIdUrl;

	@PropertyConfig(value = "logistics.transport.transportListAsHistory")
	private String transportListAsHistory;

	@PropertyConfig(value = "logistics.transport.findIdByKeyword")
	private String findIdByKeywordUrl;

	@PropertyConfig(value = "logistics.transport.findTrackList")
	private String findTrackListUrl;

	@PropertyConfig(value = "logistics.device.manage.shutdown")
	private String deviceShutdownUrl;

	@PropertyConfig(value = "logistics.device.manage.startup")
	private String deviceStartupUrl;

	@PropertyConfig(value = "logistics.device.manage.locationFreq")
	private String locationFreqUrl;

	@PropertyConfig(value = "logistics.transport.device.findById")
	private String findById;

	@PropertyConfig(value = "logistics.transport.device.updateDevice")
	private String updateDevice;

	@PropertyConfig(value = "logistics.transport.device.findDeviceByNumber")
	private String findDeviceByNumberUrl;

	@PropertyConfig(value = "logistics.transport.device.selectCountByAttribute")
	private String selectCountByAttribute;

	@PropertyConfig(value = "logistics.transport.findIdByCheckNumber")
	private String findIdByCheckNumberUrl;

	@PropertyConfig(value = "logistics.transport.findTransportCountByTrans")
	private String findTransportCountByTransUrl;

	@PropertyConfig(value = "server.url.device")
	private String deviceUrl;

	@PropertyConfig(value = "server.url.system")
	private String systemUrl;

	@PropertyConfig(value = "logistics.transport.device.findDeviceListByNumber")
	private String findDeviceListByNumber;

	@PropertyConfig(value = "logistics.transport.deviceLocation.findNewLocationList")
	private String findNewLocationList;

	@PropertyConfig(value = "logistics.system.deviceManage.insert")
	private String deviceManageInsertUrl;

	@PropertyConfig(value = "url.transport.connect")
	private String url;

	/**
	 * 展示订单运输列表（状态为运输中）
	 * 
	 * @return
	 */
	@RequestMapping("/transportList")
	public @ResponseBody Response<Object> findAllTransportOrderList(
			@RequestBody @RequestValidate Request<Object> request) {
		Long companyId = userAppService.findCompanyIdByUser(systemUrl, queryUser, RequestContext.getUserId());
		Response<Object> response = new Response<Object>();
		response.setMessage(HttpStatusCode.SUCCESS.getMessage());
		response.setStatus(HttpStatusCode.SUCCESS.getCode());
		Response<List<TransOrder>> trResponse = null;
		TransOrder transOrder = new TransOrder();
		transOrder.setTransStatus(TransStatus.TRANSPORT_ING.getValue());
		transOrder.setCreatorId(companyId);
		Pager pager = request.getPager();
		int count = 0;
		List<DeviceLocation> deviceLocations = null;
		trResponse = transportOrderAppService.findOrderList(url, findOrderListUrl, transOrder, pager);
		if (trResponse != null && trResponse.getData() != null) {
			count = trResponse.getCount();
			deviceLocations = deviceLocationAppService.findNewLocationListByOrders(url, findNewLocationList,
					trResponse.getData());
		}
		if (trResponse != null && trResponse.getData() != null) {
			List<TransportOrderResponse> transportOrderResponseList = new ArrayList<TransportOrderResponse>();
			for (TransOrder transOrder2 : trResponse.getData()) {
				TransportOrderResponse transportOrderResponse = new TransportOrderResponse();
				transportOrderResponse.setId(transOrder2.getId());
				transportOrderResponse.setContainerId(transOrder2.getContainerId());
				transportOrderResponse.setStartPosition(transOrder2.getOrigin());
				transportOrderResponse.setEndPosition(transOrder2.getDestination());
				transportOrderResponse.setTransportType(transOrder2.getTransMode());
				transportOrderResponse.setTransportStatus(transOrder2.getTransStatus());
				transportOrderResponse.setCreateTime(transOrder2.getCreateTime());
				if (deviceLocations != null) {
					for (DeviceLocation deviceLocation : deviceLocations) {
						if (deviceLocation.getOrderId().equals(transportOrderResponse.getId())) {
							transportOrderResponse.setNewAddress(deviceLocation.getAddress());
						}
					}
				}
				transportOrderResponseList.add(transportOrderResponse);
			}
			response.setData(transportOrderResponseList == null ? new JSONArray() : transportOrderResponseList);

		} else {
			response.setData(new JSONArray());
		}
		response.setCount(count);
		return response;
	}

	/**
	 * 添加一条订单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/insertTransportData")
	public @ResponseBody Response<Object> addTransportOrder(
			@RequestBody @RequestValidate Request<TransportOrderRequest> request) {
		Long companyId = userAppService.findCompanyIdByUser(systemUrl, queryUser, RequestContext.getUserId());
		Response<Object> response = new Response<Object>();
		response.setStatus(HttpStatusCode.SUCCESS.getCode());
		response.setMessage(HttpStatusCode.SUCCESS.getMessage());
		Response<Map<String, Long>> trResponse = null;
		String imei = null; // 设备编号
		boolean flag = true;
		boolean flag2 = true;
		int numberCode = DeviceStatus.DISABLED.getKey();
		int containerCode = ContainerStatus.USING_CONTAINER.getKey();
		TransOrder transOrder = null;
		Map<String, Long> orderIdMap = null;
		if (request != null && request.getData() != null) {
			transOrder = dealWithTransOrder(request.getData());
			if (transOrder.getDeviceNumber() != null) {
				Device device = new Device();
				device.setNumber(transOrder.getDeviceNumber());
				device.setCompanyId(companyId);
				numberCode = checkDeviceNumberIsNull(device); // 校验设备号是否存在
				containerCode = transportOrderAppService.checkContainerIsNull(url, findTransportCountByTransUrl,
						transOrder.getContainerId());
				flag = (numberCode == DeviceStatus.USABLE.getKey()) ? false : true;
				flag2 = (containerCode == ContainerStatus.NULL_CONTAINER.getKey()) ? false : true;
				imei = transOrder.getDeviceNumber();
			}
			if (flag) { // 校验设备编号
				response.setData(null);
				response.setStatus(numberCode);
				return response;
			}
			if (flag2) { // 校验container
				response.setData(null);
				response.setStatus(containerCode);
				return response;
			}
			boolean isSuccess = deviceProcessing(imei, deviceUrl, null, DeviceStatus.USING.getValue(), transOrder); // 设定
			if (isSuccess) {
				orderIdMap = transportOrderAppService.addOrder(url, addTransportOrderUrl, transOrder);// 创建订单
				addDeviceRecord(transOrder, DeviceStatus.USING.getValue());// 创建设备记录
				response.setData(orderIdMap);
			} else {
				response.setData(null);
				response.setMessage(HttpStatusCode.FAILED.getMessage());
				response.setStatus(HttpStatusCode.FAILED.getCode());
			}
		}
		return response;
	}

	private void addDeviceRecord(TransOrder transOrder, int status) {
		// TODO Auto-generated method stub
		DeviceManageAppResponse deviceManage = new DeviceManageAppResponse();
		Device device = deviceAppservice.findByNumber(url, findDeviceByNumberUrl, transOrder.getDeviceNumber());
		deviceManage.setDeviceId(device.getId());
		deviceManage.setLocation(transOrder.getDestination());
		deviceManage.setStatus(status);
		deviceChangeStatusAppService.addDeviceRecord(systemUrl, deviceManageInsertUrl, deviceManage);
	}

	private int checkDeviceNumberIsNull(Device device) {
		// TODO Auto-generated method stub
		int status = DeviceStatus.DISABLED.getKey();
		Response<Integer> selectCountResponse = null;
		try {
			selectCountResponse = iHttpClient.invokeHttp(url + selectCountByAttribute, device,
					new TypeReference<Response<Integer>>() {
					});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (selectCountResponse != null) {
			status = selectCountResponse.getData();
		}
		return status;
	}

	private TransOrder dealWithTransOrder(TransportOrderRequest request) {
		// TODO Auto-generated method stub
		TransOrder transOrder = new TransOrder();
		Address startPosition = request.getStartPosition();
		Address endPosition = request.getEndPosition();
		String origin = (startPosition == null ? null : JsonUtils.toJson(startPosition));
		String destination = (endPosition == null ? null : JsonUtils.toJson(endPosition));
		if (request.getDeviceId() != null) {
			transOrder.setDeviceNumber(request.getDeviceId());// 校验设备号是否存在
		}
		transOrder.setCarrierNumber(null);
		transOrder.setBillNumber(
				(request.getLadingId() == null || "".equals(request.getLadingId())) ? null : request.getLadingId());
		transOrder.setOrigin(origin);
		transOrder.setDestination(destination);
		transOrder.setLocationFreq(request.getLocateFrequency());
		transOrder.setTransMode(request.getTransportType());
		transOrder.setTransStatus(TransStatus.TRANSPORT_ING.getValue());
		transOrder.setStartTime(System.currentTimeMillis());
		transOrder.setFinishTime(null);
		transOrder.setStatus(YesNo.YES.getValue());
		transOrder.setRemark("备注");
		transOrder.setCreateTime(System.currentTimeMillis());
		transOrder.setCreatorId(RequestContext.getCompanyId());
		transOrder.setUpdatorId(null);
		transOrder.setUpdateTime(null);
		transOrder.setContainerId(request.getContainerId());
		if (FrequencyType.MIN.getKey().equals(request.getFrequencyType())) {
			transOrder.setLocationFreqType(FrequencyType.MIN.getValue());
		} else if (FrequencyType.HOUR.getKey().equals(request.getFrequencyType())) {
			transOrder.setLocationFreqType(FrequencyType.HOUR.getValue());
		} else if (FrequencyType.DAY.getKey().equals(request.getFrequencyType())) {
			transOrder.setLocationFreqType(FrequencyType.DAY.getValue());
		}
		return transOrder;
	}

	private boolean deviceProcessing(String imei, String deviceUrl, String processUrl, int status,
			TransOrder transOrder) {
		// TODO Auto-generated method stub
		boolean isstartUp = false;
		Response<Map<String, String>> deviceResponse = new Response<Map<String, String>>();
		// 唤醒或者关闭指定编号的设备
		if (processUrl != null) {
			try {
				DeviceShutdownRequest bizReq = new DeviceShutdownRequest();
				bizReq.setImei(imei);
				bizReq.setUserId(RequestContext.getUserId());
				deviceResponse = iHttpClient.invokeHttp(deviceUrl + processUrl, bizReq,
						new TypeReference<Response<Map<String, String>>>() {
						});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (deviceResponse != null) {
				if (deviceResponse.getStatus() == StatusCode.SUCCESS.getCode()) {
					isstartUp = true;
				}
			}
		} else {
			isstartUp = true;
		}
		if (isstartUp) {
			boolean flag = false;
			if (transOrder != null) {
				flag = sendLocationFreq(transOrder, imei);
			} else {
				flag = isNotOrderWorking(imei);
			}
			if (flag) {
				Device device = new Device();
				device.setNumber(imei);
				device.setStatus(status);
				// 成功唤醒设备，修改设备状态
				Response<Integer> response = null;
				try {
					response = iHttpClient.invokeHttp(url + updateDevice, device,
							new TypeReference<Response<Integer>>() {
							});
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (response != null) {
					return response.getData() != 0 ? true : false;
				}
			}
		}
		return false;
	}

	private boolean isNotOrderWorking(String imei) {
		// TODO Auto-generated method stub
		TransOrder transOrder = new TransOrder();
		transOrder.setDeviceNumber(imei);
		transOrder.setTransStatus(TransStatus.TRANSPORT_ING.getValue());
		Response<Integer> response = null;
		try {
			response = iHttpClient.invokeHttp(url + findTransportCountByTransUrl, transOrder,
					new TypeReference<Response<Integer>>() {
					});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (response.getData() > 0) {
			return false;
		}
		return true;
	}

	private boolean sendLocationFreq(TransOrder transOrder, String imei) {
		// TODO Auto-generated method stub
		Response<Map<String, String>> locationFreqResponse = new Response<Map<String, String>>();
		DeviceLocationFreqSetRequest deviceLocationFreqSetRequest = new DeviceLocationFreqSetRequest();
		int freq = dealwithFreq(transOrder);
		deviceLocationFreqSetRequest.setFreq(freq);
		deviceLocationFreqSetRequest.setImei(imei);
		deviceLocationFreqSetRequest.setUserId(RequestContext.getUserId());
		try {
			locationFreqResponse = iHttpClient.invokeHttp(deviceUrl + locationFreqUrl, deviceLocationFreqSetRequest,
					new TypeReference<Response<Map<String, String>>>() {
					});
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (locationFreqResponse.getStatus() == StatusCode.SUCCESS.getCode()) {
			return true;
		}
		return false;
	}

	private int dealwithFreq(TransOrder transOrder) {
		int locationFreq = transOrder.getLocationFreq() == 0 ? 1 : transOrder.getLocationFreq();
		int type = transOrder.getLocationFreqType() == 0 ? 1 : transOrder.getLocationFreqType();
		int freq = 1;
		// TODO Auto-generated method stub
		switch (type) {
		case 1:
			freq = 1 * locationFreq;
			break;
		case 2:
			freq = 60 * locationFreq;
			break;
		case 3:
			freq = 720 * locationFreq;
			break;
		}
		return freq;
	}

	/**
	 * 修改订单运输状态
	 * 
	 * @param request
	 *            需要修改的状态
	 * @return
	 */
	@RequestMapping(value = "/updateTransportStatus")
	public @ResponseBody Response<Object> upateTransportStatus(
			@RequestBody @RequestValidate Request<UpdateTransportStatusRequest> request) {
		Response<Object> response = new Response<Object>();
		response.setData(null);
		response.setMessage(HttpStatusCode.SUCCESS.getMessage());
		response.setStatus(HttpStatusCode.SUCCESS.getCode());
		UpdateTransportStatusRequest updateTransportStatusRequest = null;
		boolean isSuccess = false;
		int status = 0;
		if (request != null && request.getData() != null) {
			updateTransportStatusRequest = request.getData();
			TransOrder transOrder = new TransOrder();
			transOrder.setId(updateTransportStatusRequest.getId());
			transOrder.setTransStatus(updateTransportStatusRequest.getTransportStatus());
			if (updateTransportStatusRequest.getTransportStatus() == TransStatus.TRANSPORT_END.getValue()) {
				transOrder.setFinishTime(System.currentTimeMillis());
				// 如果查询到数据就就修改设备状态
				String imei = updateTransportStatusRequest.getDeviceId();
				isSuccess = deviceProcessing(imei, deviceUrl, deviceShutdownUrl, DeviceStatus.FINISH.getValue(), null);
			} else {
				isSuccess = true;
			}
			if (isSuccess) {
				status = transportOrderAppService.updateTransportStatus(url, updateTansportStatusUrl, transOrder);
				transOrder.setDestination(JsonUtils.toJson(updateTransportStatusRequest.getRecyclePosition()));
				transOrder.setDeviceNumber(updateTransportStatusRequest.getDeviceId());
				addDeviceRecord(transOrder, DeviceStatus.FINISH.getValue());
			}
		}
		if (status == 0) {
			response.setMessage(HttpStatusCode.FAILED.getMessage());
			response.setStatus(HttpStatusCode.FAILED.getCode());
		}
		return response;
	}

	/**
	 * 更新订单提单号
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateLadingId")
	public @ResponseBody Response<Object> updateLading(
			@RequestBody @RequestValidate Request<UpdateTransLadingIdRequest> request) {
		Response<Object> response = new Response<Object>();
		response.setData(null);
		response.setMessage(HttpStatusCode.SUCCESS.getMessage());
		response.setStatus(HttpStatusCode.SUCCESS.getCode());
		Response<Map<String, Integer>> trResponse = null;
		UpdateTransLadingIdRequest updateTransLadingIdRequest = null;
		if (request != null && request.getData() != null) {
			updateTransLadingIdRequest = request.getData();
			TransOrder transOrder = new TransOrder();
			transOrder.setId(updateTransLadingIdRequest.getId());
			transOrder.setBillNumber(updateTransLadingIdRequest.getLadingId());
			try {
				trResponse = iHttpClient.invokeHttp(url + updateLadingIdUrl, transOrder,
						new TypeReference<Response<Map<String, Integer>>>() {
						});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (trResponse != null && trResponse.getData() != null) {
			int status = trResponse.getData().get("status");
			if (status == 0) {
				response.setMessage(HttpStatusCode.FAILED.getMessage());
				response.setStatus(HttpStatusCode.FAILED.getCode());
			}
		} else {
			response.setMessage(HttpStatusCode.FAILED.getMessage());
			response.setStatus(HttpStatusCode.FAILED.getCode());
		}
		return response;
	}

	/**
	 * 订单历史记录（运输状态为已完成）
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/transportHistory")
	public @ResponseBody Response<Object> findTransportOrdersAsHistory(
			@RequestBody @RequestValidate Request<Object> request) {
		Long companyId = userAppService.findCompanyIdByUser(systemUrl, queryUser, RequestContext.getUserId());
		Response<Object> response = new Response<Object>();
		response.setMessage(HttpStatusCode.SUCCESS.getMessage());
		response.setStatus(HttpStatusCode.SUCCESS.getCode());
		TransOrder transOrder = new TransOrder();
		transOrder.setTransStatus(TransStatus.TRANSPORT_END.getValue());
		transOrder.setCreatorId(companyId);
		Response<List<TransOrder>> transOrderResponse = null;
		Pager pager = (request != null && request.getPager() != null) ? request.getPager() : new Pager(1, 10);
		int count = 0;
		try {
			transOrderResponse = iHttpClient.invokeHttp(url + transportListAsHistory, transOrder, pager,
					new TypeReference<Response<List<TransOrder>>>() {
					});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (transOrderResponse != null && transOrderResponse.getData()!=null && transOrderResponse.getData().size() > 0) {
			count = transOrderResponse.getCount();
			List<HistoryTransportResponse> responses = new ArrayList<HistoryTransportResponse>();
			for (TransOrder transOrder2 : transOrderResponse.getData()) {
				HistoryTransportResponse historyTransportResponse = new HistoryTransportResponse();
				historyTransportResponse.setId(transOrder2.getId());
				historyTransportResponse.setContainerId(transOrder2.getContainerId());
				historyTransportResponse.setStartPosition(transOrder2.getOrigin());
				historyTransportResponse.setEndPosition(transOrder2.getDestination());
				historyTransportResponse.setTransportType(transOrder2.getTransMode());
				historyTransportResponse.setTransportStatus(transOrder2.getTransStatus());
				historyTransportResponse.setCreateTime(transOrder2.getCreateTime());
				historyTransportResponse.setCompleteTime(transOrder2.getFinishTime());
				responses.add(historyTransportResponse);
			}
			response.setData(responses == null ? new JSONArray() : responses);
		} else {
			response.setData(new JSONArray());
		}
		response.setCount(count);
		return response;
	}

	/**
	 * 运输详情
	 * 
	 * @param request
	 *            订单id
	 * @return
	 */
	@RequestMapping(value = "/transportDetail")
	public @ResponseBody Response<Object> findTransportOrderDetail(
			@RequestBody @RequestValidate Request<Map<String, String>> request) {
		Response<Object> response = new Response<Object>();
		response.setMessage(HttpStatusCode.SUCCESS.getMessage());
		response.setStatus(HttpStatusCode.SUCCESS.getCode());
		Response<TransOrder> transOrderResponse = null;
		if (request != null && request.getData() != null) {
			TransOrder transOrder = new TransOrder();
			transOrder.setId(request.getData().get("id") == null ? null : Long.parseLong(request.getData().get("id")));
			try {
				transOrderResponse = iHttpClient.invokeHttp(url + findOrderByAttributeUrl, transOrder,
						new TypeReference<Response<TransOrder>>() {
						});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (transOrderResponse != null && transOrderResponse.getData() != null) {
			TransOrder transOrder2 = transOrderResponse.getData();
			Device device = new Device();
			if (transOrder2.getDeviceNumber() != null) {
				Response<Device> deviceResponse = null;
				device.setNumber(transOrder2.getDeviceNumber());
				try {
					deviceResponse = iHttpClient.invokeHttp(url + findDeviceByNumberUrl, device,
							new TypeReference<Response<Device>>() {
							});
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (deviceResponse != null && transOrderResponse.getData() != null) {
					device = deviceResponse.getData();
				}
			}
			TransportDetailResponse transportDetailResponse = new TransportDetailResponse();
			transportDetailResponse.setContainerId(transOrder2.getContainerId());
			transportDetailResponse.setLadingId(transOrder2.getBillNumber());
			transportDetailResponse.setDeviceId(transOrder2.getDeviceNumber());
			transportDetailResponse.setCreateTime(transOrder2.getCreateTime());
			transportDetailResponse.setCompleteTime(transOrder2.getFinishTime());
			transportDetailResponse.setDeviceStatus(device == null ? null : device.getStatus());
			transportDetailResponse.setLocateFrequency(transOrder2.getLocationFreq());
			transportDetailResponse.setStartPosition(JsonUtils.fromJson(transOrder2.getOrigin(), Address.class));
			transportDetailResponse.setEndPosition(JsonUtils.fromJson(transOrder2.getDestination(), Address.class));
			transportDetailResponse.setTransportStatus(transOrder2.getTransStatus());
			transportDetailResponse.setTransportType(transOrder2.getTransMode());
			transportDetailResponse.setFrequencyType(FrequencyType.getKey(transOrder2.getLocationFreqType()));
			response.setData(transportDetailResponse);
		} else {
			response.setData(new JSONObject());
		}
		return response;
	}

	/**
	 * 删除一条订单运输记录
	 * 
	 * @param request
	 *            订单id
	 * @return
	 */
	@RequestMapping("/delete")
	public @ResponseBody Response<Object> removeTransportOrder(
			@RequestBody @RequestValidate Request<Map<String, String>> request) {
		Response<Object> response = new Response<Object>();
		response.setData(null);
		response.setMessage(HttpStatusCode.SUCCESS.getMessage());
		response.setStatus(HttpStatusCode.SUCCESS.getCode());
		Response<Map<String, Integer>> trResponse = null;
		if (request != null && request.getData() != null) {
			TransOrder transOrder = new TransOrder();
			transOrder.setId(request.getData().get("id") == null ? null : Long.parseLong(request.getData().get("id")));
			try {
				trResponse = iHttpClient.invokeHttp(url + deleteTranportOrderUrl, transOrder,
						new TypeReference<Response<Map<String, Integer>>>() {
						});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (trResponse != null && trResponse.getData() != null) {
			int status = trResponse.getData().get("status");
			if (status == 0) {
				response.setMessage(HttpStatusCode.FAILED.getMessage());
				response.setStatus(HttpStatusCode.FAILED.getCode());
			}
		} else {
			response.setMessage(HttpStatusCode.FAILED.getMessage());
			response.setStatus(HttpStatusCode.FAILED.getCode());
		}
		return response;
	}

	/**
	 * 搜索运输记录
	 * 
	 * @param request
	 * @return
	 */

	@RequestMapping("/search")
	public @ResponseBody Response<Object> searchTransportOrder(
			@RequestBody @RequestValidate Request<Map<String, Object>> request) {
		Long companyId = userAppService.findCompanyIdByUser(systemUrl, queryUser, RequestContext.getUserId());
		Response<Object> response = new Response<Object>();
		response.setMessage(HttpStatusCode.SUCCESS.getMessage());
		response.setStatus(HttpStatusCode.SUCCESS.getCode());
		List<TransportOrderResponse> responses = new ArrayList<TransportOrderResponse>();
		Response<List<TransOrder>> transOrderResponse = null;
		Pager pager = request.getPager();
		int count = 0;
		if (request != null && request.getData() != null) {
			Map<String, Object> map = request.getData();
			SearchAttribute searchAttribute = new SearchAttribute();
			searchAttribute.setCompanyId(companyId);
			Set<Map.Entry<String, Object>> entryseSet = map.entrySet();
			for (Map.Entry<String, Object> entry : entryseSet) {
				if (entry.getKey().equals("startDate")) {
					searchAttribute.setStartDate((Long) entry.getValue());
				} else if (entry.getKey().equals("endDate")) {
					searchAttribute.setEndDate((Long) entry.getValue());
				} else if (entry.getKey().equals("startPosition")) {
					String json = JsonUtils.toJson(entry.getValue()).replace("\\", "");
					Address address = JsonUtils.fromJson(json, Address.class);
					searchAttribute.setStartPosition(address.getCity());
				} else if (entry.getKey().equals("endPosition")) {
					String json = JsonUtils.toJson(entry.getValue()).replace("\\", "");
					Address address = JsonUtils.fromJson(json, Address.class);
					searchAttribute.setEndPosition(address.getCity());
				} else if (entry.getKey().equals("transportType")) {
					searchAttribute.setTransportType(JsonUtils.fromJson((String) entry.getValue(), ArrayList.class));
				} else if (entry.getKey().equals("transportStatus")) {
					searchAttribute.setTransportStatus((Integer) entry.getValue());
				} else if (entry.getKey().equals("page")) {
					searchAttribute.setPage((Integer) entry.getValue());
				} else if (entry.getKey().equals("pageCount")) {
					searchAttribute.setPageCount((Integer) entry.getValue());
				}
			}
			try {
				transOrderResponse = iHttpClient.invokeHttp(url + findTrandportOrdersByAttributeUrl, searchAttribute,
						pager, new TypeReference<Response<List<TransOrder>>>() {
						});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (transOrderResponse != null && transOrderResponse.getData() != null) {
			count = transOrderResponse.getCount();
			for (TransOrder transOrder2 : transOrderResponse.getData()) {
				TransportOrderResponse transportOrderResponse = new TransportOrderResponse();
				transportOrderResponse.setId(transOrder2.getId());
				transportOrderResponse.setContainerId(transOrder2.getContainerId());
				transportOrderResponse.setStartPosition(transOrder2.getOrigin());
				transportOrderResponse.setEndPosition(transOrder2.getDestination());
				transportOrderResponse.setTransportType(transOrder2.getTransMode());
				transportOrderResponse.setTransportStatus(transOrder2.getTransStatus());
				transportOrderResponse.setCreateTime(transOrder2.getCreateTime());
				responses.add(transportOrderResponse);
			}
		}
		response.setData(responses.size() <= 0 ? new JSONArray() : responses);
		response.setCount(count);
		return response;
	}

	/**
	 * 查询号码：在未登录的情况下
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/checkId")
	public @ResponseBody Response<Object> searchChecked(@RequestBody Request<Map<String, String>> request) {
		Response<Object> response = new Response<Object>();
		response.setStatus(HttpStatusCode.SUCCESS.getCode());
		response.setMessage(HttpStatusCode.SUCCESS.getMessage());
		List<TransportIdResponse> transportIdResponses = new ArrayList<TransportIdResponse>();
		Response<List<TransOrder>> transordersResponse = null;
		if (request != null && request.getData() != null) {
			try {
				transordersResponse = iHttpClient.invokeHttp(url + findIdByKeywordUrl, request.getData(),
						new TypeReference<Response<List<TransOrder>>>() {
						});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (transordersResponse != null && transordersResponse.getData() != null) {
			for (TransOrder transOrder : transordersResponse.getData()) {
				TransportIdResponse transportIdResponse = new TransportIdResponse();
				transportIdResponse.setId(transOrder.getId());
				transportIdResponse.setLadingId(transOrder.getBillNumber());
				transportIdResponse.setDeviceId(transOrder.getDeviceNumber());
				transportIdResponse.setContainerId(transOrder.getContainerId());
				transportIdResponses.add(transportIdResponse);
			}
		}
		response.setData(transportIdResponses.isEmpty() ? new JSONArray() : transportIdResponses);
		return response;
	}

	/**
	 * 查询号码：在已经登录的情况下
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/checkNumber")
	public @ResponseBody Response<Object> searchCheckedNumber(
			@RequestBody @RequestValidate Request<Map<String, String>> request) {
		Long companyId = RequestContext.getCompanyId();
		Response<Object> response = new Response<Object>();
		response.setStatus(HttpStatusCode.SUCCESS.getCode());
		response.setMessage(HttpStatusCode.SUCCESS.getMessage());
		List<TransportIdResponse> transportIdResponses = new ArrayList<TransportIdResponse>();
		Response<List<TransOrder>> transordersResponse = null;
		if (request != null && request.getData() != null) {
			try {
				transordersResponse = iHttpClient.invokeHttp(url + findIdByCheckNumberUrl, request.getData(),
						new TypeReference<Response<List<TransOrder>>>() {
						});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (transordersResponse != null && transordersResponse.getData() != null) {
			for (TransOrder transOrder : transordersResponse.getData()) {
				if(companyId ==transOrder.getCreatorId()) {
					TransportIdResponse transportIdResponse = new TransportIdResponse();
					transportIdResponse.setId(transOrder.getId());
					transportIdResponse.setLadingId(transOrder.getBillNumber());
					transportIdResponse.setDeviceId(transOrder.getDeviceNumber());
					transportIdResponse.setContainerId(transOrder.getContainerId());
					transportIdResponses.add(transportIdResponse);
				}
			}
		}
		response.setData(transportIdResponses.isEmpty() ? new JSONArray() : transportIdResponses);
		return response;
	}

}
