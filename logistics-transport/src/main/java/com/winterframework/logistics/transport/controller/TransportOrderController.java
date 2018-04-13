package com.winterframework.logistics.transport.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.winterframework.logistics.base.model.Request;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.common.entity.SearchAttribute;
import com.winterframework.logistics.common.entity.TransOrder;
import com.winterframework.logistics.common.enums.ContainerStatus;
import com.winterframework.logistics.common.service.IDeviceLocationService;
import com.winterframework.logistics.common.service.ITransportOrderService;
import com.winterframework.modules.web.jsonresult.Pager;

@Controller("transportOrderController")
@RequestMapping("/transport/order")
public class TransportOrderController {
	private static Logger logger = LoggerFactory.getLogger(TransportOrderController.class);

	@Resource(name = "transOrderServiceImpl")
	ITransportOrderService transportOrderService;

	@Resource(name = "deviceLocationServiceImpl")
	IDeviceLocationService iDeviceLocationService;

	/**
	 * 展示运输列表（状态为运输中）
	 * 
	 * @return
	 */
	@RequestMapping("/transportList")
	public @ResponseBody Response<List<TransOrder>> findAllTransportOrderList(
			@RequestBody Request<TransOrder> request) {
		logger.info("/transport/order/transportList");
		Response<List<TransOrder>> response = new Response<List<TransOrder>>();
		List<TransOrder> transOrderList = null;
		int count=0;
		if (request != null && request.getData() != null) {
			Pager pager=request.getPager();
			count=transportOrderService.getCount(request.getData());
			transOrderList = transportOrderService.findListTransportOrderByPager(request.getData(), pager,count);
		}
	
		if (transOrderList.size() > 0) {
			response.setData(transOrderList);
			response.setCount(count);
		}
		return response;
	}
	
	@RequestMapping("/transportListAsHistory")
	public @ResponseBody Response<List<TransOrder>> findAllHistoryTransportOrderList(
			@RequestBody Request<TransOrder> request) {
		logger.info("/transport/order/transportListAsHistory");
		Response<List<TransOrder>> response = new Response<List<TransOrder>>();
		List<TransOrder> transOrderList = null;
		int count=0;
		if (request != null && request.getData() != null) {
			Pager pager=request.getPager();
			count=transportOrderService.getCount(request.getData());
			transOrderList = transportOrderService.getHistoryByAttribute(request.getData(), pager,count);
		}
	
		if (transOrderList.size() > 0) {
			response.setData(transOrderList);
			response.setCount(count);
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
	public @ResponseBody Response<List<TransOrder>> searchTransportOrder(
			@RequestBody Request<SearchAttribute> request) {
		logger.info("/transport/order/search");
		Response<List<TransOrder>> response = new Response<List<TransOrder>>();
		int count=0;
		List<TransOrder> transOrders =null;
		if (request != null && request.getData() != null) {
			Pager pager=request.getPager();
			count=transportOrderService.searchListCount(request.getData());
			transOrders = transportOrderService.searchByAttribute(request.getData(),pager,count);	
		}
		if (transOrders != null && transOrders.size() > 0) {
			response.setData(transOrders);
			response.setCount(count);
		}
		return response;
	}
	/**
	 * 添加一条数据
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/insertTransportData")
	public @ResponseBody Response<Map<String, Long>> addTransportOrder(@RequestBody Request<TransOrder> request) {
		logger.info("/transport/order/addTransportOrder");
		Response<Map<String, Long>> response = new Response<Map<String, Long>>();
		Long id = 0L;
		if (request != null && request.getData() != null) {
			TransOrder transOrder = request.getData();		
			id=transportOrderService.addTransportOrder(transOrder);
			Map<String, Long> map = new HashMap<String, Long>();
			map.put("id", id);
			response.setData(map);
		}
		return response;
	}


	/**
	 * 修改运输状态
	 * 
	 * @param request
	 */
	@RequestMapping(value = "/updateTansportStatus")
	public @ResponseBody Response<Object> upateTransportStatus(@RequestBody Request<TransOrder> request) {
		logger.info("/transport/order/updateStatus");
		Response<Object> response = new Response<Object>();
		int status = 0;
		if (request != null && request.getData() != null) {
			status = transportOrderService.updateTransportStatus(request.getData());
		}
		response.setStatus(status);
		return response;
	}

	/**
	 * 更新提单号
	 * 
	 * @return
	 */
	@RequestMapping("/updateLadingId")
	public @ResponseBody Response<Map<String, Integer>> updateLading(@RequestBody Request<TransOrder> request) {
		logger.info("/transport/order/updateLadingId");
		Response<Map<String, Integer>> response = new Response<Map<String, Integer>>();
		Map<String, Integer> map = new HashMap<String, Integer>();
		int status = 0;
		if (request != null && request.getData() != null) {
			status = transportOrderService.updateTransportStatus(request.getData());
		}
		map.put("status", status);
		response.setData(map);
		return response;
	}


	/**
	 * 删除一条运输记录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/delete")
	public @ResponseBody Response<Map<String, Integer>> removeTransportOrder(@RequestBody Request<TransOrder> request) {
		logger.info("/transport/order/delete");
		Response<Map<String, Integer>> response = new Response<Map<String, Integer>>();
		Map<String, Integer> map = new HashMap<String, Integer>();
		int status = 0;
		if (request != null && request.getData() != null) {
			status = transportOrderService.deleteTransport(request.getData());
		}
		map.put("status", status);
		response.setData(map);
		return response;
	}



	/**
	 * 查询号码，条件是在未登录的情况下
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/checkId")
	public @ResponseBody Response<List<TransOrder>> searchChecked(@RequestBody Request<Map<String, String>> request) {
		logger.info("/transport/order/checkId");
		Response<List<TransOrder>> response = new Response<List<TransOrder>>();
		if (request != null && !request.getData().isEmpty()) {
			response.setData(transportOrderService.searchByCode(request.getData().get("code")));
		}
		return response;
	}
	
	/**
	 * 查询号码，条件是在已经登录的情况下
	 * @param request
	 * @return
	 */
	@RequestMapping("/checkNumber")
	public @ResponseBody Response<List<TransOrder>> searchTransOrderByNumber(@RequestBody Request<Map<String, String>> request){
		logger.info("/transport/order/checkNumber");
		Response<List<TransOrder>> response = new Response<List<TransOrder>>();
		List<TransOrder> transOrders=null;
		if (request != null && request.getData()!=null ) {
			String code=(request.getData()==null)? "":request.getData().get("code");
			transOrders=transportOrderService.searchTransportOrderByNumber(code);
		}
		response.setData(transOrders);
		return response;
	}

	@RequestMapping("/findByAttribute")
	public @ResponseBody Response<TransOrder> findById(@RequestBody Request<TransOrder> request) {
		logger.info("/transport/order/findByAttribute");
		Response<TransOrder> response = new Response<TransOrder>();
		TransOrder transOrder = null;
		if (request.getData() != null) {
			transOrder = transportOrderService.findByAttribute(request.getData());
		}
		response.setData(transOrder);
		return response;
	}
	
	@RequestMapping("/findTransportCountByTrans")
	public @ResponseBody Response<Integer> findTransportCountByTrans(@RequestBody Request<TransOrder> request){
		logger.info("/transport/order/findTransportCountByTrans");
		Response<Integer> response=new Response<Integer>();
		int num=0;
		if(request.getData()==null) {
			num=transportOrderService.getCount(request.getData());
		}
		response.setData(num);
		return response;
		
	}
}
