package com.winterframework.logistics.system.user.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.winterframework.logistics.base.enums.YesNo;
import com.winterframework.logistics.base.model.Request;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.base.utils.JsonUtils;
import com.winterframework.logistics.common.entity.City;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.common.service.ICityService;

@Controller("cityController")
@RequestMapping("/system/city")
public class CityController {
	private static Logger logger = LoggerFactory.getLogger(CityController.class);
	@Resource(name="cityServiceImpl")
	ICityService cityservice;
	
	@RequestMapping("/insertCitys")
	public @ResponseBody Response<List<Long>> insertCitys(@RequestBody Request<List<String>> request) throws LmException{
		logger.info("insertCitys ----request:"+JsonUtils.toJson(request));
		Response<List<Long>> response=new Response<List<Long>>();
		List<Long> idList=new ArrayList<Long>();
		List<String> cityNames =new ArrayList<String>();
		cityNames.addAll(request.getData());
//		去重复
		List<City> citys=cityservice.checkIsContains(cityNames);
		for(int i=0;i<citys.size();i++) {
			if(cityNames.contains(citys.get(i).getName())) {
				cityNames.remove(citys.get(i).getName());
			}
		}
		List<City> newCitys=new ArrayList<City>();
		for (String cityName : cityNames) {
			City city=new City();
			city.setName(cityName);
			city.setNumber(cityName);
			city.setCreateTime(System.currentTimeMillis());
			city.setCreatorId(1L);
			city.setStatus(YesNo.YES.getValue());
			newCitys.add(city);
		}
		if(newCitys!=null && newCitys.size()>0) {
			cityservice.insertCitys(newCitys);	
		}
		List<City>cityList=cityservice.checkIsContains(request.getData());
		for (City city : cityList) {
			idList.add(city.getId());
		}
		response.setData(idList);
		return response;
	}
}
