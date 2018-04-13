package com.winterframework.logistics.device.service.scheduler;

import java.lang.reflect.Method;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component("detailQuartzStateJobBean")
@DisallowConcurrentExecution
public class DetailQuartzStateJobBean extends QuartzJobBean{
	private String targetObject;
	private String targetMethod;
	private final ApplicationContext applicationContext;

	public DetailQuartzStateJobBean() {
		this.applicationContext = ApplicationContextUtil.getContext();
	}

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			Object otargetObject = applicationContext.getBean(targetObject);
			Method m = null;
			try {
				m = otargetObject.getClass().getMethod(targetMethod, new Class[] {});
				m.invoke(otargetObject, new Object[] {});
			} catch (SecurityException e) {

			} catch (NoSuchMethodException e) {

			}
		} catch (Exception e) {
			throw new JobExecutionException(e);
		}
	}

	public void setTargetObject(String targetObject) {
		this.targetObject = targetObject;
	}

	public void setTargetMethod(String targetMethod) {
		this.targetMethod = targetMethod;
	}
}