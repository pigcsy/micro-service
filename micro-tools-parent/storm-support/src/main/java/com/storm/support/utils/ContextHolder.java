package com.storm.support.utils;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;


public final class ContextHolder {
	
	private static Logger logger = LoggerFactory.getLogger(ContextHolder.class);
	
	/**
	 * Default Constructure
	 */
	private ContextHolder(){}

	private static ApplicationContext ctx;

	public static void setCtx(ApplicationContext context) {
		if (context == null) {
			throw new IllegalArgumentException("get the Spring Application Context is null.");
		}
		ctx = context;
		logger.warn("has been injected with ApplicationContext successfully.");
	}

	public static ApplicationContext getCtx() {
		while (ctx == null) {
			logger.warn("Can not get ApplicationContext, Please wait spring context load complete.");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				logger.error("Wait get ApplicationContext exception.", e);
			}
		}
		return ctx;
	}

	/**
	 * 根据name获取对象
	 * @param name
	 * @return
	 */
	public static Object getBean(String name) {
		Object obj = getCtx().getBean(name);
		return obj;
	}

	/**
	 * 根据类型获取对象
	 * @param c
	 * @return
	 */
	public static <T> T getBean(Class<T> c) {
		T obj = getCtx().getBean(c);
		return obj;
	}
	
	/**
	 * Return a Resource handle for the specified resource.
	 * The handle should always be a reusable resource descriptor,
	 * allowing for multiple {@link Resource#getInputStream()} calls.
	 * <p><ul>
	 * <li>Must support fully qualified URLs, e.g. "file:C:/test.dat".
	 * <li>Must support classpath pseudo-URLs, e.g. "classpath:test.dat".
	 * <li>Should support relative file paths, e.g. "WEB-INF/test.dat".
	 * (This will be implementation-specific, typically provided by an
	 * ApplicationContext implementation.)
	 * </ul>
	 * <p>Note that a Resource handle does not imply an existing resource;
	 * you need to invoke {@link Resource#exists} to check for existence.
	 * @param location the resource location
	 * @return a corresponding Resource handle
	 * @see #CLASSPATH_URL_PREFIX
	 * @see org.springframework.core.io.Resource#exists
	 * @see org.springframework.core.io.Resource#getInputStream
	 */
	public static Resource getResource(String location) {
		return getCtx().getResource(location);
	}
	
	/**
	 * Resolve the given location pattern into Resource objects.
	 * <p>Overlapping resource entries that point to the same physical
	 * resource should be avoided, as far as possible. The result should
	 * have set semantics.
	 * @param locationPattern the location pattern to resolve
	 * @return the corresponding Resource objects
	 * @throws IOException in case of I/O errors
	 */
	public static Resource[] getResources(String locationPattern) throws IOException {
		return getCtx().getResources(locationPattern);
	}

}
