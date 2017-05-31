package com.core.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.util.Asserts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class IPUtils {

	private static final String url = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json";
	
	private static Logger logger = LoggerFactory.getLogger(IPUtils.class);
	
	/**
	 * 查找ip所在区域
	 * @param ip
	 * @return
	 */
	public static String getRegionByIp(String ip){
		Map<String,Object> params = new HashMap<>();
		params.put("ip", ip);
		try {
			 String res = HttpClientUtil.get(url, params);
			 JSONObject json = JSONObject.parseObject(res);
			 Asserts.notNull(json, "city is null");
			 return json.getString("city");
		} catch (Exception e) {
			logger.error("ip={}所属的区域未找到",ip);
		}
		return null;
	}

	
	 public static String getIpAddr(HttpServletRequest request) {
		 String ip = request.getHeader("x-forwarded-for");
		 if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		    ip = request.getHeader("Proxy-Client-IP");
		  }
		 if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		    ip = request.getHeader("WL-Proxy-Client-IP");
		 }
		 if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		    ip = request.getRemoteAddr();
		 }
		 return ip;
	 }

}
