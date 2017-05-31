package com.storm.support.abstracts;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.storm.support.utils.ApplicationContextUtil;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;

public abstract class AbstractBaseSpout extends BaseRichSpout {

	private static final long serialVersionUID = 7598113415649695593L;

	protected Logger logger;
	protected TopologyContext context;
	protected SpoutOutputCollector collector;
	protected ApplicationContext appContext;

	public final void open(@SuppressWarnings("rawtypes") Map conf, TopologyContext context,
			SpoutOutputCollector collector) {
		String configFile = getSpringConfigFile();
		appContext = ApplicationContextUtil.getAppContext(configFile);
		this.context = context;
		this.collector = collector;
		logger = LoggerFactory.getLogger(getClass());
		openInternal(conf, context, collector);
	}

	@SuppressWarnings("rawtypes")
	public abstract void openInternal(Map conf, TopologyContext context,SpoutOutputCollector collector);

	public abstract void declareOutputFields(OutputFieldsDeclarer declarer);

	/**
	 * 启用 ack机制，详情参考：https://github.com/alibaba/jstorm/wiki/Ack-%E6%9C%BA%E5%88%
	 * B6
	 * 
	 * @param msgId
	 */
	@Override
	public void ack(Object msgId) {
		super.ack(msgId);
	}

	/**
	 * 消息处理失败后需要自己处理
	 * 
	 * @param msgId
	 */
	@Override
	public void fail(Object msgId) {
		super.fail(msgId);
		logger.error("流计算 ack 失败,msgId:" + msgId);
	}

	/**
	 * 本拓扑配置文件名,如：applicationContext.xml
	 * 
	 * @return
	 */
	public abstract String getSpringConfigFile();

}
