package com.winterframework.logistics.device.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winterframework.logistics.base.enums.StatusCode;
import com.winterframework.logistics.base.enums.YesNo;
import com.winterframework.logistics.common.entity.DeviceLocationGps;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.common.service.impl.DeviceLocationGpsServiceImpl;
import com.winterframework.logistics.device.dao.IDvcDeviceLocationGpsDao;
import com.winterframework.logistics.device.service.IDvcDeviceLocationGpsService;

/**
 * 设备定位（GPS）服务
 * @ClassName
 * @Description
 * @author ibm
 * 2017年11月29日
 */
@Service("dvcDeviceLocationGpsServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class DvcDeviceLocationGpsServiceImpl extends DeviceLocationGpsServiceImpl implements IDvcDeviceLocationGpsService {
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource(name="dvcDeviceLocationGpsDaoImpl")
	private IDvcDeviceLocationGpsDao dvcDeviceLocationGpsDao;
	
	@Override
	public List<DeviceLocationGps> queryUnhandleList(Date fromTime, Date toTime) throws LmException {
		try{
			return dvcDeviceLocationGpsDao.getListByHandleflagAndTimes(YesNo.NO.getValue(), fromTime.getTime(), toTime.getTime());
		}catch(Exception e){
			log.error("getListByHandleflagAndTimes error. fromTime="+fromTime+" toTime="+toTime,e);
			throw new LmException(StatusCode.DAO_ERROR,e);
		}
	}
	@Override
	public List<DeviceLocationGps> queryListByImeiAndTimes(String imei,Date fromTime, Date toTime) throws LmException {
		try{
			return dvcDeviceLocationGpsDao.getListByImeiAndTimes(imei,fromTime.getTime(), toTime.getTime());
		}catch(Exception e){
			log.error("queryList error. fromTime="+fromTime+" toTime="+toTime,e);
			throw new LmException(StatusCode.DAO_ERROR,e);
		}
	}
}
