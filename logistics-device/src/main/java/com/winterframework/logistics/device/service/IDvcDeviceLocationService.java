package com.winterframework.logistics.device.service;

import java.util.Date;
import java.util.List;

import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.common.service.IDeviceLocationService;
import com.winterframework.logistics.device.trdparty.vo.Track;

public interface IDvcDeviceLocationService extends IDeviceLocationService{
	public List<Track> queryTrack(String imei,String key,Date startTime,Date endTime,boolean isExcludeLbs) throws LmException;
}
