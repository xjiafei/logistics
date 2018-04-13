
package com.winterframework.logistics.base.http;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.winterframework.logistics.base.enums.StatusCode;
import com.winterframework.logistics.base.exception.BizException;
import com.winterframework.logistics.base.model.Context;
import com.winterframework.logistics.base.model.Request;
import com.winterframework.logistics.base.model.Response;

public class HttpUtil {
	private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);
	
	public static <T,R> Response<T> http(String serverUrl,String action,R requestData,Class<?> clazz) throws BizException{
		try{ 
			Request<R> request = new Request<R>();
			request.setData(requestData);
			Response<T> t = invokeHttp(serverUrl+action, request, clazz);
			return t;
		}catch(Exception e){
			log.error(e.getMessage(),e);
			throw new BizException(StatusCode.HTTP.getCode(),StatusCode.HTTP.getMessage(),e);
		}
	}
	
	/**
	* Title: invokeHttp
	* Description:
	* @param url
	* @param params
	* @return 
	* @see com.winterframework.firefrog.IHttpClient.http.IHttpJsonClient#invokeHttp(java.lang.String, com.winterframework.modules.web.jsonresult.Request) 
	*/
	private static <T, R> Response<T> invokeHttp(String url, Request<R> request, Class... classType) throws Exception {
		return HttpClientUtil.postJsonObject(url, request, classType);
	}
	
	public static <T,R> Response<T> http(String serverUrl,String action,Context ctx,R requestData,Class<?> clazz) throws BizException{
		try{
			Request<R> request = new Request<R>();
			request.setData(requestData);
			Response<T> t = invokeHttp(serverUrl+action, request, clazz);
			return t;
		}catch(Exception e){
			log.error(e.getMessage(),e);
			throw new BizException(StatusCode.HTTP.getCode(),StatusCode.HTTP.getMessage(),e);
		}
	}
	@SuppressWarnings("rawtypes")
	public static <R> Response http(String serverUrl,String action,R requestData) throws BizException{
		try{	
			Request<R> request = new Request<R>();
			request.setData(requestData);
			Response t = invokeHttp(serverUrl+action, request);
			return t;
		}catch(Exception e){
			log.error(e.getMessage(),e);
			throw new BizException(StatusCode.HTTP.getCode(),StatusCode.HTTP.getMessage(),e);
		}
	}

	public static String http(String serverUrl,String action,Map<String,String> params) throws BizException{
		try{	
			return HttpClientUtil.getStrByUrl(serverUrl+action, params);
		}catch(Exception e){
			log.error(e.getMessage(),e);
			throw new BizException(StatusCode.HTTP.getCode(),StatusCode.HTTP.getMessage(),e);
		}
	}
	private static <R> Response invokeHttp(String url, Request<R> request) throws Exception {
		return HttpClientUtil.postJsonObject(url, request);
	}
}
