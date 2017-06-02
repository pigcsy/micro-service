package com.safty.scheduled;

import com.safty.service.SaftyBaseDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@Configuration
@EnableScheduling
public class SaftyBaseDataScheduled {


    private Logger logger = LoggerFactory.getLogger(SaftyBaseDataScheduled.class);

    @Autowired
    private SaftyBaseDataService saftyBaseDataService;


    @Scheduled(cron = "0 0/1 * * * *")
    public void checkUnSaveBlack() {
        logger.info("从nginx中获取基础数据开始");
        String path = "E:/tools/nginx-1.9.9/logs_access.log";
        saftyBaseDataService.saveBaseDataList(path);
        logger.info("从nginx中获取基础数据结束");
    }
}
