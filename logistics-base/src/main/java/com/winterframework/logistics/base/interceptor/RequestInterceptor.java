package com.winterframework.logistics.base.interceptor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.winterframework.logistics.base.enums.StatusCode;
import com.winterframework.logistics.base.model.Request;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.base.model.Status;
import com.winterframework.logistics.base.utils.DateUtils;
import com.winterframework.logistics.base.utils.JsonUtils;
import com.winterframework.logistics.base.utils.SignUtil;
import com.winterframework.modules.web.util.JsonMapper;

public class RequestInterceptor implements HandlerInterceptor{
	private Logger logger = Logger.getLogger(RequestInterceptor.class);
	private Map<String,String> pathMap;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		response.setCharacterEncoding("utf-8");
		Status status=null;
		String postStr = StringUtils.collectionToDelimitedString(IOUtils.readLines(request.getInputStream()),"");
		Request req = JsonMapper.nonEmptyMapper().fromJson(postStr, Request.class);
		logger.error("request: uri="+request.getRequestURI()+" params="+ postStr);
		boolean flag=false;
		Response res=new Response(StatusCode.UNKNOW.getCode(),StatusCode.UNKNOW.getMessage());;
		try{
			if(!checkTimestamp(req.getTimestamp())){
				status=new Status(StatusCode.REQ_EXPIRED.getCode(),StatusCode.REQ_EXPIRED.getMessage());
			}else if(!checkSign(req)){
				status=new Status(StatusCode.SIGN_INVALID.getCode(),StatusCode.SIGN_INVALID.getMessage());
			}
			if(status!=null){
				res=new Response(status.getCode(),status.getMessage());
			}else{
				flag= true;
			}
		}catch(Exception e){
			logger.error("request check error. uri="+request.getRequestURI()+" params="+ postStr);
			res=new Response(StatusCode.PARAM_INVALID.getCode(),StatusCode.PARAM_INVALID.getMessage());
		}
		if(!flag){
			response.getWriter().print(JsonUtils.toJson(res));
			response.getWriter().flush();
			response.getWriter().close();
		}
		return true;
	}
	private boolean checkSign(Request req){
		Map<String,String> params=new HashMap<String,String>();
		params.put("token", req.getToken());
		params.put("timestamp", req.getTimestamp()+"");
		params.put("data", JsonUtils.toJson(req.getData()));
		return SignUtil.verify(req.getSign(), req.getToken()+req.getTimestamp()+JsonUtils.toJson(req.getData()));
	}
	//5分钟内的请求有效
	private boolean checkTimestamp(Long timestamp){
		return DateUtils.calcMinutesBetween(DateUtils.getDate(timestamp), DateUtils.currentDate())<=5;
	}
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception arg3)
            throws Exception {
    }

  //在业务处理器处理请求执行完成后,生成视图之前执行的动作   
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView model) throws Exception {
    	logger.info("==============loading path===============");
		Iterator<Entry<String, String>> it = pathMap.entrySet().iterator();
		while(it.hasNext()){
			Entry<String,String> en = it.next();
			request.setAttribute(en.getKey(), en.getValue());
		}
		request.setAttribute("currentContextPath", request.getContextPath());
    }
    public Map<String,String> getPathMap() {
		return pathMap;
	}

	public void setPathMap(Map<String,String> pathMap) {
		this.pathMap = pathMap;
	}
}