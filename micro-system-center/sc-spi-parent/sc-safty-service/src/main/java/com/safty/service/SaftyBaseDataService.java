package com.safty.service;

import com.core.base.AbstractService;
import com.core.utils.DateUtils;
import com.core.utils.FileStreamUtils;
import com.safty.domain.entity.SaftyBaseData;
import com.safty.repositories.SaftyBaseDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

;


@Service
public class SaftyBaseDataService extends AbstractService<SaftyBaseData, Integer>{

	
	private static final long serialVersionUID = 7906370511310004893L;
	
	@Autowired
	private SaftyBaseDataRepository saftyBaseDataService;
	
	private Logger logger = LoggerFactory.getLogger(SaftyBaseDataService.class);
	
	@Override
	protected SaftyBaseDataRepository getRepository() {
		return saftyBaseDataService;
	}
	
	
	public void saveBaseDataList(String path){
		List<String> list = FileStreamUtils.readListFromFile(path);
		logger.debug("读取日志文件，获取数据{}", list);
		List<SaftyBaseData> dataList = parseListToBase(list);
		logger.debug("转换为安全基础数据={}", com.alibaba.fastjson.JSON.toJSON(dataList));
		saftyBaseDataService.save(dataList);
	}
	
	@SuppressWarnings("static-access")
	public  List<SaftyBaseData> parseListToBase(List<String> list){
		if (CollectionUtils.isEmpty(list)){
			return null;
		}
		List<SaftyBaseData> dataList = new ArrayList<>();
		String[] arr = null;
		String params = "";
		String ip = "";
		Date date = null;
		String mode = "";
		String url = "";
		for (String str : list){
			arr = str.split("\\|");
			ip = arr[0].trim().equals("-") ? arr[5] : arr[0].trim();
			date = DateUtils.parseNginxDate(arr[1]);
			mode = arr[2].substring(0, arr[2].indexOf("/"));
			url = arr[2].substring(arr[2].indexOf("/")).replace("HTTP/1.1", "").trim();
			if (url.indexOf("?") > -1){
				params = url.substring(url.indexOf("?")+1);
			} else {
				params = arr[9];
			}
			URLDecoder decoder = new URLDecoder();
			try {
				params = decoder.decode(params.replace("\\x22", "'").replace("\\x", "%"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error("params="+params+"解码失败", e);
			}
			SaftyBaseData data = new SaftyBaseData(ip, date, DateUtils.date2String(date, "yyyy-MM-dd"),
					new BigDecimal(arr[7]), url, mode, arr[3], params);
			dataList.add(data);
		}
		return dataList;
	}
	
	
}
