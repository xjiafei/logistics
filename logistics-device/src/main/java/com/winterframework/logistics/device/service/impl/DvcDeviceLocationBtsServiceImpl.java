package com.winterframework.logistics.device.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winterframework.logistics.base.enums.StatusCode;
import com.winterframework.logistics.base.enums.YesNo;
import com.winterframework.logistics.base.utils.DateUtils;
import com.winterframework.logistics.common.entity.DeviceLocationBts;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.common.service.impl.DeviceLocationBtsServiceImpl;
import com.winterframework.logistics.device.dao.IDvcDeviceLocationBtsDao;
import com.winterframework.logistics.device.service.IDvcDeviceLocationBtsService;

/**
 * 设备定位（Lbs）服务
 * 
 * @ClassName
 * @Description
 * @author ibm 2017年11月29日
 */
@Service("dvcDeviceLocationBtsServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class DvcDeviceLocationBtsServiceImpl extends DeviceLocationBtsServiceImpl
		implements IDvcDeviceLocationBtsService {
	protected Logger log = LoggerFactory.getLogger(getClass());

	@Resource(name = "dvcDeviceLocationLbsDaoImpl")
	private IDvcDeviceLocationBtsDao dvcDeviceLocationLbsDao;

	@Override
	public List<DeviceLocationBts> queryUnhandleList(Date fromTime, Date toTime) throws LmException {
		try {
			return dvcDeviceLocationLbsDao.getListByHandleflagAndTimes(YesNo.NO.getValue(), fromTime.getTime(),
					toTime.getTime());
		} catch (Exception e) {
			log.error("getListByHandleflagAndTimes error. fromTime=" + fromTime + " toTime=" + toTime, e);
			throw new LmException(StatusCode.DAO_ERROR, e);
		}
	}

	@Override
	public List<DeviceLocationBts> queryListByImeiAndTimes(String imei, Date fromTime, Date toTime) throws LmException {
		try {
			return dvcDeviceLocationLbsDao.getListByImeiAndTimes(imei, fromTime.getTime(), toTime.getTime());
		} catch (Exception e) {
			log.error("queryList error. fromTime=" + fromTime + " toTime=" + toTime, e);
			throw new LmException(StatusCode.DAO_ERROR, e);
		}
	}

	@Override
	public List<DeviceLocationBts> getByIds(List<Long> idList) {
		// TODO Auto-generated method stub
		return dvcDeviceLocationLbsDao.getByIds(idList);
	}

	@Override
	public void addBtsList(List<String> strList, String imei, List<String> timeList) throws LmException {
		// TODO Auto-generated method stub
		List<DeviceLocationBts> dlbList = new ArrayList<DeviceLocationBts>();
		for (int i = 0; i < strList.size(); i++) {
			if (Pattern.compile("^\\*MG[0-9]{0,20}\\,BA&X.*\\#$").matcher(strList.get(i)).matches()) {
				String[] newStrArray = strList.get(i).substring(strList.get(i).split("X")[0].length() + 1).split("&")[0]
						.split(";");
				if (newStrArray.length >= 1) {
					DeviceLocationBts deviceLocationBts = new DeviceLocationBts();
					String[] firstStrArray = newStrArray[0].split(",");
					if (firstStrArray.length >= 5) {
						deviceLocationBts.setMcc(firstStrArray[0]);
						deviceLocationBts.setMnc(firstStrArray[1]);
						deviceLocationBts.setLac1(firstStrArray[2]);
						deviceLocationBts.setCi1(firstStrArray[3]);
						deviceLocationBts.setRssi1(firstStrArray[4]);
					}
					if (newStrArray.length >= 2) {
						String[] scStrArray = newStrArray[1].split(",");
						if (scStrArray.length >= 3) {
							deviceLocationBts.setLac2(scStrArray[0]);
							deviceLocationBts.setCi2(scStrArray[1]);
							deviceLocationBts.setRssi2(scStrArray[2]);
						}
						if (newStrArray.length >= 3) {
							String[] thrStrArray = newStrArray[2].split(",");
							if (thrStrArray.length >= 3) {
								deviceLocationBts.setLac3(thrStrArray[0]);
								deviceLocationBts.setCi3(thrStrArray[1]);
								deviceLocationBts.setRssi3(thrStrArray[2]);
							}
							if (newStrArray.length >= 4) {
								String[] fruStrArray = newStrArray[3].split(",");
								if (fruStrArray.length >= 3) {
									deviceLocationBts.setLac4(fruStrArray[0]);
									deviceLocationBts.setCi4(fruStrArray[1]);
									deviceLocationBts.setRssi4(fruStrArray[2]);
								}

								if (newStrArray.length >= 5) {
									String[] fivStrArray = newStrArray[4].split(",");
									if (fivStrArray.length >= 3) {
										deviceLocationBts.setLac5(fivStrArray[0]);
										deviceLocationBts.setCi5(fivStrArray[1]);
										deviceLocationBts.setRssi5(fivStrArray[2]);
									}
								}
							}
						}
					}
					Long times = DateUtils.parse(timeList.get(i), DateUtils.DATETIME_JSVIEW_FORMAT_PATTERN).getTime();
					deviceLocationBts.setTime(times);
					deviceLocationBts.setHandleFlag(0);
					deviceLocationBts.setCreateTime(times);
					deviceLocationBts.setCreatorId(-2L);
					deviceLocationBts.setImei(imei);
					deviceLocationBts.setStatus(1);
					dlbList.add(deviceLocationBts);

				}

			}

		}
		try {
			dvcDeviceLocationLbsDao.insertBatch(dlbList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
