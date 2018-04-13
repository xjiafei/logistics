package com.winterframework.logistics.common.dao;

import java.util.List;

import com.winterframework.logistics.base.dao.IBaseDao;
import com.winterframework.logistics.common.entity.Device;
import com.winterframework.modules.web.jsonresult.Pager;

public interface IDeviceDao  extends IBaseDao<Device>{
	Device getByNumber(String number);
	
	List<Device> getListByNumbers(List<String> numbers);
	
	Integer getCountByAttribute(Device device);
	
	List<Device> getListByStatus(Integer status) throws Exception;

	List<Device> selectListByAttribute(Device device, Pager pager, int count);

	int getCountByNumberCode(Device device);

	List<Device> getDeviceListByNumberCode(Device device, Pager pager, int count);

	Integer updateDevice(Device device);
}