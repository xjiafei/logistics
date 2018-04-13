package com.winterframework.logistics.device.service.scheduler.location;

import java.net.URI;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.winterframework.logistics.base.http.HttpSSLClient;
import com.winterframework.logistics.base.utils.JsonUtils;

public class BaiDuGetCityNameUtil {
	public static String getResultByUrl(String url) throws Exception {

		DefaultHttpClient httpclient = new DefaultHttpClient();
		URL urls = new URL(url); URI uri = new URI(urls.getProtocol(), urls.getHost(), urls.getPath(), urls.getQuery(), null);
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

	public static CityJsonObject getCityNameByLocation(String location)throws Exception{
		LatLng latLng = Gps2Amap.transGCJ2WGS(new LatLng(Double.valueOf(location.split(",")[0]),Double.valueOf(location.split(",")[1])));
		location = latLng.latitude+","+latLng.longitude;
		String url = "http://api.map.baidu.com/geocoder/v2/?";
		url +="ak=PdDYk9b506nxVGV2ATzTZvFe&coordtype=wgs84ll&location="+location+"&output=json";
		String resultStr = getResultByUrl(url);
		if(resultStr != null ){
		BaiduLocationResult baiduLocationResult = JsonUtils.fromJson(resultStr,BaiduLocationResult.class);
		 if(baiduLocationResult.getStatus()==0){
			 return baiduLocationResult.getResult();
		 }
		}
		return null;
	}
	
	public static void main(String[] s) throws Exception{
		/*try {
			getCityNameByLocation("22.2222,333.444");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		

			DefaultHttpClient httpclient = new DefaultHttpClient();
			URL urls = new URL("https://maps.googleapis.com/maps/api/geocode/json?latlng=22.557217500000004,113.9692066&key=AIzaSyCtsNGlSKHWVxjSh0XPpdK7WD0Ye3RTRsA");
			URI uri = new URI(urls.getProtocol(), urls.getHost(), urls.getPath(), urls.getQuery(), null);
			HttpGet httpget = new HttpGet(uri);
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity(); 
			String result = null;
			if (entity != null) {
				result = EntityUtils.toString(entity, "UTF-8");
			}
			httpclient.getConnectionManager().shutdown();
	}
}
