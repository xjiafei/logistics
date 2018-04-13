package com.winterframework.logistics.device.service;

import com.winterframework.logistics.common.entity.DeviceLocation;
import com.winterframework.logistics.common.exception.LmException;

public interface ILocationResolutionService {

	DeviceLocation resolution(Double lat, Double lng) throws LmException;

}
