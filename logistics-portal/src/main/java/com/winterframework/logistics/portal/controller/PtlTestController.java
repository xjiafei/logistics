package com.winterframework.logistics.portal.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.winterframework.logistics.base.aop.annotation.RequestValidate;
import com.winterframework.logistics.base.enums.StatusCode;
import com.winterframework.logistics.base.http.RequestContext;
import com.winterframework.logistics.base.model.Context;
import com.winterframework.logistics.base.model.Request;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.portal.exception.PortalException;
import com.winterframework.logistics.portal.service.IPtlTestService;
 
@Controller("ptlTestController")
@RequestMapping("/portal/test")
public class PtlTestController {
	private static final Logger log = LoggerFactory.getLogger(PtlTestController.class);
	@Resource(name = "ptlTestServiceImpl")
	private IPtlTestService ptlTestService;
	
	@RequestMapping("/test")
	@ResponseBody
	public Object test(@RequestBody @RequestValidate Request<String> req) throws PortalException{
		Response<String> res=new Response<String>();  
		Context ctx=new Context(RequestContext.getUserId());
		ptlTestService.test(ctx);
		res.setStatus(StatusCode.SUCCESS.getCode());
		return res;
	}
	
}
