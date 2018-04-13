package com.winterframework.logistics.web.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.winterframework.logistics.base.enums.StatusCode;
import com.winterframework.logistics.base.exception.BizException;
import com.winterframework.logistics.base.http.HttpUtil;
import com.winterframework.logistics.base.http.RequestContext;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.dto.UserResponse;
import com.winterframework.logistics.web.exception.WebException;
import com.winterframework.modules.spring.exetend.PropertyConfig;


@Component("contextAdvice")
@Aspect
@Order(101)
public class ContextAdvice {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@PropertyConfig(value="server.url.system")
	private String serverSystemUrl;
	@PropertyConfig(value="logistics.system.user.queryById")
	private String queryUserByIdPath;
	
	@Around("execution(* com.winterframework.logistics.web.*..*Controller.*(..))")
	public Object execute(ProceedingJoinPoint pjp) throws Throwable {
		Long userId=RequestContext.getUserId();
		if(userId!=0 && userId!=-1){
			try {
				Response<UserResponse> res=HttpUtil.http(serverSystemUrl, queryUserByIdPath,userId,UserResponse.class);
				if(res !=null && res.getStatus() ==StatusCode.SUCCESS.getCode() && res.getData()!=null) {
					RequestContext.get().setCompanyId(res.getData().getCompanyId());
				}else {
					throw new WebException(res.getStatus(),res.getMessage(),null);
				}
			} catch (BizException e) {
				log.error("context query user info error.userId="+userId,e);
				throw new WebException(e.getCode(),e.getMessage(),e);
			}
		}
		
		return pjp.proceed();
	}
}
