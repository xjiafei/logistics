package com.winterframework.logistics.device.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winterframework.logistics.base.enums.StatusCode;
import com.winterframework.logistics.common.entity.TransOrder;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.common.service.impl.TransportOrderServiceImpl;
import com.winterframework.logistics.device.dao.IDvcTransOrderDao;
import com.winterframework.logistics.device.service.IDvcTransOrderService;

/**
 * 运输单服务
 * @ClassName
 * @Description
 * @author ibm
 * 2017年11月29日
 */
@Service("dvcTransOrderServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class DvcTransOrderServiceImpl extends TransportOrderServiceImpl implements IDvcTransOrderService {
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource(name="dvcTransOrderDaoImpl")
	private IDvcTransOrderDao dvcTransOrderDao;
	
	
	@Override
	public TransOrder queryByDeviceNumberAndTime(String deviceNumber,Long time) throws LmException {
		try{
			return dvcTransOrderDao.getByDeviceNumberAndTime(deviceNumber, time);
		}catch(Exception e){
			log.error("dao error.",e);
			throw new LmException(StatusCode.DAO_ERROR);
		}
	}
}
