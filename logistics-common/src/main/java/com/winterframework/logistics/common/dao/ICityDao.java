package com.winterframework.logistics.common.dao;

import java.util.List;

import com.winterframework.logistics.base.dao.IBaseDao;
import com.winterframework.logistics.common.entity.City;

public interface ICityDao extends IBaseDao<City>{
	
	public List<City> selectCityListById(List<Long> idList);

	public List<City> checkIsContains(List<String> cityNames);

	public int insertCitys(List<City> citys);

}