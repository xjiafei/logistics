package com.winterframework.logistics.device.service.scheduler.location;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.winterframework.logistics.base.http.HttpClientUtil;
import com.winterframework.logistics.base.utils.JsonUtils;

public class AmapUtil {

	private static Logger log = LoggerFactory.getLogger(AmapUtil.class);

	public static GaoDeLocationStruc getGaoDeLocation(String url) throws Exception {
		String resultStr = BaiDuGetCityNameUtil.getResultByUrl(url);
		if (resultStr != null) {
			resultStr = resultStr.replace("[]", "\"\"");
			GaoDeLocationOperator gaoDeLocationOperator = JsonUtils.fromJson(resultStr, GaoDeLocationOperator.class);
			if (null != gaoDeLocationOperator && gaoDeLocationOperator.getStatus() == 1) {
				return gaoDeLocationOperator.getResult();
			}
		}
		return null;
	}

	public static Regeocode getCityNameByLocation(String location) throws Exception {
		return getCityNameByLocation(location, false);
	}

	public static Regeocode getCityNameByLocation(String location, boolean isTransGaoDeLocation) throws Exception {
		LatLng latLng = new LatLng(Double.valueOf(location.split(",")[0]), Double.valueOf(location.split(",")[1]));
		if (isTransGaoDeLocation) {
			latLng = Gps2Amap.transGCJ2WGS(latLng);
		}
		location = latLng.latitude + "," + latLng.longitude;
		 String url = "http://restapi.amap.com/v3/geocode/regeo?";
		 url
		 +="key=9c00a697f30cbe3606f4e634b804c90a&extensions=base&location="+location+"&output=json";
		 
//		String url = "https://restapi.amap.com/v3/geocode/regeo?output=json&location=" + location
//				+ "&key=9c00a697f30cbe3606f4e634b804c90a&radius=1000&extensions=base";
		try {
			String resultStr = HttpClientUtil.getResultByUrl(url);
			if (resultStr != null) {
				resultStr = resultStr.replace("[]", "\"\"");
				GaoDeLocationResult baiduLocationResult = JsonUtils.fromJson(resultStr, GaoDeLocationResult.class);
				if (baiduLocationResult.getStatus() == 1) {
					return baiduLocationResult.getRegeocode();
				} else {
					log.error("坐标转换为实际地址：info = " + baiduLocationResult.getInfo() + " ; infocode = "
							+ baiduLocationResult.getInfocode() + " ;");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("坐标转换为实际地址时，出现异常。location=" + location + " ; ");
		}
		return null;
	}

	public static void main(String[] a) throws Exception {
		// String location="116.310003,39.991957";
		// http://lbs.amap.com/api/webservice/reference/georegeo/
//		String location = "113.9397684,22.55330874";

		// Regeocode regeocode = getCityNameByLocation(location);
		// System.out.println("regeocode : "+regeocode.toString());
		// {"status":"1","info":"OK","infocode":"10000","regeocode":{"formatted_address":"北京市海淀区燕园街道北京大学","addressComponent":{"country":"中国","province":"北京市","city":[],"citycode":"010","district":"海淀区","adcode":"110108","township":"燕园街道","towncode":"110108015000","neighborhood":{"name":"北京大学","type":"科教文化服务;学校;高等院校"},"building":{"name":"北京大学","type":"科教文化服务;学校;高等院校"},"streetNumber":{"street":"颐和园路","number":"5号","location":"116.310518,39.9918931","direction":"东","distance":"44.4465"},"businessAreas":[{"location":"116.29522008325625,39.99426090286774","name":"颐和园","id":"110108"}]}}}
		// String resultStr =
		// "{\"status\":\"1\",\"info\":\"OK\",\"infocode\":\"10000\",\"regeocode\":{\"formatted_address\":\"北京市海淀区燕园街道北京大学\",\"addressComponent\":\"中国\"}}";
		// String resultStr =
		// "{\"status\":\"1\",\"info\":\"OK\",\"infocode\":\"10000\",\"regeocode\":{\"formatted_address\":\"北京市海淀区燕园街道北京大学\",\"addressComponent\":\"中国\"}}";
		// GaoDeLocationResult baiduLocationResult =
		// JsonUtils.fromJson(resultStr,GaoDeLocationResult.class);
		// System.out.println("baiduLocationResult:"+baiduLocationResult+"; ");
	}
}