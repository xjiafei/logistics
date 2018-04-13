package com.winterframework.logistics.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winterframework.logistics.base.service.BaseServiceImpl;
import com.winterframework.logistics.common.dao.IDeviceChangeStatusDao;
import com.winterframework.logistics.common.entity.DeviceChangeStatus;
import com.winterframework.logistics.common.service.IDeviceChangeStatusService;
import com.winterframework.modules.web.jsonresult.Pager;
@Service("deviceChangeStatusServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class DeviceChangeStatusServiceImpl extends BaseServiceImpl<IDeviceChangeStatusDao,DeviceChangeStatus> implements IDeviceChangeStatusService {
	@Resource(name="deviceChangeStatusDaoImpl")
	private IDeviceChangeStatusDao deviceChangeStatusDao;
	@Override
	protected IDeviceChangeStatusDao getEntityDao() {
		// TODO Auto-generated method stub
		return deviceChangeStatusDao;
	}

	@Override
	public List<DeviceChangeStatus> findListByAttribute(DeviceChangeStatus deviceManage, Pager pager, int count) {
		// TODO Auto-generated method stub
		try {
			return deviceChangeStatusDao.selectListByAttribute(deviceManage,pager,count);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insert(DeviceChangeStatus data) {
		// TODO Auto-generated method stub
		return deviceChangeStatusDao.insertOne(data);
	}

	@Override
	public int getCountByattribute(DeviceChangeStatus data) {
		// TODO Auto-generated method stub
		return deviceChangeStatusDao.getCountByAttribute(data);
	}

	
}
