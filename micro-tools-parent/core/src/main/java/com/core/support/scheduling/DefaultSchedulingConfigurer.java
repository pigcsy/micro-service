package com.core.support.scheduling;

import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
@EnableScheduling
@EnableAsync(mode = AdviceMode.PROXY, proxyTargetClass = false, order = Ordered.HIGHEST_PRECEDENCE)
public class DefaultSchedulingConfigurer extends AsyncConfigurerSupport implements SchedulingConfigurer {
	@Bean
	public ThreadPoolTaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setPoolSize(20);
		scheduler.setAwaitTerminationSeconds(80);
		scheduler.setWaitForTasksToCompleteOnShutdown(true);
		return scheduler;
	}

	@Override
	public Executor getAsyncExecutor() {
		Executor executor = this.taskScheduler();
		return executor;
	}

	@Override
	public void configureTasks(ScheduledTaskRegistrar registrar) {
		TaskScheduler scheduler = this.taskScheduler();
		registrar.setTaskScheduler(scheduler);
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		// TODO Auto-generated method stub
		return new SimpleAsyncUncaughtExceptionHandler();
	}
}