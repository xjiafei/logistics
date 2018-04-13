package com.winterframework.logistics.transport.dao.impl;


import org.springframework.stereotype.Repository;

import com.winterframework.logistics.transport.dao.DeviceMapper;
import com.winterframework.logistics.transport.dao.vo.DeviceVO;
import com.winterframework.logistics.transport.entity.Device;
import com.winterframework.orm.dal.ibatis3.BaseIbatis3Dao;
@Repository("deviceMapperImpl")
public class DeviceMapperImpl extends BaseIbatis3Dao<DeviceVO> implements DeviceMapper{

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Device record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Device record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DeviceVO selectByPrimaryKey(Long id) {
		 DeviceVO device= this.sqlSessionTemplate.selectOne(this.getQueryPath("selectByPrimaryKey"), id);
		
		return device;
	}

	@Override
	public int updateByPrimaryKeySelective(Device record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Device record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
