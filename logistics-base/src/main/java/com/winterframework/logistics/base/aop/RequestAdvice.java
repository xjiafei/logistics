package com.winterframework.logistics.base.aop;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.winterframework.logistics.base.enums.StatusCode;
import com.winterframework.logistics.base.exception.BizException;
import com.winterframework.logistics.base.model.Request;
import com.winterframework.logistics.base.utils.DateUtils;
import com.winterframework.logistics.base.utils.JsonUtils;
import com.winterframework.logistics.base.utils.SignUtil;


@Component("requestAdvice")
@Aspect
@Order(1)
public class RequestAdvice {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Around("execution(* com.winterframework.logistics.app.*..*Controller.*(..)) || execution(* com.winterframework.logistics.web.*..*Controller.*(..))")
	public Object execute(ProceedingJoinPoint pjp) throws Throwable {
		for(int i=0;i<pjp.getArgs().length;i++){
			if(pjp.getArgs()[i] instanceof Request){
				Request req = ((Request) pjp.getArgs()[i]);
				logger.error("request: req="+JsonUtils.toJson(req));
				/*if(!checkTimestamp(req.getTimestamp())){
					throw new BizException(StatusCode.REQ_EXPIRED.getCode(),StatusCode.REQ_EXPIRED.getMessage());
				}*/
				/*else if(!checkSign(req)){
					throw new BizException(StatusCode.SIGN_INVALID.getCode(),StatusCode.SIGN_INVALID.getMessage());
				}*/
				break;
			}
		}
		
		return pjp.proceed();
	}
	private boolean checkSign(Request req){
		Map<String,String> params=new HashMap<String,String>();
		params.put("token", req.getToken());
		params.put("timestamp", req.getTimestamp()+"");
		params.put("data", JsonUtils.toJson(req.getData()));
		//排序dataStr
		String dataStr=JsonUtils.toJson(req.getData());
		char[] cs=dataStr.toCharArray();
		Arrays.sort(cs);
		return SignUtil.verify(req.getSign(), req.getToken()+req.getTimestamp()+new String(cs).replace("\"", ""));
	}
	
	//5分钟内的请求有效
	private boolean checkTimestamp(Long timestamp){
		return DateUtils.calcMinutesBetween(DateUtils.getDate(timestamp), DateUtils.currentDate())<=5;
	}
}
