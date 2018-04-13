package com.winterframework.logistics.transport.dao;

import com.winterframework.logistics.transport.dao.vo.DeviceVO;
import com.winterframework.logistics.transport.entity.Device;

public interface DeviceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Device record);

    int insertSelective(Device record);

    DeviceVO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);
}