package com.winterframework.logistics.device.service;


import java.util.List;

import com.winterframework.logistics.base.model.Context;
import com.winterframework.logistics.base.service.IBaseService;
import com.winterframework.logistics.common.entity.Device;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.device.entity.DeviceCmd;

public interface IDeviceCmdService extends IBaseService<DeviceCmd> {
	/**获取未执行最早的指令
	 * @param imei
	 * @param function
	 * @return
	 * @throws LmException
	 */
	DeviceCmd queryEarliestOneNotClosed(String imei,String function) throws LmException;
	/**获取未关闭的指令imei集合
	 * @return
	 * @throws LmException
	 */
	List<String> queryImeisNotClosed() throws LmException;
	/**获取未关闭指令列表
	 * @param imei
	 * @return
	 * @throws LmException
	 */
	List<DeviceCmd> queryListNotClosedByImei(String imei) throws LmException;
	/**
	 * 指令执行成功
	 * @param ctx
	 * @param imei
	 * @param function
	 * @throws LmException
	 */
	void success(Context ctx,String imei,String function) throws LmException;
	/**
	 * 指令发送
	 * @param cmd
	 * @return 是否执行中（等待响应)
	 * @throws LmException
	 */
	boolean send(DeviceCmd cmd,Device device) throws LmException;
}
