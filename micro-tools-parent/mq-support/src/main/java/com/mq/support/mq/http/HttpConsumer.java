package com.mq.support.mq.http;

import com.alibaba.fastjson.JSON;
import com.aliyun.openservices.ons.api.impl.authority.AuthUtil;
import com.core.utils.MD5;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * mq HTTP 接收消息代码
 *
 * @author Maven
 *         <pre>
 *                                                             _oo0oo_
 *                                                            o8888888o
 *                                                            88" . "88
 *                                                            (| -_- |)
 *                                                            0\  =  /0
 *                                                          ___/`---'\___
 *                                                        .' \\|     |// '.
 *                                                       / \\|||  :  |||// \
 *                                                      / _||||| -:- |||||- \
 *                                                     |   | \\\  -  /// |   |
 *                                                     | \_|  ''\---/''  |_/ |
 *                                                     \  .-\__  '-'  ___/-. /
 *                                                   ___'. .'  /--.--\  `. .'___
 *                                                ."" '<  `.___\_<|>_/___.' >' "".
 *                                               | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *                                               \  \ `_.   \_ __\ /__ _/   .-` /  /
 *                                           =====`-.____`.___ \_____/___.-`___.-'=====
 *                                                             `=---='
 *                                           ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *                                                     佛祖开光         永无BUG
 *                                         </pre>
 */
@ConfigurationProperties(locations = "classpath:application.properties", prefix = "spring.mq")
@Data
public class HttpConsumer {

    private static final Logger log = LogManager.getLogger();
    private static final String NEWLINE = "\n";
    private String accessKey;
    private String secretKey;
    private String consumerId;
    private String url;
    private String topic;

    public List<SimpleMessage> pull() {
        List<SimpleMessage> result = null;
        long time = System.currentTimeMillis();
        GetRequest req = Unirest.get(url);
        String signString = topic + NEWLINE + consumerId + NEWLINE + time;
        String sign = AuthUtil.calSignature(signString.getBytes(StandardCharsets.UTF_8), secretKey);
        req.header("Signature", sign);
        req.header("AccessKey", accessKey);
        req.header("ConsumerID", consumerId);
        req.queryString("topic", topic);
        req.queryString("time", time);
        req.queryString("num", 32);

        try {
            HttpResponse<String> res = req.asString();
            if (res.getStatus() == 200) {
                if (res.getBody() != null && !res.getBody().isEmpty()) {
                    try {
                        result = JSON.parseArray(res.getBody(), SimpleMessage.class);
                    } catch (Exception e) {
                        log.error("get message error", e);
                    }
                }
            }
        } catch (UnirestException e) {
            log.error("get message error", e);
        }
        return result;
    }

    public boolean delete(String msgHandle) {
        long time = System.currentTimeMillis();
        HttpRequestWithBody req = Unirest.delete(url);
        String signString = topic + NEWLINE + consumerId + NEWLINE + MD5.getInstance().getMD5String(msgHandle) + NEWLINE
                + time;
        String sign = AuthUtil.calSignature(signString.getBytes(StandardCharsets.UTF_8), secretKey);
        req.header("Signature", sign);
        req.header("AccessKey", accessKey);
        req.header("ConsumerID", consumerId);
        req.queryString("topic", topic);
        req.queryString("time", time);
        req.queryString("msgHandle", msgHandle);
        try {
            HttpResponse<String> res = req.asString();
            if (res.getStatus() == 204) {
                return true;
            } else {
                log.error("delete message error: {}", msgHandle, res.getBody());
            }
        } catch (UnirestException e) {
            log.error("delete message error: {}", msgHandle, e);
        }
        return false;
    }


}
