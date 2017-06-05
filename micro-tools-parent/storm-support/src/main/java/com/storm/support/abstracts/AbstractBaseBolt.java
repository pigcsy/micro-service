package com.storm.support.abstracts;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.base.BaseBasicBolt;
import com.storm.support.utils.ApplicationContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.Map;

public abstract class AbstractBaseBolt extends BaseBasicBolt {

    private static final long serialVersionUID = -1896254821616492317L;

    public Logger logger;
    protected TopologyContext context;
    protected ApplicationContext appContext;

    @Override
    @SuppressWarnings("rawtypes")
    public void prepare(Map stormConf, TopologyContext context) {
        String configFile = getSpringConfigFile();
        appContext = ApplicationContextUtil.getAppContext(configFile);
        this.context = context;
        logger = LoggerFactory.getLogger(getClass());
        prepareInternal(stormConf, context);
    }

    public abstract void prepareInternal(@SuppressWarnings("rawtypes") Map stormConf, TopologyContext context);


    public abstract String getSpringConfigFile();

}
