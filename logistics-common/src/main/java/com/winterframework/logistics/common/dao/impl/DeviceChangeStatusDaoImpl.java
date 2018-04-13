package com.winterframework.logistics.common.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import com.winterframework.logistics.base.dao.BaseDaoImpl;
import com.winterframework.logistics.common.dao.IDeviceChangeStatusDao;
import com.winterframework.logistics.common.entity.DeviceChangeStatus;
import com.winterframework.logistics.common.entity.TransOrder;
import com.winterframework.modules.page.Page;
import com.winterframework.modules.web.jsonresult.Pager;

@Repository("deviceChangeStatusDaoImpl")
public class DeviceChangeStatusDaoImpl <E extends DeviceChangeStatus>  extends BaseDaoImpl<DeviceChangeStatus> implements IDeviceChangeStatusDao {

	@Override
	public int insertOne(DeviceChangeStatus data) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.insert(getQueryPath("insert"),data);
	}

	@Override
	public int getCountByAttribute(DeviceChangeStatus data) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(getQueryPath("getCountByAttribute"), data);
	}

	@Override
	public List<DeviceChangeStatus> selectListByAttribute(DeviceChangeStatus deviceManage, Pager pager, int count) {
		// TODO Auto-generated method stub
		List<DeviceChangeStatus> deviceChangeStatus = null;
		if (count == 0 || count < pager.getEndNo()) {
			deviceChangeStatus = sqlSessionTemplate.selectList(getQueryPath("getByAttribute"), deviceManage);
		} else {
			RowBounds rowBounds = dealWithRowBounds(pager, count);
			deviceChangeStatus = sqlSessionTemplate.selectList(getQueryPath("getByAttribute"), deviceManage, rowBounds);
		}
		return deviceChangeStatus;
	}
	public RowBounds dealWithRowBounds(Pager pager, int count) {
		Page<TransOrder> page = new Page<TransOrder>(pager.getStartNo(), pager.getEndNo(), count);
		return new RowBounds(page.getFirstResult(), page.getPageSize());
	}
}
