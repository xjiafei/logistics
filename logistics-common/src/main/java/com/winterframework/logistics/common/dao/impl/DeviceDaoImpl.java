package com.winterframework.logistics.common.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import com.winterframework.logistics.base.dao.BaseDaoImpl;
import com.winterframework.logistics.common.dao.IDeviceDao;
import com.winterframework.logistics.common.entity.Device;
import com.winterframework.logistics.common.entity.TransOrder;
import com.winterframework.modules.page.Page;
import com.winterframework.modules.web.jsonresult.Pager;

@Repository("deviceDaoImpl")
public class DeviceDaoImpl<E extends Device> extends BaseDaoImpl<Device> implements IDeviceDao {

	@Override
	public Device getByNumber(String number) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(getQueryPath("getByNumber"), number);
	}

	@Override
	public List<Device> getListByNumbers(List<String> numbers) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(getQueryPath("getListByNumbers"), numbers);
	}

	@Override
	public Integer getCountByAttribute(Device device) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(getQueryPath("getCountByAttribute"), device);
	}

	@Override
	public List<Device> getListByStatus(Integer status) throws Exception {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(getQueryPath("getListByStatus"), status);
	}

	@Override
	public List<Device> selectListByAttribute(Device device, Pager pager, int count) {
		// TODO Auto-generated method stub
		List<Device> devices = null;
		if (count == 0 || count < pager.getEndNo()) {
			devices = sqlSessionTemplate.selectList(getQueryPath("getByAttribute"), device);
		} else {
			RowBounds rowBounds = dealWithRowBounds(pager, count);
			devices = sqlSessionTemplate.selectList(getQueryPath("getByAttribute"), device, rowBounds);
		}
		return devices;
	}

	public RowBounds dealWithRowBounds(Pager pager, int count) {
		Page<TransOrder> page = new Page<TransOrder>(pager.getStartNo(), pager.getEndNo(), count);
		return new RowBounds(page.getFirstResult(), page.getPageSize());
	}

	@Override
	public int getCountByNumberCode(Device device) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(getQueryPath("getCountByCode"), device);
	}

	@Override
	public List<Device> getDeviceListByNumberCode(Device device, Pager pager, int count) {
		// TODO Auto-generated method stub
		List<Device> devices = null;
		if (count == 0 || count < pager.getEndNo()) {
			devices = sqlSessionTemplate.selectList(getQueryPath("getBySearchCode"), device);
		} else {
			RowBounds rowBounds = dealWithRowBounds(pager, count);
			devices = sqlSessionTemplate.selectList(getQueryPath("getBySearchCode"), device, rowBounds);
		}
		return devices;
	}

	@Override
	public Integer updateDevice(Device device) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.update(getQueryPath("update"), device);
	}

}
