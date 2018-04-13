package com.winterframework.logistics.device.service;

import com.winterframework.logistics.common.entity.DeviceLocationBts;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.device.dto.GoogleDocumentationResponse;

public interface IBtsResolutionService {

	GoogleDocumentationResponse resolution(DeviceLocationBts bts) throws LmException;

}
