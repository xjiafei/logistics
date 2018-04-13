package com.winterframework.logistics.base.http;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.winterframework.logistics.base.model.Request;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.modules.web.util.JsonMapper;
/** 
* @ClassName: HttpJsonClientExt 
* @Description: 
* @author 你的名字 
* @date 2013-9-25 上午11:22:06 
*  
*/
public class HttpClientUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
	private static int HTTP_TIMEOUT_SECOND = 60;
	private static int HTTP_TIMEOUT_SECOND_DEFAULT = 20;
	private static ObjectMapper mapper = new ObjectMapper();
	private static JsonMapper jmapper = JsonMapper.nonEmptyMapper();
	static {
		mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_EMPTY);
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}

	public static <T, R> Response<T> postJsonObject(String url, Request<R> params, Class... classNames)
			throws ClientProtocolException, IOException, SecurityException, NoSuchMethodException {
		String result = postJsonObjectReturnStr(url, params);
		
		ParameterizedType pt = (ParameterizedType) HttpClientUtil.class.getDeclaredMethod("postJsonObject",
				String.class, Request.class, Class[].class).getGenericReturnType();
		if (classNames != null) {
			return  (Response)jmapper.fromJson(result, jmapper.createCollectionType(Response.class, classNames));
		} else {
			try{
				return (Response)jmapper.fromJson(result, (Class) pt.getRawType());
			}catch(Exception e){
				return  (Response)jmapper.fromJson(result, Response.class);
			}
		}
	}
	

	public static <T, R> Response<T> postJsonObject(String url, Request<R> params, TypeReference typeReference)
			throws ClientProtocolException, IOException, SecurityException, NoSuchMethodException {
		
		String result = postJsonObjectReturnStr(url, params);
		
		ParameterizedType pt = (ParameterizedType) HttpClientUtil.class.getDeclaredMethod("postJsonObject",
				String.class, Request.class, TypeReference.class).getGenericReturnType();
		if (typeReference != null) {
			return (Response)mapper.readValue(result, typeReference);
		} else {
			return (Response)jmapper.fromJson(result, (Class) pt.getRawType());
		}
	}

	public static Response postJsonObject(String url, Request params) throws ClientProtocolException,
			IOException, SecurityException, NoSuchMethodException {
		
		String result = postJsonObjectReturnStr(url, params);
		
		Type pt = HttpClientUtil.class.getDeclaredMethod("postJsonObject",
				String.class, Request.class).getGenericReturnType();
		return (Response)jmapper.fromJson(result, Response.class);
	}

	public static String postJsonObjectReturnStr(String url, Request params) throws ClientProtocolException,
			IOException, SecurityException, NoSuchMethodException { 
		HttpClient httpclient = new DefaultHttpClient();
		if (HTTP_TIMEOUT_SECOND > 0) {
			HttpParams httpParams = httpclient.getParams();
			HttpConnectionParams.setConnectionTimeout(httpParams, HTTP_TIMEOUT_SECOND * 1000);// 
			HttpConnectionParams.setSoTimeout(httpParams, HTTP_TIMEOUT_SECOND * 1000);// 
		} else {
			HttpParams httpParams = httpclient.getParams();
			HttpConnectionParams.setConnectionTimeout(httpParams, HTTP_TIMEOUT_SECOND_DEFAULT*1000);// 
			HttpConnectionParams.setSoTimeout(httpParams, HTTP_TIMEOUT_SECOND_DEFAULT*1000);// 
		}
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.getParams().setBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
			if (params != null) {
				String str = jmapper.toJson(params);
				logger.info("request:" + str + ",url:" + url);
				ByteArrayEntity mult = new ByteArrayEntity(str.getBytes("UTF-8"));
				mult.setContentType("application/json");
				httpPost.setEntity(mult);
			}

			logger.debug("begin to post url:" + url);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String result = httpclient.execute(httpPost, responseHandler);
			logger.debug("response:" + result);
			return result;
		}finally {
			httpclient.getConnectionManager().shutdown();
		}

	}
	
	public static String postStrReturnStr(String url, String params,String referer)
			throws ClientProtocolException, IOException, SecurityException,
			NoSuchMethodException {
		HttpClient httpclient = new DefaultHttpClient();
		if (HTTP_TIMEOUT_SECOND > 0) {
			HttpParams httpParams = httpclient.getParams();
			HttpConnectionParams.setConnectionTimeout(httpParams,
					HTTP_TIMEOUT_SECOND * 1000);// 
			HttpConnectionParams.setSoTimeout(httpParams,
					HTTP_TIMEOUT_SECOND * 1000);// 
		} else {
			HttpParams httpParams = httpclient.getParams();
			HttpConnectionParams.setConnectionTimeout(httpParams, 3000);// 
			HttpConnectionParams.setSoTimeout(httpParams, 3000);//
		}
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.getParams().setBooleanParameter(
					CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
			if (params != null) {
				logger.debug("request:" + params + ",referer:" + referer);
				org.apache.http.entity.StringEntity se = new org.apache.http.entity.StringEntity(params);
//				se.setContentType("text/html;charset=utf-8");
//				httpPost.setEntity(se);
//				httpPost.addHeader("referer", referer);
				
				httpPost.setHeader("Content-Type","application/x-www-form-urlencoded");
				httpPost.setHeader("Referer",referer);
				httpPost.setEntity(new UrlEncodedFormEntity(getListFromString(params),"UTF-8"));  
			}

			logger.debug("begin to post url:" + url);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String result = httpclient.execute(httpPost, responseHandler);
			logger.debug("response:" + result);
			return result;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

	}
	
	private static List<NameValuePair> getListFromString(String adata) {  
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		
		String[] parmas = adata.split("&");
		
		for(String parm:parmas){
			String[] kv = parm.split("=");
			NameValuePair nvp = new BasicNameValuePair(kv[0],kv[1]);
			nameValuePairs.add(nvp);
		}
		
        return nameValuePairs;  
    }  
	private static String getMap2String(Map<String,String> adata) {  
		StringBuffer sb=new StringBuffer();
		for(Map.Entry<String,String> e:adata.entrySet()){
			sb.append(e.getKey()).append("=").append(e.getValue()).append("&");
		}
		
        return sb.substring(0,sb.length()-1);  
    }  
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
	
	public static String getStrByUrl(String url,Map<String,String> params) throws Exception {
		String paramStr=getMap2String(params);
		String urlPath=url+"?"+paramStr;
		DefaultHttpClient httpclient = new DefaultHttpClient();
		URL urls = new URL(urlPath); URI uri = new URI(urls.getProtocol(), urls.getHost(), urls.getPath(), urls.getQuery(), null);
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

	public static void main(String[] args) throws Exception {
		System.out.println(1);
		String t="{ \"head\" : {   \"sowner\" : 0,    \"rowner\" : 0,    \"msn\" : 0,    \"userId\" : 0,    \"msnsn\" : 0,    \"status\" : 0,    \"sendtime\" : 1401505069900  },  \"body\" : {    \"result\" : null,    \"pager\" : {      \"startNo\" : 0,      \"endNo\" : 0,      \"total\" : 0   } }}";
		System.out.println(1);
		Object obj= (Response)jmapper.fromJson(t, Response.class);
		System.out.println(1);
	}
	
}
