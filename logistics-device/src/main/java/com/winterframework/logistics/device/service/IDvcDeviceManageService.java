package com.winterframework.logistics.device.service;

import com.winterframework.logistics.base.model.Context;
import com.winterframework.logistics.common.exception.LmException;

public interface IDvcDeviceManageService{
	/**
	 * 开启设备
	 * @param imei
	 * @throws LmException
	 */
	void startup(Context ctx,String imei) throws LmException;
	/**
	 * 关闭设备
	 * @param imei
	 * @throws LmException
	 */
	void shutdown(Context ctx,String imei) throws LmException;
	/**
	 * 设置设备定位频率
	 * @param imei
	 * @param freq
	 * @throws LmException
	 */
	void locationFreqSett(Context ctx,String imei,Integer freq) throws LmException;
	
	

}
