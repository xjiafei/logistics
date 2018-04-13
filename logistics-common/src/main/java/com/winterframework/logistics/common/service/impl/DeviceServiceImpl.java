package com.winterframework.logistics.common.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winterframework.logistics.base.enums.StatusCode;
import com.winterframework.logistics.base.service.BaseServiceImpl;
import com.winterframework.logistics.common.dao.IDeviceDao;
import com.winterframework.logistics.common.entity.Device;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.common.service.IDeviceService;
import com.winterframework.modules.web.jsonresult.Pager;

@Service("deviceServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class DeviceServiceImpl  extends BaseServiceImpl<IDeviceDao,Device> implements IDeviceService{
	
	@Resource(name="deviceDaoImpl")
	IDeviceDao deviceDao;
	
	@Override
	protected IDeviceDao getEntityDao() {
		// TODO Auto-generated method stub
		return deviceDao;
	}
	
	@Override
	public Device getVO(Device device) {
		// TODO Auto-generated method stub
		try {
			return deviceDao.selectByAttribute(device);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return device;
	}

	@Override
	public Device getByNumber(String number) {
		// TODO Auto-generated method stub
		Device device = null;
		try {
			device=deviceDao.getByNumber(number);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return device;
	}

	@Override
	public List<Device> findDeviceListByAttribute(Device device, Pager pager, int count) {
		// TODO Auto-generated method stub
		try {
			return deviceDao.selectListByAttribute(device,pager,count);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Device>();
	}

	@Override
	public List<Device> getListByNumbers(List<String> numbers) {
		// TODO Auto-generated method stub
		return deviceDao.getListByNumbers(numbers);
	}

	@Override
	public Integer updateDevice(Device device) {
		// TODO Auto-generated method stub
		return deviceDao.updateDevice(device);
	}

	@Override
	public Integer getCountByAttribute(Device device) {
		// TODO Auto-generated method stub
		return deviceDao.getCountByAttribute(device);
	}
	
	@Override
	public List<Device> queryListByStatus(Integer status) throws LmException {
		try {
			return deviceDao.getListByStatus(status);
		} catch (Exception e) {
			log.error("dao error.",e);
			throw new LmException(StatusCode.DAO_ERROR, e);
		}
	}

	@Override
	public int getCountByNumberCode(Device device) {
		// TODO Auto-generated method stub
		return deviceDao.getCountByNumberCode(device);
	}

	@Override
	public List<Device> findDeviceListByNumberCode(Device device, Pager pager, int count) {
		// TODO Auto-generated method stub
		return deviceDao.getDeviceListByNumberCode(device,pager,count);
	}
	
}
