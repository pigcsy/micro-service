package com.core.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * 发送http请求工具
 *
 * @author 轴承
 * @date 16/8/19 下午1:02
 */
public class HttpClientTools {
    private static Logger logger = LoggerFactory.getLogger(HttpClientTools.class);

    /**
     * GET请求
     *
     * @param url
     * @param charset
     * @return
     */
    public static String httpGet(String url, String charset) {
        logger.info("发送HTTP GET 请求 地址 {}", url);
        System.out.println(String.format("发送HTTP GET 请求 地址 %s", url));
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            // 创建httpget.
            HttpGet httpGet = new HttpGet(url);
            RequestConfig config = RequestConfig.custom().setSocketTimeout(10 * 1000).setConnectTimeout(10 * 1000).build();
            httpGet.setConfig(config);
            // 执行get请求.
            try (CloseableHttpResponse response = client.execute(httpGet)) {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                // 打印响应状态
                if (entity != null) {
                    return EntityUtils.toString(entity, charset);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("发送GET请求失败", e);
        }
        return null;
    }

    /**
     * get请求
     *
     * @param url     请求链接
     * @param param   参数map
     * @param charset 编码格式
     * @return 请求响应字符串
     */
    public static String httpGet(String url, Map<String, String> param, String charset) {
        if (param != null) {
            String paramStr = buildGetParam(param);
            if (StringUtils.isNotBlank(paramStr)) {
                return httpGet(url + "?" + paramStr, charset);
            }
        }
        return httpGet(url, charset);
    }

    public static <T> String httpGet(String url, T param, String charset) {
        String paramStr = buildGetParam(JSON.toJSONString(param));
        if (StringUtils.isNotBlank(paramStr)) {
            return httpGet(url + "?" + paramStr, charset);
        }
        return httpGet(url, charset);
    }

    /**
     * 发送post请求
     *
     * @param url
     * @param paramStr
     * @param charset
     * @return
     */
    public static String httpPost(String url, String paramStr, String charset) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(url);
            RequestConfig config = RequestConfig.custom().setSocketTimeout(10 * 1000).setConnectTimeout(10 * 1000).build();
            post.setConfig(config);
            post.setEntity(new StringEntity(paramStr, charset));
            try (CloseableHttpResponse response = client.execute(post)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    return EntityUtils.toString(entity, charset);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * form表单提交
     *
     * @param url
     * @param charset
     * @param parameters
     * @return
     */
    public static String httpForm(String url, List<NameValuePair> parameters, String charset) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(url);
            RequestConfig config = RequestConfig.custom().setSocketTimeout(10 * 1000).setConnectTimeout(10 * 1000).build();
            post.setConfig(config);
            // post.setEntity(new StringEntity(paramStr, charset));
            post.setEntity(new UrlEncodedFormEntity(parameters, charset));
            logger.info(">> 头部信息 {}", JSON.toJSONString(post.getAllHeaders()));

            try (CloseableHttpResponse response = client.execute(post)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    return EntityUtils.toString(entity, charset);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 构造get请求参数
     *
     * @param map
     * @return
     */
    private static String buildGetParam(Map<String, String> map) {
        StringBuffer sb = new StringBuffer();
        map.entrySet().forEach(target -> {
            if (StringUtils.isNotBlank(target.getKey()) && StringUtils.isNotBlank(target.getValue())) {
                sb.append(target.getKey()).append('=').append(target.getValue()).append("&");
            }
        });
        return sb.length() > 0 ? sb.substring(0, sb.length() - 1) : "";
    }

    /**
     * 构造get请求参数
     *
     * @param paramJson
     * @return
     */
    private static String buildGetParam(String paramJson) {
        if (StringUtils.isBlank(paramJson))
            return "";
        JSONObject jsonObject = JSON.parseObject(paramJson);
        StringBuffer sb = new StringBuffer();
        jsonObject.entrySet().forEach(target -> {
            if (StringUtils.isNotBlank(target.getKey()) && target.getValue() != null) {
                sb.append(target.getKey()).append('=').append(target.getValue()).append("&");
            }
        });
        return sb.length() > 0 ? sb.substring(0, sb.length() - 1) : "";
    }
}
