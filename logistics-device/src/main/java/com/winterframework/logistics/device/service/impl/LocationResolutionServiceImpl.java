package com.winterframework.logistics.device.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.winterframework.logistics.base.enums.StatusCode;
import com.winterframework.logistics.base.enums.YesNo;
import com.winterframework.logistics.base.utils.JsonUtils;
import com.winterframework.logistics.common.entity.DeviceLocation;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.device.dto.GoogleLocationOperatorResults;
import com.winterframework.logistics.device.service.ILocationResolutionService;
import com.winterframework.logistics.device.service.scheduler.location.AmapUtil;
import com.winterframework.logistics.device.service.scheduler.location.GoogleUtils;
import com.winterframework.logistics.device.service.scheduler.location.Regeocode;

@Service("locationResolutionServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class LocationResolutionServiceImpl implements ILocationResolutionService {
	protected static Logger log = LoggerFactory.getLogger(LocationResolutionServiceImpl.class);

	/**
	 * 优先高德地理反解析，如果解析结果为空则进行google地理反解析
	 * 
	 * @throws Exception
	 */
	@Override
	public DeviceLocation resolution(Double lat, Double lng,boolean isResolutionByGaoDe) throws LmException {
		DeviceLocation location = new DeviceLocation();
		boolean flag = false;
		if(isResolutionByGaoDe) {
			try {
				Regeocode regeocode = AmapUtil.getCityNameByLocation(lng + "," + lat);
				log.info("Geocoding gps logitude=" + lng + " latitude=" + lat + " result:" + JsonUtils.toJson(regeocode));

				if (regeocode == null) {
					flag = true;
				} else {
					location.setAddress(regeocode.getFormatted_address());
					location.setStatus(YesNo.YES.getValue());
					location.setRemark("Gaode geocoding from amap.");
					if (regeocode.getFormatted_address() == null || regeocode.getFormatted_address().length() == 0) {
						flag = true;
					}
				}
				if (flag) {
					location=googleLocation(lat,lng,location);
					location.setRemark("Google geocoding from amap.");
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error("google地址反解析出现错误   ， lat:" + lat + "lng:" + lng);
				throw new LmException(StatusCode.DAO_ERROR, e);
			}
		}else {
			try {
				location=googleLocation(lat,lng,location);
				location.setRemark("Google geocoding from amap.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error("google地址反解析出现错误   ， lat:" + lat + "lng:" + lng);
				throw new LmException(StatusCode.DAO_ERROR, e);
			}
		}
		return location;
	}

	private DeviceLocation googleLocation(Double lat, Double lng, DeviceLocation location) throws Exception {
		// TODO Auto-generated method stub
		GoogleLocationOperatorResults[] googleLocationOperatorResults = GoogleUtils
				.getCityNameByGoogleGeocode(lat + "," + lng);
		log.info("Geocoding google logitude=" + lat + " latitude=" + lng + " result:"
				+ JsonUtils.toJson(googleLocationOperatorResults));

		if (googleLocationOperatorResults == null) {
			location.setAddress("UNKOWN");
		} else {
			location.setAddress(googleLocationOperatorResults[0].getFormatted_address());
			location.setStatus(YesNo.YES.getValue());
		}
		return location;
	}

//	public static void main(String[] args) {
//		
//		double lng =119.39776459999997;
//		double lat = 34.74320290000001;
//		DeviceLocation location = new DeviceLocation();
//		boolean flag = false;
//		boolean isResolutionByGaoDe=false;
//		if(isResolutionByGaoDe) {
//			try {
//				Regeocode regeocode = AmapUtil.getCityNameByLocation(lng + "," + lat);
//			System.out.println("Geocoding gps logitude=" + lng + " latitude=" + lat + " result:" + JsonUtils.toJson(regeocode));
//
//				if (regeocode == null) {
//					flag = true;
//				} else {
//					location.setAddress(regeocode.getFormatted_address());
//					location.setStatus(YesNo.YES.getValue());
//					location.setRemark("Gaode geocoding from amap.");
//					if (regeocode.getFormatted_address() == null || regeocode.getFormatted_address().length() == 0) {
//						flag = true;
//					}
//				}
//				if (flag) {
//					location=googleLocation(lat,lng,location);
//					location.setRemark("Google geocoding from amap.");
//				}
//				
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				log.error("google地址反解析出现错误   ， lat:" + lat + "lng:" + lng);
//				
//			}
//		}else {
//			try {
//				location=googleLocation(lat,lng,location);
//				location.setRemark("Google geocoding from amap.");
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				log.error("google地址反解析出现错误   ， lat:" + lat + "lng:" + lng);
//				
//			}
//		}
//		System.out.println("解析后的地址："+location.getAddress());
//	}
}
