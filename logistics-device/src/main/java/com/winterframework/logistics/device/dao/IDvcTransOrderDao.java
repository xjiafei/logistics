package com.winterframework.logistics.device.dao;

import com.winterframework.logistics.common.dao.ITransOrderDao;
import com.winterframework.logistics.common.entity.TransOrder;

public interface IDvcTransOrderDao extends ITransOrderDao {

	TransOrder getByDeviceNumberAndTime(String deviceNumber, Long time) throws Exception;
	

}
