package com.winterframework.logistics.device.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winterframework.logistics.base.enums.StatusCode;
import com.winterframework.logistics.base.utils.JsonUtils;
import com.winterframework.logistics.common.entity.DeviceLocationBts;
import com.winterframework.logistics.common.exception.LmException;
import com.winterframework.logistics.device.dto.GoogleCellTower;
import com.winterframework.logistics.device.dto.GoogleDocumentationRequest;
import com.winterframework.logistics.device.dto.GoogleDocumentationResponse;
import com.winterframework.logistics.device.service.IBtsResolutionService;
import com.winterframework.logistics.device.service.scheduler.location.AmapUtil;
import com.winterframework.logistics.device.service.scheduler.location.GaoDeLocationStruc;
import com.winterframework.logistics.device.service.scheduler.location.GoogleUtils;

@Service("btsResolutionServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class BtsResolutionServiceImpl implements IBtsResolutionService {
	protected Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public GoogleDocumentationResponse resolutionByGoogle(DeviceLocationBts bts) throws LmException {
		// TODO Auto-generated method stub
		try {
			GoogleCellTower[] cellTowers = new GoogleCellTower[5];
			if (bts.getCi1() != null && bts.getLac1() != null && bts.getRssi1() != null) {
				cellTowers[0] = new GoogleCellTower(bts.getCi1(), bts.getLac1(), bts.getMcc(), bts.getMnc(), 0,
						bts.getRssi1(), 0);
			}
			if (bts.getCi2() != null && bts.getLac2() != null && bts.getRssi2() != null) {
				cellTowers[0] = new GoogleCellTower(bts.getCi2(), bts.getLac2(), bts.getMcc(), bts.getMnc(), 0,
						bts.getRssi2(), 0);
			}
			if (bts.getCi3() != null && bts.getLac3() != null && bts.getRssi3() != null) {
				cellTowers[0] = new GoogleCellTower(bts.getCi3(), bts.getLac3(), bts.getMcc(), bts.getMnc(), 0,
						bts.getRssi3(), 0);
			}
			if (bts.getCi4() != null && bts.getLac4() != null && bts.getRssi4() != null) {
				cellTowers[0] = new GoogleCellTower(bts.getCi4(), bts.getLac4(), bts.getMcc(), bts.getMnc(), 0,
						bts.getRssi4(), 0);
			}
			if (bts.getCi5() != null && bts.getLac5() != null && bts.getRssi5() != null) {
				cellTowers[0] = new GoogleCellTower(bts.getCi5(), bts.getLac5(), bts.getMcc(), bts.getMnc(), 0,
						bts.getRssi5(), 0);
			}

			GoogleDocumentationRequest googleDocumentationRequest = new GoogleDocumentationRequest();
			googleDocumentationRequest.setCarrier("Vodafone");
			googleDocumentationRequest
					.setHomeMobileCountryCode(Integer.parseInt(Optional.ofNullable(bts.getMcc()).orElse("0")));
			googleDocumentationRequest
					.setHomeMobileNetworkCode(Integer.parseInt(Optional.ofNullable(bts.getMnc()).orElse("0")));
			googleDocumentationRequest.setRadioType("gsm");
			googleDocumentationRequest.setConsiderIp("false");
			googleDocumentationRequest.setCellTowers(cellTowers);
			googleDocumentationRequest.setWifiAccessPoints(null);
			log.info("googleDocumentationRequest 请求实体：" + JsonUtils.toJson(googleDocumentationRequest));
			return GoogleUtils.getLocationByBts(googleDocumentationRequest);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("google 移动网络解析出现错误！   ， btsId:" + bts.getId() + "  imei:" + bts.getImei());
			throw new LmException(StatusCode.DAO_ERROR, e);
		}
	}

	@Override
	public GaoDeLocationStruc resolutionByGaoDe(String number, DeviceLocationBts bts) throws LmException {
		// TODO Auto-generated method stub
		final String key = "a445886dba2e781c569899bf0478670c";
		final String baseUrl = "http://apilocate.amap.com/position?key=" + key + "&";
		StringBuffer url = new StringBuffer();
		url.append(baseUrl);
		url.append("&accesstype=0");
		url.append("&imei=" + number);
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
		GaoDeLocationStruc result = null;
		try {
			result = AmapUtil.getGaoDeLocation(url.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("Geocoding bts request:" + url.toString() + " result:" + JsonUtils.toJson(result));
		return result;
	}

	public static void main(String[] args) {

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

		GoogleCellTower[] cellTowers = new GoogleCellTower[5];
		if (bts.getCi1() != null && bts.getLac1() != null && bts.getRssi1() != null) {
			cellTowers[0] = new GoogleCellTower(bts.getCi1(), bts.getLac1(), bts.getMcc(), bts.getMnc(), 0,
					bts.getRssi1(), 0);
		}
		if (bts.getCi2() != null && bts.getLac2() != null && bts.getRssi2() != null) {
			cellTowers[1] = new GoogleCellTower(bts.getCi2(), bts.getLac2(), bts.getMcc(), bts.getMnc(), 0,
					bts.getRssi2(), 0);
		}
		if (bts.getCi3() != null && bts.getLac3() != null && bts.getRssi3() != null) {
			cellTowers[2] = new GoogleCellTower(bts.getCi3(), bts.getLac3(), bts.getMcc(), bts.getMnc(), 0,
					bts.getRssi3(), 0);
		}
		if (bts.getCi4() != null && bts.getLac4() != null && bts.getRssi4() != null) {
			cellTowers[3] = new GoogleCellTower(bts.getCi4(), bts.getLac4(), bts.getMcc(), bts.getMnc(), 0,
					bts.getRssi4(), 0);
		}
		if (bts.getCi5() != null && bts.getLac5() != null && bts.getRssi5() != null) {
			cellTowers[4] = new GoogleCellTower(bts.getCi5(), bts.getLac5(), bts.getMcc(), bts.getMnc(), 0,
					bts.getRssi5(), 0);
		}

		System.out.println("数组：" + JsonUtils.toJson(cellTowers));
		GoogleDocumentationRequest googleDocumentationRequest = new GoogleDocumentationRequest();
		googleDocumentationRequest.setCarrier("Vodafone");
		googleDocumentationRequest.setHomeMobileCountryCode(Integer.parseInt(Optional.ofNullable("460").orElse("0")));
		googleDocumentationRequest.setHomeMobileNetworkCode(Integer.parseInt(Optional.ofNullable("460").orElse("0")));
		googleDocumentationRequest.setRadioType("gsm");
		googleDocumentationRequest.setConsiderIp("false");
		googleDocumentationRequest.setCellTowers(cellTowers);
		googleDocumentationRequest.setWifiAccessPoints(null);
		GoogleDocumentationResponse d = null;
		try {
			d = GoogleUtils.getLocationByBts(googleDocumentationRequest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("location:" + JsonUtils.toJson(d));

	}

}
