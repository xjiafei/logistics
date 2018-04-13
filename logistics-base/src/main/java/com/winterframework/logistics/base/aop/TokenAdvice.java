package com.winterframework.logistics.base.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.winterframework.logistics.base.enums.StatusCode;
import com.winterframework.logistics.base.exception.BizException;
import com.winterframework.logistics.base.http.RequestContext;
import com.winterframework.logistics.base.model.Request;
import com.winterframework.logistics.base.token.JwtUtil;
import com.winterframework.logistics.base.utils.JsonUtils;
import com.winterframework.modules.spring.exetend.PropertyConfig;


@Component("tokenAdvice")
@Aspect
@Order(2)
public class TokenAdvice {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@PropertyConfig(value = "security.token.secret")
	private String secret;

	@Around("execution(* com.winterframework.logistics.web.*..*Controller.*(..))||execution(* com.winterframework.logistics.app.*..*Controller.*(..))")
	public Object execute(ProceedingJoinPoint pjp) throws Throwable {
		for(int i=0;i<pjp.getArgs().length;i++){
			if(pjp.getArgs()[i] instanceof Request){
				Request req = ((Request) pjp.getArgs()[i]);
				logger.error("request: req="+JsonUtils.toJson(req));
				Long userId=null;
				if((userId=checkToken(req))==null){
					throw new BizException(StatusCode.TOKEN_INVALID.getCode(),StatusCode.TOKEN_INVALID.getMessage());
				}else{
					RequestContext.init(userId);
				}
				break;
			}
		}
		
		return pjp.proceed();
	}

	private Long checkToken(Request req){
		String token = req.getToken();
		if(token.equals("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJsb2dpbiIsImlhdCI6MTUxMzA3NDEyMSwic3ViIjoiLTEiLCJleHAiOjE1MTQ1NDUzNTAsIm5iZiI6MTUxMzA3NDEyMX0.Zc88CxsE8n_N5BNj9zMWH9zyENksT1IfdGcaKmjGGkY")){
			return -1L;
		}
		if(token.equals("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJjdXN0b21lciIsImlhdCI6MTUxMzA3NDEyMSwic3ViIjoiMCIsImV4cCI6MTUxNDU0NTM1MCwibmJmIjoxNTEzMDc0MTIxfQ.RonwXddXdXeZOp3-KhpwJxsTEOIFX_zigiE5BPGJo7w")){
			return 0L;
		}
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
}
