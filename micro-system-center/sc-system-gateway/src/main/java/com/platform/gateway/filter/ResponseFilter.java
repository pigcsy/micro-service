package com.platform.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.common.support.enums.SystemEn;
import com.core.constants.HttpConstant;
import com.core.exception.MicroException;
import com.core.exception.ErrorHolder.CodeTemp;
import com.core.support.security.DefaultCurrentPrincipal;
import com.core.support.web.domain.DefaultResult;
import com.core.support.web.domain.ErrorResult;
import com.core.utils.HttpErrorDecoder;
import com.core.utils.IPUtils;
import com.platform.gateway.provider.client.AccessClient;
import com.platform.gateway.provider.request.AccessRequestVo;
import com.netflix.util.Pair;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.post.SendResponseFilter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;

@Component
public class ResponseFilter extends SendResponseFilter {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AccessClient accessClient;


	@Value("${spring.application.name:unknown}")
	private String gatewayName;


	public ResponseFilter() {

	}

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}
	
	private Object tryParseResult(String result){
		try {
			return JSON.parse(result);
		} catch (Exception e) {
		}
		return result;
	}
	@Override
	public Object run() {
		RequestContext context = RequestContext.getCurrentContext();
		try {
			String result = this.getResponseData(context);
			logger.error("状态：{},信息:{}",context.getResponseStatusCode(),result);
			if(result == null){
				return null;
			}
			preSetHeader(context);//设置头部信息
			HttpStatus statusEn = HttpStatus.valueOf(context.getResponseStatusCode());
			if(statusEn == HttpStatus.OK) {
				ErrorResult errorResult = HttpErrorDecoder.decode(result);
				if(errorResult != null) {
					context.setResponseStatusCode(errorResult.getHttpStatus());
					context.setResponseBody(JSON.toJSONString(new DefaultResult<>(errorResult.getMessage(), errorResult.getCode())));
				}else{
					context.setResponseBody(JSON.toJSONString(new DefaultResult(tryParseResult(result))));
				}
			} else {
				MicroException exception = new MicroException("服务异常");
				if (context.getThrowable() != null) {
					exception = new MicroException(context.getThrowable().getCause().getMessage());
				}

				context.setResponseBody(JSON.toJSONString(new DefaultResult(exception.getMessage(),exception.getErrorCode())));
			}
//			
//			if(statusEn == HttpStatus.OK){
//				preSetHeader(context);// 非500异常需设置头部json响应
//				context.setResponseBody(JSON.toJSONString(new DefaultResult(tryParseResult(result))));
//				return null;
//			}
//			context.setResponseStatusCode(statusEn.value());
//			if(statusEn != HttpStatus.OK) {
//				preSetHeader(context);// 非500异常需设置头部json响应
//			Exception exception = HttpErrorDecoder.decode(HttpStatus.INTERNAL_SERVER_ERROR, result);
//				context.setResponseBody(JSON.toJSONString(new DefaultResult(exception.getMessage(), exception.getErrorCode())));	
//				return null;
//			} else {
//				preSetHeader(context);// 非500异常需设置头部json响应
//				cleanResponse(context);
//				context.setResponseBody(JSON.toJSONString(new DefaultResult("网关错误", String.valueOf(HttpStatus.BAD_GATEWAY))));
//				return null;
//			} 
		} catch (IOException e) {
			context.setResponseBody(JSON.toJSONString(new DefaultResult("未知错误", CodeTemp.UNKNOW.getCode())));
		} finally {
			try {
				incrementLimitNum();
			} catch (Exception e) {
				logger.error("增加访问次数", e);
			}
			logger.info("消费时间URI-->{},----->{}",context.getRequest().getRequestURI(),(System.currentTimeMillis() - (long)context.getRequest().getAttribute("req_time")));
		}

		return null;
	}

	private String getResponseData(RequestContext context) throws IOException {
		HttpStatus statusEn = HttpStatus.valueOf(context.getResponseStatusCode());
		if(statusEn != HttpStatus.OK && context.get("error.exception") != null){
			logger.error("zuul异常",context.get("error.exception"));
			return "服务异常";
		}
		
		// 流文件输出，不进行包装
		if (context.getZuulResponseHeaders().stream().anyMatch(header -> HttpConstant.DOWNLOAD_HEADER.equalsIgnoreCase(header.first()))) {
			return null;
		}
		// 重组响应参数
		if (StringUtils.isNotBlank(context.getResponseBody())) {
			return context.getResponseBody();
		} else if(context.getResponseDataStream() != null){
			return StreamUtils.copyToString(context.getResponseDataStream(), Charset.forName("UTF-8"));
		} else {
			return "未知错误";
		} 
	}
	
	/**
	 * 清除响应
	 * 
	 * @param context
	 * @throws IOException
	 */
	private void cleanResponse(RequestContext context) throws IOException {
		if (context.getResponseBody() != null)
			context.setResponseBody(null);
		if (context.getResponseDataStream() != null)
			context.setResponseDataStream(null);
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return ZuulTypeEn.post.getMean();
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return -1;
	}

	private void preSetHeader(RequestContext context) {
		context.getZuulResponseHeaders().add(new Pair<String, String>(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8"));
		context.getZuulResponseHeaders().add(new Pair<String, String>("Access-Control-Allow-Origin", "*"));
		context.getZuulResponseHeaders().add(new Pair<String, String>("Access-Control-Allow-Credentials", "true"));
		context.getZuulResponseHeaders().add(new Pair<String, String>("Access-Control-Allow-Headers", "Authorization,Source,Content-type,X-Requested-With,DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type, Accept-Language, Origin, Accept-Encoding"));
		context.getZuulResponseHeaders().add(new Pair<String, String>("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS"));
	}

	/**
	 * 访问接口次增加
	 */
	private void incrementLimitNum() {
		DefaultCurrentPrincipal principal = getPrincipal();
		AccessRequestVo vo = new AccessRequestVo();
		if (principal != null) {
			vo.setGateway(gatewayName);
			vo.setSystemCode(principal.getSystemType());
			vo.setCustomerId(principal.getUid().toString());
		} else {
			RequestContext ctx = RequestContext.getCurrentContext();
			HttpServletRequest request = ctx.getRequest();
			String ip = IPUtils.getIpAddr(request);

			vo.setGateway(gatewayName);
			vo.setCustomerId(ip);
			vo.setSystemCode(SystemEn.TOURIST.getCode());
		}
		accessClient.increment(vo);
	}


	public static boolean hasAuthorized() {
		return SecurityContextHolder.getContext().getAuthentication() != null
				&& SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && SecurityContextHolder
				.getContext().getAuthentication().getPrincipal() instanceof DefaultCurrentPrincipal;
	}

	private DefaultCurrentPrincipal getPrincipal() {
		if (hasAuthorized()) {
			return (DefaultCurrentPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} else {
			return null;
		}
	}


}
