package com.mq.support.mq.http;/**
 * Created by maven on 2017/3/1.
 */


import com.aliyun.openservices.ons.api.impl.authority.AuthUtil;
import com.core.utils.MD5;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.charset.StandardCharsets;

/**
 * mq HTTP 发送消息代码
 *
 * @author Maven
 *         <pre>
 *                                                     _oo0oo_
 *                                                    o8888888o
 *                                                    88" . "88
 *                                                    (| -_- |)
 *                                                    0\  =  /0
 *                                                  ___/`---'\___
 *                                                .' \\|     |// '.
 *                                               / \\|||  :  |||// \
 *                                              / _||||| -:- |||||- \
 *                                             |   | \\\  -  /// |   |
 *                                             | \_|  ''\---/''  |_/ |
 *                                             \  .-\__  '-'  ___/-. /
 *                                           ___'. .'  /--.--\  `. .'___
 *                                        ."" '<  `.___\_<|>_/___.' >' "".
 *                                       | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *                                       \  \ `_.   \_ __\ /__ _/   .-` /  /
 *                                   =====`-.____`.___ \_____/___.-`___.-'=====
 *                                                     `=---='
 *                                   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *                                             佛祖开光         永无BUG
 *                                 </pre>
 */
@ConfigurationProperties(locations = "classpath:application.properties", prefix = "spring.mq")
@Data
public class HttpProducer {

    private static final String NEWLINE = "\n";
    private static final Logger log = LogManager.getLogger();
    private String accessKey;
    private String secretKey;
    private String producerId;
    private String url;
    private String topic;

    /**
     * 发送普通消息
     *
     * @param msg
     * @param tag
     * @param key
     * @return
     */
    public boolean send(String msg, String tag, String key) {
        return send(msg, tag, key, null);
    }

    /**
     * 发送定时消息
     *
     * @param msg
     * @param tag
     * @param key
     * @param startDeliverTime
     * @return
     */
    public boolean send(String msg, String tag, String key, Long startDeliverTime) {
        long time = System.currentTimeMillis();
        HttpRequestWithBody req = Unirest.post(url);
        String signString = topic + NEWLINE + producerId + NEWLINE + MD5.getInstance().getMD5String(msg) + NEWLINE
                + time;
        String sign = AuthUtil.calSignature(signString.getBytes(StandardCharsets.UTF_8), secretKey);
        req.header("Signature", sign);
        req.header("AccessKey", accessKey);
        req.header("ProducerID", producerId);
        req.queryString("topic", topic);
        req.queryString("time", time);
        if (startDeliverTime != null) {
            req.queryString("startdelivertime", startDeliverTime);
        }
        req.body(msg);

        try {
            HttpResponse<String> res = req.asString();
            if (res.getStatus() == 201) {
                return true;
            } else {
                log.error("post message error: {}", msg, res.getBody());
            }
        } catch (UnirestException e) {
            log.error("post message error: {}", msg, e);
        }
        return false;
    }


}

