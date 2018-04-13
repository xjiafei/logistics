package com.winterframework.logistics.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winterframework.logistics.base.service.BaseServiceImpl;
import com.winterframework.logistics.common.dao.ICityDao;
import com.winterframework.logistics.common.entity.City;
import com.winterframework.logistics.common.service.ICityService;
@Service("cityServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class CityServiceImpl extends BaseServiceImpl<ICityDao,City> implements ICityService {
	@Resource(name="cityDaoImpl")
	ICityDao iCityDao;
	
	@Override
	protected ICityDao getEntityDao() {
		// TODO Auto-generated method stub
		return iCityDao;
	}

	@Override
	public City findCityByAttribute(City city) {
		// TODO Auto-generated method stub
		try {
			return iCityDao.selectByAttribute(city);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return city;
	}

	@Override
	public List<City> findCityListByCityId(List<Long> idList) {
		// TODO Auto-generated method stub
		return iCityDao.selectCityListById(idList);
	}

	@Override
	public List<City> checkIsContains(List<String> cityNames) {
		// TODO Auto-generated method stub
		return iCityDao.checkIsContains(cityNames);
	}

	@Override
	public int insertCitys(List<City> citys) {
		// TODO Auto-generated method stub
		return iCityDao.insertCitys(citys);
	}


}
