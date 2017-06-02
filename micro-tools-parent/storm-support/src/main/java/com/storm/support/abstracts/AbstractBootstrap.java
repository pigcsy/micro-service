package com.storm.support.abstracts;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.generated.StormTopology;
import com.beust.jcommander.JCommander;
import com.storm.support.scheme.AbstractMultiScheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storm.kafka.KafkaSpout;
import storm.kafka.SpoutConfig;
import storm.kafka.ZkHosts;

public abstract class AbstractBootstrap {
    public static Logger logger = LoggerFactory.getLogger(AbstractBootstrap.class);
    public boolean debug = false;

    public AbstractBootConfig parseConfig(String[] args) {
        AbstractBootConfig config = new AbstractBootConfig();
        new JCommander(config, args);
        logger.info("input args :" + config.toString());
        return config;
    }

    public void run(String[] args) {
        logger.info("-------------start  boot-------------");
        AbstractBootConfig mainConfig = this.parseConfig(args);
        Config config = new Config();
        config.put(Config.TOPOLOGY_TRIDENT_BATCH_EMIT_INTERVAL_MILLIS, 500);
        config.setNumWorkers(mainConfig.workerNum);
        config.setNumAckers(0);
        logger.info("config :" + config.toString());
        try {
            if (debug == true) {
                new LocalCluster().submitTopology(mainConfig.topologyNm, config,
                        this.buildTopology(mainConfig));
            } else {
                StormSubmitter.submitTopology(mainConfig.topologyNm, config,
                        this.buildTopology(mainConfig));
            }
            logger.info("-----------successed------------");
        } catch (AlreadyAliveException e) {
            e.printStackTrace();
        } catch (InvalidTopologyException e) {
            e.printStackTrace();
        }
        logger.info("-------------end-------------");
    }

    public KafkaSpout buildKafkaSpout(AbstractBootConfig config, AbstractMultiScheme<?> scheme, String topic, String id) {
        SpoutConfig spoutConfig = new SpoutConfig(new ZkHosts(config.zkHosts, config.kafkaPath), topic, "/" + config.topologyNm, id);
        if (scheme != null)
            spoutConfig.scheme = scheme;
        spoutConfig.ignoreZkOffsets = true;
        spoutConfig.startOffsetTime = -1;
        spoutConfig.metricsTimeBucketSizeInSecs = 120;
        spoutConfig.stateUpdateIntervalMs = 10000;
        return new KafkaSpout(spoutConfig);
    }

    public abstract StormTopology buildTopology(AbstractBootConfig config);
}
