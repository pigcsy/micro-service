package com.storm.support.abstracts;

import com.beust.jcommander.Parameter;

public class AbstractBootConfig {

	@Parameter(names = { "-zk", "--zookeeper" }, description = "The zookeeper hosts", required = false)
	public String zkHosts;

	@Parameter(names = { "-kp", "--kafka-path" }, description = "The kafka path in zookeeper")
	public String kafkaPath = "/brokers";

	//从何时的offset时间开始读，默认为最旧的offset
	@Parameter(names = { "-sft", "--start-offset-time" }, description = "kafka start offset,-1 for the latest Kafka offset, and -2 for the earliest available offset.")
	public long startOffsetTime = kafka.api.OffsetRequest.EarliestTime();
	//设置为true，则每次拓扑重新启动时，都会从开头读取消息。如果为false，则：第一次启动，从开头读取，之后的重启均是从offset中读取
	@Parameter(names = { "-izo", "--ignore-zk-offsets" }, description = "kafka config ignoreZkOffsets")
	public boolean ignoreZkOffsets = false;

	@Parameter(names = { "-n", "--name" }, description = "The name of topology")
	public String topologyNm;

	@Parameter(names = { "-w", "--works" }, description = "The number of works")
	public int workerNum = 1;

	@Parameter(names = { "-s", "--spouts" }, description = "The number of spouts")

	public int spoutNum = 1;

	@Parameter(names = { "-b", "--bolts" }, description = "The number of bolts")
	public int boltsNum = 1;

	@Override
	public String toString() {
		return "AbstractBootConfig [zkHosts=" + zkHosts + ", kafkaPath=" + kafkaPath
				+ ", startOffsetTime=" + startOffsetTime + ", ignoreZkOffsets=" + ignoreZkOffsets
				+ ", topologyNm=" + topologyNm + ", workNum=" + workerNum + ", spoutNum=" + spoutNum
				+ ", boltsNum=" + boltsNum + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}


}
