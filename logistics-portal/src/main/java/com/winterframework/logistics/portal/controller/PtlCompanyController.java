package com.winterframework.logistics.portal.controller;

import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.winterframework.logistics.base.model.Request;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.portal.dto.PtlCompanyWebResponse;
import com.winterframework.logistics.portal.entity.Company;
import com.winterframework.logistics.portal.exception.PortalException;
import com.winterframework.logistics.portal.service.IPtlCompanyService;

@Controller("ptlCompanyController")
@RequestMapping("/portal/company")
public class PtlCompanyController {

	private static final Logger log = LoggerFactory.getLogger(PtlCompanyController.class);

	@Resource(name = "ptlCompanyServiceImpl")
	private IPtlCompanyService ptlCompanyService;
	/**
	 * 公司信息
	 * @param request
	 * @return
	 * @throws PortalException
	 */
	@RequestMapping("/Intro")
	public @ResponseBody Response<PtlCompanyWebResponse> companyIntro(@RequestBody Request<Map<String, String>> request)
			throws PortalException {
		log.info("ptlCompanyController:/portal/Intro");
		Company company = ptlCompanyService.getEntity();
		Response<PtlCompanyWebResponse> response = new Response<PtlCompanyWebResponse>();
		PtlCompanyWebResponse ptlCompanyWebResponse = new PtlCompanyWebResponse();
		ptlCompanyWebResponse.setId(company.getId());
		ptlCompanyWebResponse.setCooper(company.getCooper());
		ptlCompanyWebResponse.setIntro(company.getIntro());
		ptlCompanyWebResponse.setLogo(company.getLogo());
		ptlCompanyWebResponse.setName(company.getName());
		ptlCompanyWebResponse.setPhilo(company.getPhilo());
		ptlCompanyWebResponse.setHomePics(Optional.ofNullable(company.getHomePics())
				.orElse("null.jpg").split(","));
		response.setData(ptlCompanyWebResponse);
		return response;
	}

}
