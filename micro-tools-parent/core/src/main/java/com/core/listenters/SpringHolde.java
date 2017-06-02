package com.core.listenters;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获取spring容器内的bean
 */
@Component
public class SpringHolde implements ApplicationContextAware {

    private static ApplicationContext springContext;

    public SpringHolde() {
        super();
    }

    public static ApplicationContext getApplicationContext() {
        return springContext;
    }

    @SuppressWarnings("static-access")
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.springContext = applicationContext;
    }

    public static <T> T getBean(Class<T> type) throws BeansException {
        return springContext.getBean(type);
    }

    public static boolean isInitialization() {
        return springContext != null;
    }

    public static Object getBean(String name) throws BeansException {
        return springContext.getBean(name);
    }
}
