package com.storm.support.utils;


import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
 


@Component
public class ApplicationContextUtil {

	private static ApplicationContext appContext;


	public static ApplicationContext getAppContext() {
		return initializeContext();
	}
	public static ApplicationContext getAppContext(String fileName) {
		if(StringUtils.isBlank(fileName)){
			return initializeContext();
		}else{
			return initializeContext(fileName);
		}
		
	}
	public static ApplicationContext initializeContext() {
		synchronized (ApplicationContextUtil.class) {
			if (appContext == null) {
				appContext = new ClassPathXmlApplicationContext("classpath*:**/applicationContext.xml");
				ContextHolder.setCtx(appContext);
			}
		}
		return appContext;
	}
	public static ApplicationContext initializeContext(String fileName) {
		synchronized (ApplicationContextUtil.class) {
			if (appContext == null) {
				appContext = new ClassPathXmlApplicationContext("classpath*:**/"+fileName);
				ContextHolder.setCtx(appContext);
			}
		}
		return appContext;
	}
}
