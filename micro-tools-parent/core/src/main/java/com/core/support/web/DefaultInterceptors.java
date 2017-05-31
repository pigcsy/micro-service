package com.core.support.web;

import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSON;
import com.core.utils.WebUtils;
import org.slf4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultInterceptors extends HandlerInterceptorAdapter {
	Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("HTTP request [url]{}[/url][params]{}[/params]", request.getRequestURL(),
				WebUtils.getRequestParams(request));
		return super.preHandle(request, response, handler);
	}


	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.error("HTTP response ERROR:{}",ex);
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("HTTP response:{}",JSON.toJSON(handler));
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

}
