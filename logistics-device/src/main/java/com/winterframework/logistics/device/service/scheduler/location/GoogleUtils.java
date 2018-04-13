package com.winterframework.logistics.device.service.scheduler.location;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONObject;
import com.winterframework.logistics.base.utils.JsonUtils;
import com.winterframework.logistics.device.dto.GoogleDocumentationRequest;
import com.winterframework.logistics.device.dto.GoogleDocumentationResponse;
import com.winterframework.logistics.device.dto.GoogleLocationOperatorResponse;
import com.winterframework.logistics.device.dto.GoogleLocationOperatorResults;

public class GoogleUtils {
	private static Logger log = LoggerFactory.getLogger(GoogleUtils.class);

	public static GoogleLocationOperatorResults[] getCityNameByGoogleGeocode(String location) throws Exception {

		/*
		 * String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" +
		 * location +
		 * "&location_type=ROOFTOP&result_type=street_address&key=AIzaSyCtsNGlSKHWVxjSh0XPpdK7WD0Ye3RTRsA";
		 */

		String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + location
				+ "&language=zh-CN&key=AIzaSyCtsNGlSKHWVxjSh0XPpdK7WD0Ye3RTRsA";
		try {
			String resultStr = getResultByUrl(url);
			if (resultStr != null) {
				resultStr = resultStr.replace("[]", "\"\"");
				GoogleLocationOperatorResponse googleLocationOperator = JsonUtils.fromJson(resultStr,
						GoogleLocationOperatorResponse.class);
				if (null != googleLocationOperator && googleLocationOperator.getStatus().equals("OK")) {
					return googleLocationOperator.getResults();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("坐标转换为实际地址时，出现异常。location=" + location + " ; ");
		}
		return null;
	}

	private static String getResultByUrl(String url) throws ParseException, IOException, URISyntaxException {
		// TODO Auto-generated method stub
		DefaultHttpClient httpclient = HttpClientUtils.getHttpClient(new DefaultHttpClient());
		URL urls = new URL(url);
		URI uri = new URI(urls.getProtocol(), urls.getHost(), urls.getPath(), urls.getQuery(), null);
		HttpGet httpget = new HttpGet(uri);
		HttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();
		String result = null;
		if (entity != null) {
			result = EntityUtils.toString(entity, "UTF-8");
		}
		httpclient.getConnectionManager().shutdown();
		return result;
	}

	public static GoogleDocumentationResponse getLocationByBts(GoogleDocumentationRequest googleDocumentationRequest)
			throws Exception {

		String url = "https://www.googleapis.com/geolocation/v1/geolocate?key=AIzaSyCtsNGlSKHWVxjSh0XPpdK7WD0Ye3RTRsA";
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("homeMobileCountryCode", googleDocumentationRequest.getHomeMobileCountryCode());
		jsonObject.put("homeMobileNetworkCode", googleDocumentationRequest.getHomeMobileNetworkCode());
		jsonObject.put("radioType", googleDocumentationRequest.getRadioType());
		jsonObject.put("carrier", googleDocumentationRequest.getCarrier());
		jsonObject.put("considerIp", googleDocumentationRequest.getConsiderIp());
		jsonObject.put("cellTowers", googleDocumentationRequest.getCellTowers());
		jsonObject.put("wifiAccessPoints", googleDocumentationRequest.getWifiAccessPoints());
		try {
			String resultStr = getPostResultByUrl(url, jsonObject);
			if (resultStr != null) {
				resultStr = resultStr.replace("[]", "\"\"");
				return JsonUtils.fromJson(resultStr, GoogleDocumentationResponse.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("google geolocation转换时，出现异常。 ");
		}
		return null;
	}

	private static String getPostResultByUrl(String url, JSONObject jsonObject)
			throws ParseException, IOException, URISyntaxException {
		// TODO Auto-generated method stub
		DefaultHttpClient httpclient = HttpClientUtils.getHttpClient(new DefaultHttpClient());
		URL urls = new URL(url);
		URI uri = new URI(urls.getProtocol(), urls.getHost(), urls.getPath(), urls.getQuery(), null);

		HttpPost httpPost = new HttpPost(uri);
		httpPost.setHeader("Content-type", "application/json; charset=utf-8");
		StringEntity entity = new StringEntity(jsonObject.toString(), Charset.forName("UTF-8"));// 构建消息实体
		httpPost.setEntity(entity);

		HttpResponse response = httpclient.execute(httpPost);
		HttpEntity resultEntity = response.getEntity();
		String result = null;
		if (resultEntity != null) {
			result = EntityUtils.toString(resultEntity, "UTF-8");
		}
		httpclient.getConnectionManager().shutdown();
		return result;
	}

	public static void main(String[] a) throws Exception {
		// GoogleGeocodeUtil.getCityNameByLocation("22.55718284,113.97098342");
		// String location = "22.55718284,113.97098342";
		// String url2 = "https://segmentfault.com/a/1190000006173834";
		//
		// String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" +
		// location
		// +
		// "&location_type=ROOFTOP&result_type=street_address&key=AIzaSyCtsNGlSKHWVxjSh0XPpdK7WD0Ye3RTRsA";
		// String urs =
		// "https://maps.googleapis.com/maps/api/geocode/json?latlng=22.55718284,113.97098342&location_type=ROOFTOP&result_type=street_address&key=AIzaSyCtsNGlSKHWVxjSh0XPpdK7WD0Ye3RTRsA";
		// System.out.println(GoogleGeocodeUtil.getResultByUrl(urs));

	}
}
