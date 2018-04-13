package com.winterframework.logistics.device.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.winterframework.logistics.base.enums.YesNo;
import com.winterframework.logistics.base.exception.BizException;
import com.winterframework.logistics.base.model.Context;
import com.winterframework.logistics.base.utils.DateUtils;
import com.winterframework.logistics.base.utils.JsonUtils;
import com.winterframework.logistics.common.entity.Device;
import com.winterframework.logistics.common.entity.DeviceLocation;
import com.winterframework.logistics.common.entity.DeviceLocationBts;
import com.winterframework.logistics.common.entity.DeviceLocationGps;
import com.winterframework.logistics.common.entity.DeviceLocationSemi;
import com.winterframework.logistics.common.entity.TransOrder;
import com.winterframework.logistics.common.enums.StatusCode;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.common.service.IDeviceLocationSemiService;
import com.winterframework.logistics.common.service.IDeviceService;
import com.winterframework.logistics.device.dto.GoogleDocumentationResponse;
import com.winterframework.logistics.device.dto.GoogleLocation;
import com.winterframework.logistics.device.service.ILocationResolutionService;
import com.winterframework.logistics.device.service.IBtsResolutionService;
import com.winterframework.logistics.device.service.IDvcDeviceLocationBtsService;
import com.winterframework.logistics.device.service.IDvcDeviceLocationGpsService;
import com.winterframework.logistics.device.service.IDvcDeviceLocationParseService;
import com.winterframework.logistics.device.service.IDvcDeviceLocationService;
import com.winterframework.logistics.device.service.IDvcTransOrderService;
import com.winterframework.logistics.device.service.scheduler.location.AmapUtil;
import com.winterframework.logistics.device.service.scheduler.location.GaoDeLocationStruc;
import com.winterframework.logistics.device.service.scheduler.location.Gps2Amap;
import com.winterframework.logistics.device.service.scheduler.location.LatLng;
import com.winterframework.logistics.device.service.scheduler.location.Regeocode;

/**
 * 设备定位数据解析服务
 * 
 * @ClassName
 * @Description
 * @author ibm 2017年11月29日
 */
@Service("dvcDeviceLocationParseServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class DvcDeviceLocationParseServiceImplBK implements IDvcDeviceLocationParseService {
	protected Logger log = LoggerFactory.getLogger(getClass());

	@Resource(name = "dvcDeviceLocationGpsServiceImpl")
	private IDvcDeviceLocationGpsService dvcDeviceLocationGpsService;
	@Resource(name = "dvcDeviceLocationBtsServiceImpl")
	private IDvcDeviceLocationBtsService dvcDeviceLocationBtsService;
	@Resource(name = "dvcDeviceLocationServiceImpl")
	private IDvcDeviceLocationService dvcDeviceLocationService;
	@Resource(name = "deviceServiceImpl")
	private IDeviceService deviceService;
	@Resource(name = "dvcTransOrderServiceImpl")
	private IDvcTransOrderService dvcTransOrderService;

	@Resource(name = "deviceLocationSemiServiceImpl")
	private IDeviceLocationSemiService deviceLocationSemiService;

	@Resource(name = "locationResolutionServiceImpl")
	private ILocationResolutionService locationResolutionService;
	@Resource(name = "btsResolutionServiceImpl")
	private IBtsResolutionService btsResolutionService;

	@Override
	public void executeGps() {
		Date fromDate = DateUtils.addHours(DateUtils.currentDate(), -6);
		Date toDate = DateUtils.currentDate();
		try {
			List<DeviceLocationGps> gpsList = dvcDeviceLocationGpsService.queryUnhandleList(fromDate, toDate);
			List<DeviceLocation> locationList = new ArrayList<DeviceLocation>();
			List<DeviceLocationGps> locationGpsList = new ArrayList<DeviceLocationGps>();
			for (DeviceLocationGps gps : gpsList) {
				try {
					Device device = deviceService.getByNumber(gps.getImei());
					if (device == null) {
						throw new LmException(StatusCode.IMEI_INVALID);
					}
					TransOrder order = dvcTransOrderService.queryByDeviceNumberAndTime(device.getNumber(),
							gps.getTime());
					if (order == null) {
						throw new LmException(StatusCode.HAS_NO_ORDER);
					}
					DeviceLocation location = new DeviceLocation();
					location.setDeviceId(device.getId());
					location.setOrderId(order.getId());
					location.setLongitude(gps.getLongitude());
					location.setLatitude(gps.getLatitude());
					location.setRadius((int) (gps.getFactor() / 1000));
					location.setTime(gps.getTime());
					location.setTimeStay(0);
					location.setSourceType(DeviceLocation.SourceType.GPS.getValue());
					location.setSourceId(gps.getId());
					location.setStatus(YesNo.NO.getValue());
					location.setRemark("geocoding from amap.");
					LatLng latLng = Gps2Amap.transWGS2GCJ(new LatLng(gps.getLatitude(), gps.getLongitude()));
					try {
						Regeocode regeocode = AmapUtil.getCityNameByLocation(latLng.longitude + "," + latLng.latitude);
						log.info("Geocoding gps logitude=" + latLng.longitude + " latitude=" + latLng.latitude
								+ " result:" + JsonUtils.toJson(regeocode));
						if (regeocode == null) {
							location.setAddress("UNKOWN");
						} else {
							location.setAddress(regeocode.getFormatted_address());
							// location.setCity((regeocode.getAddressComponent().getCity()==null||regeocode.getAddressComponent().getCity()=="")?regeocode.getAddressComponent().getProvince():regeocode.getAddressComponent().getCity());
							location.setStatus(YesNo.YES.getValue());
						}
					} catch (Exception e) {
						log.error("parse gps address failed.", e);
						location.setAddress("UNKOWN");
					}
					gps.setRemark("success");
					locationList.add(location);
				} catch (Exception e) {
					String emsg = BizException.getStackTraceMsg(e);
					if (e instanceof LmException) {
						log.error("parse gps location failed.status=" + ((LmException) e).getCode() + " msg="
								+ ((LmException) e).getMessage(), e);
					} else {
						log.error("parse gps location failed.", e);
					}
					gps.setRemark("failed:" + emsg.substring(0, 200));
				}
				gps.setHandleFlag(YesNo.YES.getValue());
				locationGpsList.add(gps);
			}
			dvcDeviceLocationService.save(new Context(-1L), locationList);
			dvcDeviceLocationGpsService.save(new Context(-1L), locationGpsList);
		} catch (Exception e) {
			log.error("device location parse error.fromDate=" + fromDate + " toDate=" + toDate, e);
		}
	}

	@Override
	public void executeBts() {
		final String key = "a445886dba2e781c569899bf0478670c";
		final String baseUrl = "http://apilocate.amap.com/position?key=" + key + "&";
		Date fromDate = DateUtils.addHours(DateUtils.currentDate(), -6);
		Date toDate = DateUtils.currentDate();
		try {
			List<DeviceLocationBts> lbsList = dvcDeviceLocationBtsService.queryUnhandleList(fromDate, toDate);
			List<DeviceLocation> locationList = new ArrayList<DeviceLocation>();
			List<DeviceLocationBts> locationBtsList = new ArrayList<DeviceLocationBts>();
			for (DeviceLocationBts bts : lbsList) {
				try {
					Device device = deviceService.getByNumber(bts.getImei());
					if (device == null) {
						throw new LmException(StatusCode.IMEI_INVALID);
					}
					TransOrder order = dvcTransOrderService.queryByDeviceNumberAndTime(device.getNumber(),
							bts.getTime());
					if (order == null) {
						throw new LmException(StatusCode.HAS_NO_ORDER);
					}
					DeviceLocation location = new DeviceLocation();
					location.setDeviceId(device.getId());
					location.setOrderId(order.getId());
					location.setTime(bts.getTime());
					location.setTimeStay(0);
					location.setSourceType(DeviceLocation.SourceType.BTS.getValue());
					location.setSourceId(bts.getId());
					location.setStatus(YesNo.NO.getValue());
					location.setRemark("geocoding from amap.");
					try {
						StringBuffer url = new StringBuffer();
						url.append(baseUrl);
						url.append("&accesstype=0");
						url.append("&imei=" + device.getNumber());
						url.append("&cdma=0");
						url.append("&bts=" + bts.getMcc() + "," + bts.getMnc() + "," + bts.getLac1() + ","
								+ bts.getCi1() + "," + bts.getRssi1());
						if (bts.getLac2() != null) {
							url.append("&nearbts=" + bts.getMcc() + "," + bts.getMnc() + "," + bts.getLac2() + ","
									+ bts.getCi2() + "," + bts.getRssi2());
							if (bts.getLac3() != null) {
								url.append("|" + bts.getMcc() + "," + bts.getMnc() + "," + bts.getLac3() + ","
										+ bts.getCi3() + "," + bts.getRssi3());
								if (bts.getLac4() != null) {
									url.append("|" + bts.getMcc() + "," + bts.getMnc() + "," + bts.getLac4() + ","
											+ bts.getCi4() + "," + bts.getRssi4());
									if (bts.getLac5() != null) {
										url.append("|" + bts.getMcc() + "," + bts.getMnc() + "," + bts.getLac5() + ","
												+ bts.getCi5() + "," + bts.getRssi5());
									}
								}
							}
						}
						url.append("&output=json");
						GaoDeLocationStruc result = AmapUtil.getGaoDeLocation(url.toString());
						log.info("Geocoding bts request:" + url.toString() + " result:" + JsonUtils.toJson(result));
						if (result != null) {
							LatLng latlng = new LatLng(
									result.getLocation().split(",")[1] + "," + result.getLocation().split(",")[0]);
							latlng = Gps2Amap.transGCJ2WGS(latlng);
							location.setLongitude(latlng.getLongitude());
							location.setLatitude(latlng.getLatitude());
							location.setRadius((result.getRadius() != null && Integer.valueOf(result.getRadius()) < 500
									&& Integer.valueOf(result.getRadius()) > 0) ? Integer.valueOf(result.getRadius())
											: 500);
							if (null != result.getDesc()) {
								location.setAddress(result.getDesc().replace(" ", ""));
								// location.setCity((regeocode.getAddressComponent().getCity()==null||regeocode.getAddressComponent().getCity()=="")?regeocode.getAddressComponent().getProvince():regeocode.getAddressComponent().getCity());
								location.setStatus(YesNo.YES.getValue());
							}
						} else {
							location.setLongitude(0.0);
							location.setLatitude(0.0);
						}
					} catch (Exception e) {
						location.setLongitude(0.0);
						location.setLatitude(0.0);
						log.error("parse bts address failed.", e);
					}
					bts.setRemark("success");
					locationList.add(location);
				} catch (Exception e) {
					String emsg = BizException.getStackTraceMsg(e);
					if (e instanceof LmException) {
						log.error("parse bts location failed.status=" + ((LmException) e).getCode() + " msg="
								+ ((LmException) e).getMessage(), e);
					} else {
						log.error("parse bts location failed.", e);
					}
					bts.setRemark("failed:" + emsg.substring(0, 200));
				}
				bts.setHandleFlag(YesNo.YES.getValue());
				locationBtsList.add(bts);
			}
			dvcDeviceLocationService.save(new Context(-1L), locationList);
			dvcDeviceLocationBtsService.save(new Context(-1L), locationBtsList);
		} catch (Exception e) {
			log.error("device location parse error.fromDate=" + fromDate + " toDate=" + toDate);
		}

	}

	public void executeGoogleReGeocodeByGps() {
		// TODO Auto-generated method stub
		Date fromDate = DateUtils.addHours(DateUtils.currentDate(), -6);
		Date toDate = DateUtils.currentDate();
		try {
			List<DeviceLocationGps> gpsList = dvcDeviceLocationGpsService.queryUnhandleList(fromDate, toDate);
			List<DeviceLocationSemi> locationSemiList = new ArrayList<DeviceLocationSemi>();
			List<DeviceLocationGps> locationGpsList = new ArrayList<DeviceLocationGps>();
			for (DeviceLocationGps gps : gpsList) {
				try {
					Device device = deviceService.getByNumber(gps.getImei());
					if (device == null) {
						throw new LmException(StatusCode.IMEI_INVALID);
					}
					TransOrder order = dvcTransOrderService.queryByDeviceNumberAndTime(device.getNumber(),
							gps.getTime());
					if (order == null) {
						throw new LmException(StatusCode.HAS_NO_ORDER);
					}
					DeviceLocationSemi locationSemi = new DeviceLocationSemi();
					locationSemi.setImei(gps.getImei());
					locationSemi.setLongitude(gps.getLongitude());
					locationSemi.setLatitude(gps.getLatitude());
					locationSemi.setRadius((int) (gps.getFactor() / 1000));
					// locationSemi.setDistance();

					locationSemi.setTimeBegin(gps.getTime());
					locationSemi.setTimeEnd(gps.getTime());
					locationSemi.setSourceType(DeviceLocationSemi.SourceType.GPS.getValue());
					locationSemi.setSourceId(gps.getId());
					locationSemi.setStatus(YesNo.NO.getValue());
					locationSemi.setHandleStatus(DeviceLocationSemi.HandleStatus.INIT.getValue());
					// LatLng latLng = Gps2Amap.transWGS2GCJ(new LatLng(gps.getLatitude(),
					// gps.getLongitude()));
					/*
					 * DeviceLocation location = new DeviceLocation();
					 * location.setDeviceId(device.getId()); location.setOrderId(order.getId());
					 * location.setLongitude(gps.getLongitude());
					 * location.setLatitude(gps.getLatitude()); location.setRadius((int)
					 * (gps.getFactor() / 1000)); location.setTime(gps.getTime());
					 * location.setTimeStay(0);
					 * location.setSourceType(DeviceLocation.SourceType.GPS.getValue());
					 * location.setSourceId(gps.getId()); location.setStatus(YesNo.NO.getValue());
					 * location.setRemark("geocoding from amap.");
					 */

					LatLng latLng = new LatLng(gps.getLatitude(), gps.getLongitude());
					// 如果在国外，则默认不进行转换

					if (Gps2Amap.outOfChina(gps.getLatitude(), gps.getLongitude())) {
						locationSemi.setInnerCn(0); // 表示位置在国外
					} else {
						latLng = Gps2Amap.transWGS2GCJ(latLng);
						locationSemi.setInnerCn(1); // 表示位置在国内
					}

					try {
						DeviceLocation location2 = locationResolutionService.resolution(latLng.getLatitude(),
								latLng.getLongitude(), locationSemi.getInnerCn() == 0 ? false : true); // 如果是在国外就直接用谷歌解析
						locationSemi.setLatitude(latLng.getLatitude());
						locationSemi.setLongitude(latLng.getLongitude());
						locationSemi.setAddress(location2.getAddress());
						locationSemi.setStatus(location2.getStatus());
						locationSemi.setRemark(location2.getRemark());
					} catch (Exception e) {
						log.error("parse google address failed.", e);
						locationSemi.setAddress("UNKOWN");
					}
					gps.setRemark("success");
					locationSemiList.add(locationSemi);
				} catch (Exception e) {
					String emsg = BizException.getStackTraceMsg(e);
					if (e instanceof LmException) {
						log.error("parse google location failed.status=" + ((LmException) e).getCode() + " msg="
								+ ((LmException) e).getMessage(), e);
					} else {
						log.error("parse google location failed.", e);
					}
					gps.setRemark("failed:" + emsg.substring(0, 200));
				}
				gps.setHandleFlag(YesNo.YES.getValue());
				locationGpsList.add(gps);
			}
			deviceLocationSemiService.save(new Context(-1L), locationSemiList);
			dvcDeviceLocationGpsService.save(new Context(-1L), locationGpsList);
		} catch (Exception e) {
			log.error("device location parse error.fromDate=" + fromDate + " toDate=" + toDate, e);
		}

	}

	public void excuteGoogleGeolocationByBts() {
		Date fromDate = DateUtils.addHours(DateUtils.currentDate(), -6);
		Date toDate = DateUtils.currentDate();
		try {
			List<DeviceLocationBts> lbsList = dvcDeviceLocationBtsService.queryUnhandleList(fromDate, toDate);
			List<DeviceLocationSemi> locationSemiList = new ArrayList<DeviceLocationSemi>();
			List<DeviceLocationBts> locationBtsList = new ArrayList<DeviceLocationBts>();
			for (DeviceLocationBts bts : lbsList) {
				try {
					Device device = deviceService.getByNumber(bts.getImei());
					if (device == null) {
						throw new LmException(StatusCode.IMEI_INVALID);
					}
					TransOrder order = dvcTransOrderService.queryByDeviceNumberAndTime(device.getNumber(),
							bts.getTime());
					if (order == null) {
						throw new LmException(StatusCode.HAS_NO_ORDER);
					}
					DeviceLocationSemi locationSemi = new DeviceLocationSemi();
					locationSemi.setImei(bts.getImei());
					// locationSemi.setDistance();
					locationSemi.setTimeBegin(bts.getTime());
					locationSemi.setTimeEnd(bts.getTime());
					locationSemi.setSourceType(DeviceLocationSemi.SourceType.BTS.getValue());
					locationSemi.setSourceId(bts.getId());
					locationSemi.setStatus(YesNo.NO.getValue());
					locationSemi.setHandleStatus(DeviceLocationSemi.HandleStatus.INIT.getValue());

					/*
					 * DeviceLocation location = new DeviceLocation();
					 * location.setDeviceId(device.getId()); location.setOrderId(order.getId());
					 * location.setTime(bts.getTime()); location.setTimeStay(0);
					 * location.setSourceType(DeviceLocation.SourceType.BTS.getValue());
					 * location.setSourceId(bts.getId()); location.setStatus(YesNo.NO.getValue());
					 */

					try {
						boolean isOutOfChina = false;
						LatLng latlng = null;
						GaoDeLocationStruc result = btsResolutionService.resolutionByGaoDe(device.getNumber(), bts); // 高德bts解析
						if (result != null && result.getDesc() != null) { // 判断解析结果是否为空,如果解析没有结果就使用谷歌bts解析
							latlng = new LatLng(
									result.getLocation().split(",")[1] + "," + result.getLocation().split(",")[0]);
							isOutOfChina = Gps2Amap.outOfChina(latlng.getLatitude(), latlng.getLongitude()); // 判断点是否在国内，如果不是就使用谷歌解析

						} else {
							isOutOfChina = true;
						}

						if (isOutOfChina) {
							GoogleDocumentationResponse googleDocumentationResponse = btsResolutionService
									.resolutionByGoogle(bts);

							if (googleDocumentationResponse != null) {
								GoogleLocation googleLocation = googleDocumentationResponse.getLocation();
								DeviceLocation location2 = locationResolutionService.resolution(latlng.getLatitude(),
										latlng.getLongitude(), true);

								locationSemi.setLatitude(latlng.getLatitude());
								locationSemi.setLongitude(latlng.getLongitude());
								locationSemi.setAddress(location2.getAddress());
								locationSemi.setStatus(location2.getStatus());
								locationSemi.setInnerCn(
										Gps2Amap.outOfChina(googleLocation.getLat(), googleLocation.getLng()) == true
												? 0
												: 1); // 表示位置在国外
								locationSemi.setRadius(500);
							} else {
								locationSemi.setStatus(0);
							}
							locationSemi.setRemark("Google geocoding from amap.");
						} else { // 高德GPS解析
							latlng = Gps2Amap.transGCJ2WGS(latlng);
							locationSemi.setLongitude(latlng.getLongitude());
							locationSemi.setLatitude(latlng.getLatitude());
							locationSemi
									.setRadius((result.getRadius() != null && Integer.valueOf(result.getRadius()) < 500
											&& Integer.valueOf(result.getRadius()) > 0)
													? Integer.valueOf(result.getRadius())
													: 500);
							locationSemi.setAddress(result.getDesc().replace(" ", ""));
							locationSemi.setStatus(YesNo.YES.getValue());
							locationSemi.setInnerCn(1); // 1表示在国内
							locationSemi.setRemark("Gaode geocoding from amap.");
						}

					} catch (Exception e) {
						locationSemi.setLongitude(0.0);
						locationSemi.setLatitude(0.0);
						log.error("parse bts address failed.", e);
					}
					bts.setRemark("success");
					locationSemiList.add(locationSemi);
				} catch (Exception e) {
					String emsg = BizException.getStackTraceMsg(e);
					if (e instanceof LmException) {
						log.error("parse bts location failed.status=" + ((LmException) e).getCode() + " msg="
								+ ((LmException) e).getMessage(), e);
					} else {
						log.error("parse bts location failed.", e);
					}
					bts.setRemark("failed:" + emsg.substring(0, 200));
				}
				bts.setHandleFlag(YesNo.YES.getValue());
				locationBtsList.add(bts);
			}
			deviceLocationSemiService.save(new Context(-1L), locationSemiList);
			dvcDeviceLocationBtsService.save(new Context(-1L), locationBtsList);
		} catch (Exception e) {
			log.error("device location parse error.fromDate=" + fromDate + " toDate=" + toDate);
		}
	}

	public static void main(String[] args) throws Exception {

		/**
		 * MG201867282030769158, BA & X460, 0, 20904, 62876, 86; 20904, 62875, 85;
		 * 20904, 62381, 89; 20904, 29532, 94; 20904, 59282, 94; 20904, 31057, 94;
		 * 20904, 62383, 95 & E180307080139 & B0000000000 & W00 & G001090 & M591 & N14 &
		 * O0000 & T0003#
		 */

		DeviceLocationBts bts = new DeviceLocationBts();
		bts.setMcc("460");
		bts.setMnc("0");
		bts.setLac1("20904");
		bts.setCi1("62876");
		bts.setRssi1("86");
		bts.setLac2("20904");
		bts.setCi2("62875");
		bts.setRssi2("85");
		bts.setLac3("20904");
		bts.setCi3("62381");
		bts.setRssi3("89");
		bts.setLac4("20904");
		bts.setCi4("29532");
		bts.setRssi4("94");
		bts.setLac5("20904");
		bts.setCi5("59282");
		bts.setRssi5("94");
		final String key = "a445886dba2e781c569899bf0478670c";
		final String baseUrl = "http://apilocate.amap.com/position?key=" + key + "&";

		StringBuffer url = new StringBuffer();
		url.append(baseUrl);
		url.append("&accesstype=0");
		url.append("&imei=" + "867282030747105");
		url.append("&cdma=0");
		url.append("&bts=" + bts.getMcc() + "," + bts.getMnc() + "," + bts.getLac1() + "," + bts.getCi1() + ","
				+ bts.getRssi1());
		if (bts.getLac2() != null) {
			url.append("&nearbts=" + bts.getMcc() + "," + bts.getMnc() + "," + bts.getLac2() + "," + bts.getCi2() + ","
					+ bts.getRssi2());
			if (bts.getLac3() != null) {
				url.append("|" + bts.getMcc() + "," + bts.getMnc() + "," + bts.getLac3() + "," + bts.getCi3() + ","
						+ bts.getRssi3());
				if (bts.getLac4() != null) {
					url.append("|" + bts.getMcc() + "," + bts.getMnc() + "," + bts.getLac4() + "," + bts.getCi4() + ","
							+ bts.getRssi4());
					if (bts.getLac5() != null) {
						url.append("|" + bts.getMcc() + "," + bts.getMnc() + "," + bts.getLac5() + "," + bts.getCi5()
								+ "," + bts.getRssi5());
					}
				}
			}
		}
		url.append("&output=json");

		GaoDeLocationStruc result = AmapUtil.getGaoDeLocation(url.toString());
		System.out.println("Geocoding bts request:" + url.toString() + "\n result:" + JsonUtils.toJson(result));
		// excuteGoogleGeolocationByBts() test
		// String lng = "72.878139";
		// String lat = "19.080165";

	}

}
