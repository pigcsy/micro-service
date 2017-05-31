package com.safty.service;

import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.cache.support.redis.RedisCacheManager;
import com.core.base.AbstractService;
import com.core.constants.BlackWhiteCacheConst;
import com.safty.domain.entity.BlackWhiteData;
import com.safty.repositories.BlackWhiteDataRepository;


@Service
public class BlackWhiteService extends AbstractService<BlackWhiteData, Integer> {

	private static final long serialVersionUID = 3781123694317719559L;

	private Logger logger = LoggerFactory.getLogger(BlackWhiteService.class);

	@Autowired
	RedisCacheManager redisCacheManager;
	
	@Autowired
	BlackWhiteDataRepository blackWhiteDataRepository;
	
	@Override
	protected BlackWhiteDataRepository getRepository() {
		return blackWhiteDataRepository;
	}
	
	
	
	public void queryBlackResource() {
		List<BlackWhiteData> list = blackWhiteDataRepository.findAll();
		logger.debug("加载到黑/白名单资源{}", list);
		if (!CollectionUtils.isEmpty(list)){
			for (BlackWhiteData data : list){
				if (0 == data.getType()){
					redisCacheManager.putSet(BlackWhiteCacheConst.blackServiceKey+data.getUrl(), data.getIpAddress(), 0);
				} else {
					redisCacheManager.putSet(BlackWhiteCacheConst.whiteServiceKey+data.getUrl(), data.getIpAddress(), 0);
				}
			}
		}
	}

	/**
	 * 把缓存中的黑名单添加到库
	 */
	public void checkUnSaveBlack() {
		List<String> ip_urlList = redisCacheManager.getListViaNum(BlackWhiteCacheConst.waitForAddBlack, String.class, 100);
		if (CollectionUtils.isEmpty(ip_urlList)) {
			return ;
		}
		List<BlackWhiteData> blackList = parseList(ip_urlList);
		if (CollectionUtils.isEmpty(blackList)){
			return ;
		}
		logger.debug("扫描到的待入库的黑名单{}", JSON.toJSON(blackList));
		blackWhiteDataRepository.save(blackList);
	}

	private List<BlackWhiteData> parseList(List<String> ip_urlList) {
		List<BlackWhiteData> list = new ArrayList<>();
		String[] arr = new String[2];
		for (String str : ip_urlList){
			arr = str.split("_");
			boolean isExist = redisCacheManager.isInSet(BlackWhiteCacheConst.blackServiceKey+arr[1], arr[0]);
			if (!isExist) {
				BlackWhiteData black = new BlackWhiteData(arr[0], arr[1], 0);
				list.add(black);
				//添加到拦截缓存中
				redisCacheManager.putSet(BlackWhiteCacheConst.blackServiceKey+arr[1], arr[0], 0);
			}
		}
		return list;
	}

	
	
	

}
