package com.winterframework.logistics.portal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.winterframework.logistics.base.http.RequestContext;
import com.winterframework.logistics.base.model.Request;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.portal.dto.PtlBizTypeWebResponse;
import com.winterframework.logistics.portal.dto.PtlBizWebRequest;
import com.winterframework.logistics.portal.dto.PtlBizWebResponse;
import com.winterframework.logistics.portal.entity.Biz;
import com.winterframework.logistics.portal.entity.BizType;
import com.winterframework.logistics.portal.enums.EPtlBiz;
import com.winterframework.logistics.portal.exception.PortalException;
import com.winterframework.logistics.portal.service.IPtlBizService;
import com.winterframework.logistics.portal.service.IPtlBizTypeService;

@Controller("ptlBizController")
@RequestMapping("/portal/biz")
public class PtlBizController {
	private static final Logger log = LoggerFactory.getLogger(PtlBizController.class);

	@Resource(name = "ptlBizTypeServiceImpl")
	private IPtlBizTypeService ptlBizTypeService;

	@Resource(name = "ptlBizServiceImpl")
	private IPtlBizService ptlBizService;
	
	/**
	 * 业务类型
	 * @param request
	 * @return
	 * @throws PortalException
	 */
	@RequestMapping("/showBizType")
	public @ResponseBody Response<List<PtlBizTypeWebResponse>> showBizType(
			@RequestBody Request<Map<String, String>> request) throws PortalException {
		log.info("PtlBizController: /portal/biz/showBizType");
		Response<List<PtlBizTypeWebResponse>> response = new Response<List<PtlBizTypeWebResponse>>();
		List<BizType> bizTypes = ptlBizTypeService.getEntitys(EPtlBiz.BIZ_TYPE_STATUS_AVAILABLE.getValue());
		List<Biz> bizs = ptlBizService.getEntitysByBizTypes(bizTypes);
		List<PtlBizTypeWebResponse> ptlBizTypeWebResponses = new ArrayList<PtlBizTypeWebResponse>();
		for (BizType bizType : bizTypes) {
			PtlBizTypeWebResponse ptlBizTypeWebResponse = new PtlBizTypeWebResponse();
			ptlBizTypeWebResponse.setId(bizType.getId());
			for (Biz biz : bizs) {
				if (biz.getBizTypeId().equals(bizType.getId())) {
					ptlBizTypeWebResponse.setBizId(biz.getId());
				}
			}
			ptlBizTypeWebResponse.setName(bizType.getName());
			ptlBizTypeWebResponse.setNumber(bizType.getNumber());
			ptlBizTypeWebResponse.setStatus(bizType.getStatus());
			ptlBizTypeWebResponses.add(ptlBizTypeWebResponse);
		}
		response.setData(ptlBizTypeWebResponses);
		return response;
	}
	
	/**
	 * 业务详情
	 * @param request
	 * @return
	 * @throws PortalException
	 */
	@RequestMapping("/showBizDetails")
	public @ResponseBody Response<PtlBizWebResponse> showBizDetails(@RequestBody Request<Map<String, Long>> request)
			throws PortalException {
		log.info("PtlBizController: /portal/biz/showBizDetails");
		Long id = Optional.ofNullable(request.getData().get("id")).orElse(0L);
		Response<PtlBizWebResponse> response = new Response<PtlBizWebResponse>();
		Biz biz = ptlBizService.getEntity(id);
		BizType bizType = ptlBizTypeService.getEntity(biz.getBizTypeId());
		PtlBizWebResponse ptlBizWebResponse = new PtlBizWebResponse();
		ptlBizWebResponse.setId(biz.getId());
		ptlBizWebResponse.setBizTypeName(bizType.getName());
		ptlBizWebResponse.setBizTypeNumber(bizType.getNumber());
		ptlBizWebResponse.setBizTypeId(biz.getBizTypeId());
		ptlBizWebResponse.setBizIntro(biz.getBizIntro());
		ptlBizWebResponse.setCreateTime(biz.getCreateTime());
		response.setData(ptlBizWebResponse);
		return response;
	}
	
	/**
	 * 新增业务
	 * @param request
	 * @return
	 * @throws PortalException
	 */
	@RequestMapping("/addBiz")
	public @ResponseBody Response<Object> addBiz(@RequestBody Request<PtlBizWebRequest> request) throws PortalException{
		log.info("PtlBizController: /portal/biz/addBiz");
		Long userId = RequestContext.getUserId();
		PtlBizWebRequest ptlBizWebRequest=request.getData();
		BizType bizType=new BizType();
		bizType.setName(ptlBizWebRequest.getBizName());
		bizType.setNumber("0003");
		bizType.setStatus(EPtlBiz.BIZ_TYPE_STATUS_AVAILABLE.getValue());
		bizType.setCreateTime(System.currentTimeMillis());
		bizType.setCreatorId(userId);
		BizType bType=ptlBizTypeService.addBizType(bizType);
		Biz biz=new Biz();
		biz.setBizIntro(ptlBizWebRequest.getBizIntro());
		biz.setBizTypeId(bType.getId());
		biz.setStatus(EPtlBiz.BIZ_TYPE_STATUS_AVAILABLE.getValue());
		biz.setCreateTime(System.currentTimeMillis());
		biz.setCreatorId(userId);
		int result=ptlBizService.addBiz(biz);
		Response<Object> response = new Response<>();
		response.setStatus(result);
		return response;
		
	}
}
