package com.winterframework.logistics.common.service;


import java.util.List;

import com.winterframework.logistics.base.service.IBaseService;
import com.winterframework.logistics.common.entity.Device;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.modules.web.jsonresult.Pager;


public interface IDeviceService extends IBaseService<Device> {
	
	public Device getVO(Device device);
	
	public Device getByNumber(String number);
	
	public List<Device> findDeviceListByAttribute(Device device, Pager pager, int count);
	
	public List<Device> getListByNumbers(List<String> numbers);
	
	public Integer updateDevice(Device device);
	
	public Integer getCountByAttribute(Device device);
	
	List<Device> queryListByStatus(Integer status) throws LmException;

	public int getCountByNumberCode(Device device);

	public List<Device> findDeviceListByNumberCode(Device device, Pager pager, int count);
	
	
	
}
