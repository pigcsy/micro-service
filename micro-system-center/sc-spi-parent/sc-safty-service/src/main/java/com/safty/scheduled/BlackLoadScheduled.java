package com.safty.scheduled;

import com.safty.service.BlackWhiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@Configuration
@EnableScheduling
public class BlackLoadScheduled {


    private Logger logger = LoggerFactory.getLogger(BlackLoadScheduled.class);

    @Autowired
    private BlackWhiteService blackWhiteService;

    @Scheduled(cron = "0 0/5 * * * *")
    public void checkData() {
        logger.info("加载黑名单定时器启动");
        blackWhiteService.queryBlackResource();
        logger.info("加载黑名单定时器结束");
    }

    @Scheduled(cron = "0 0/1 * * * *")
    public void checkUnSaveBlack() {
        blackWhiteService.checkUnSaveBlack();
    }
}
