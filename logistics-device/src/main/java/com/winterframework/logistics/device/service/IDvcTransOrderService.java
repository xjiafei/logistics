package com.winterframework.logistics.device.service;

import com.winterframework.logistics.common.entity.TransOrder;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.common.service.ITransportOrderService;

public interface IDvcTransOrderService extends ITransportOrderService{
	public TransOrder queryByDeviceNumberAndTime(String deviceNumber,Long time) throws LmException;

}
