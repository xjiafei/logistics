package com.winterframework.logistics.base.http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.winterframework.logistics.base.model.Context;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.modules.web.jsonresult.Pager;


 
/**
 * 调用json over http协议接口客户端
 * @ClassName
 * @Description
 * @author ibm
 * 2015年6月2日
 */
public interface IHttpClient {

	public <T,R> Response<T> invokeHttp(String url, Class... dataClass) throws Exception;
	public <T,R> Response<T> invokeHttp(String url, R requestData, Class... dataClass) throws Exception;
	public <T,R> Response<T> invokeHttp(String url,Context ctx,R requestData, Class... dataClass) throws Exception;
	public <T,R> Response<T> invokeHttp(String url, TypeReference typeReference) throws Exception;
	public <T,R> Response<T> invokeHttp(String url, R requestData, TypeReference typeReference) throws Exception;
	public <T,R> Response<T> invokeHttp(String url, R requestData,Pager pager, TypeReference typeReference) throws Exception;
	public Response invokeHttpWithoutResultType(String url) throws Exception;
	public <R> Response invokeHttpWithoutResultType(String url, R requestData) throws Exception;
}
