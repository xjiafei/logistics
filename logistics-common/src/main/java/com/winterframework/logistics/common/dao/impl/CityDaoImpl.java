package com.winterframework.logistics.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.winterframework.logistics.base.dao.BaseDaoImpl;
import com.winterframework.logistics.common.dao.ICityDao;
import com.winterframework.logistics.common.entity.City;
@Repository("cityDaoImpl")
public class CityDaoImpl <E extends City>  extends BaseDaoImpl<City> implements ICityDao{

	@Override
	public List<City> selectCityListById(List<Long> idList) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(getQueryPath("getListByIdList"), idList);
	}
	
	@Override
	public List<City> checkIsContains(List<String> cityNames) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(getQueryPath("getEntitysByName"), cityNames);
	}

	@Override
	public int insertCitys(List<City> citys) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.insert(getQueryPath("insertEntitys"),citys);
	}
}
