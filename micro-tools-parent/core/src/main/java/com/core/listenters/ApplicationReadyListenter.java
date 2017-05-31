package com.core.listenters;

import com.core.support.hystrix.SecurityContextRegistratorCommandHook;
import com.netflix.hystrix.strategy.HystrixPlugins;
import org.slf4j.Logger;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationReadyListenter implements ApplicationListener<ApplicationReadyEvent> {
    Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            try {
                HystrixPlugins.getInstance().registerCommandExecutionHook(new SecurityContextRegistratorCommandHook());
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
}