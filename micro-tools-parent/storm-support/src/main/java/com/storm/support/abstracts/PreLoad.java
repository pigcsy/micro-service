package com.storm.support.abstracts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.storm.support.utils.ApplicationContextUtil;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;

public abstract class PreLoad {
	
	protected Logger logger;
	protected ApplicationContext appContext;

	public final void load(TopologyContext context,
			SpoutOutputCollector collector) {
		logger = LoggerFactory.getLogger(getClass());
		String configFile = getSpringConfigFile();
		appContext = ApplicationContextUtil.getAppContext(configFile);
	}
	
	public abstract String getSpringConfigFile();
}
