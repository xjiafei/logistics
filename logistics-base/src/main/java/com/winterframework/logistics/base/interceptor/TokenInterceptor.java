package com.winterframework.logistics.base.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.winterframework.logistics.base.enums.StatusCode;
import com.winterframework.logistics.base.http.RequestContext;
import com.winterframework.logistics.base.model.Request;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.base.token.JwtUtil;
import com.winterframework.logistics.base.utils.JsonUtils;
import com.winterframework.modules.spring.exetend.PropertyConfig;
import com.winterframework.modules.web.util.JsonMapper;

public class TokenInterceptor implements HandlerInterceptor{
	private Logger logger = Logger.getLogger(TokenInterceptor.class);
	@PropertyConfig(value = "security.token.secret")
	private String secret;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String postStr = StringUtils.collectionToDelimitedString(IOUtils.readLines(request.getInputStream()),"");
		Request req = JsonMapper.nonEmptyMapper().fromJson(postStr, Request.class);
		boolean flag=false;
		Response res=new Response(StatusCode.UNKNOW.getCode(),StatusCode.UNKNOW.getMessage());;
		logger.error("request: uri="+request.getRequestURI()+" params="+ postStr);
		try{
			Long userId=null;
			if((userId=checkToken(req))==null){
				res=new Response(StatusCode.TOKEN_INVALID.getCode(),StatusCode.TOKEN_INVALID.getMessage());
			}else{
				RequestContext.init(request.getSession().getServletContext(), request, response,userId);
				flag= true;
			}
		}catch(Exception e){
			logger.error("token check error. uri="+request.getRequestURI()+" params="+ postStr);
			res=new Response(StatusCode.PARAM_INVALID.getCode(),StatusCode.PARAM_INVALID.getMessage());
		}
		if(!flag){
			response.getWriter().print(JsonUtils.toJson(res));
			response.getWriter().flush();
			response.getWriter().close();
		}
		return flag;
	}
	private Long checkToken(Request req){
		String token = req.getToken();
        //token不存在
        if(null != token) {
        	String subject = JwtUtil.verify(token, secret);
        	if(subject!=null){ 
        		return Long.valueOf(subject);
            }else{
            	
            }
        }
        return null;
	}
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception arg3)
            throws Exception {
    	RequestContext.get().destroy();
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView model) throws Exception {
    }

}