package com.winterframework.logistics.common.service;

import java.util.List;

import com.winterframework.logistics.base.service.IBaseService;
import com.winterframework.logistics.common.entity.City;

public interface ICityService extends IBaseService<City>{
	public City findCityByAttribute(City city);
	
	public List<City> findCityListByCityId(List<Long> idList);

	public List<City> checkIsContains(List<String> cityNames);

	public int insertCitys(List<City> citys);
}
