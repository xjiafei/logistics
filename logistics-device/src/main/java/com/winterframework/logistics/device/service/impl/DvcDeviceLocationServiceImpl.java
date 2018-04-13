package com.winterframework.logistics.device.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winterframework.logistics.base.exception.BizException;
import com.winterframework.logistics.base.http.HttpUtil;
import com.winterframework.logistics.base.utils.DateUtils;
import com.winterframework.logistics.base.utils.JsonUtils;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.common.service.IUserService;
import com.winterframework.logistics.common.service.impl.DeviceLocationServiceImpl;
import com.winterframework.logistics.device.service.IDvcDeviceLocationService;
import com.winterframework.logistics.device.trdparty.vo.Track;
import com.winterframework.modules.spring.exetend.PropertyConfig;

/**
 * 设备定位服务
 * @ClassName
 * @Description
 * @author ibm
 * 2017年11月29日
 */
@Service("dvcDeviceLocationServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class DvcDeviceLocationServiceImpl extends DeviceLocationServiceImpl implements IDvcDeviceLocationService {
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource(name="userServiceImpl")
	private IUserService userService;
	@PropertyConfig(value="location.service.server.url")
	private String locationServerUrl;
	@PropertyConfig(value="location.service.action.track")
	private String locationActionTrack;
	
	private final String DATE_FULL_FORMAT_PATTERN = "yyyy-MM-ddHH:mm:ss";
	@Override
	public List<Track> queryTrack(String imei, String key, Date startTime, Date endTime, boolean isExcludeLbs)
			throws LmException {
		Map<String,String> params=new HashMap<String,String>();
		params.put("imei",imei);
		params.put("key",key);
		params.put("startTime",DateUtils.format(startTime, DATE_FULL_FORMAT_PATTERN));
		params.put("endTime",DateUtils.format(endTime, DATE_FULL_FORMAT_PATTERN));
		params.put("isExcludeLbs",isExcludeLbs?"1":"0");
		try {
			String resultStr=HttpUtil.http(locationServerUrl, locationActionTrack, params);
			return JsonUtils.fromJson2List(resultStr, Track.class);
		} catch (BizException e) {
			log.error("api failed.",e);
		}
		return null;
	}
}
