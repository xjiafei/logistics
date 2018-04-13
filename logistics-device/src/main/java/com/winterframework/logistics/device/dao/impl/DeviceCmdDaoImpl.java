package com.winterframework.logistics.device.dao.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winterframework.logistics.base.dao.BaseDaoImpl;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.device.dao.IDeviceCmdDao;
import com.winterframework.logistics.device.entity.DeviceCmd;
import com.winterframework.logistics.device.entity.DeviceCmd.ExecStatus;
@Repository("deviceCmdDaoImpl")
public class DeviceCmdDaoImpl<E extends DeviceCmd>   extends BaseDaoImpl<DeviceCmd> implements IDeviceCmdDao{

	@Override
	public DeviceCmd getEarliestOneByImeiAndFunctionAndExecStatus(String imei, String function,List<ExecStatus> execStatusList)
			throws Exception {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("imei", imei);
		map.put("function", function);
		map.put("execStatusList", getExecStatusValueList(execStatusList));
		return this.sqlSessionTemplate.selectOne(getQueryPath("getEarliestOneByImeiAndFunctionAndExecStatus"), map);
	}
	@Override
	public List<String> getImeisByExecStatus(List<ExecStatus> execStatusList) throws LmException {
		return this.sqlSessionTemplate.selectList(getQueryPath("getImeisByExecStatus"), getExecStatusValueList(execStatusList));
	}
	@Override
	public List<DeviceCmd> getListByImeiAndExecStatus(String imei, List<ExecStatus> execStatusList) throws Exception {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("imei", imei);
		map.put("execStatusList", getExecStatusValueList(execStatusList));
		
		return this.sqlSessionTemplate.selectList(getQueryPath("getListByImeiAndExecStatus"), map);
	}
	private List<Integer> getExecStatusValueList(List<ExecStatus> execStatusList){
		List<Integer> list=new ArrayList<Integer>();
		for(ExecStatus execStatus:execStatusList){
			list.add(execStatus.getValue());
		}
		return list;
	}
}
