package com.core.utils;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 使用http client连接池
 *
 * @author 轴承
 * @date 2017/4/11 上午10:28
 */
@Slf4j
@Getter
@Setter
public class PoolingHttpClientUtil {

    private static final String NET_HTTP = "http";
    private static final String NET_HTTPS = "https";
    private final static Object syncLock = new Object();
    private int timeOut = 60;
    private int maxTotal = 20;
    private int maxPerRoute = 20;
    private int retryTimes = 5;
    // static final int timeOut = 10 * 1000;
    private String charset = "utf-8";
    private CloseableHttpClient httpClient = null;

    public PoolingHttpClientUtil() {
        init();
    }

    /**
     * 设置请求头信息
     *
     * @param headers
     * @param request
     * @return
     */
    private static HttpRequest setHeaders(Map<String, Object> headers, HttpRequest request) {
        for (Map.Entry entry : headers.entrySet()) {
            if (!entry.getKey().equals("Cookie")) {
                request.addHeader((String) entry.getKey(), (String) entry.getValue());
            } else {
                Map<String, Object> Cookies = (Map<String, Object>) entry.getValue();
                for (Map.Entry entry1 : Cookies.entrySet()) {
                    request.addHeader(new BasicHeader("Cookie", (String) entry1.getValue()));
                }
            }
        }
        return request;
    }

    private void config(HttpRequestBase httpRequestBase) {
        // 设置Header等
        // httpRequestBase.setHeader("User-Agent", "Mozilla/5.0");
        // httpRequestBase
        // .setHeader("Accept",
        // "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        // httpRequestBase.setHeader("Accept-Language",
        // "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");// "en-US,en;q=0.5");
        // httpRequestBase.setHeader("Accept-Charset",
        // "ISO-8859-1,utf-8,gbk,gb2312;q=0.7,*;q=0.7");

        // 配置请求的超时设置
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(timeOut)
                .setConnectTimeout(timeOut).setSocketTimeout(timeOut).build();
        httpRequestBase.setConfig(requestConfig);
    }

    /**
     * 获取HttpClient对象
     *
     * @return
     * @author SHANHY
     * @create 2015年12月18日
     */
    public CloseableHttpClient getHttpClient(String url) {
        if (httpClient == null) {
            synchronized (syncLock) {
                if (httpClient == null) {
                    // httpClient = createHttpClient(200, 40, 100, hostname, port);
                    httpClient = init();
                }
            }
        }
        return httpClient;
    }

    /**
     * 创建HttpClient对象
     *
     * @return
     * @author SHANHY
     * @create 2015年12月18日
     */
    public CloseableHttpClient init() {
        log.info("== [Http Client Config] == \n timeOut = {}, maxTotal = {}, maxPerRoute = {}, retryTimes = {}, charset = {}",
                getTimeOut(), getMaxTotal(), getMaxPerRoute(), getRetryTimes(), getCharset());
        ConnectionSocketFactory factory = PlainConnectionSocketFactory
                .getSocketFactory();
        LayeredConnectionSocketFactory sslFactory = SSLConnectionSocketFactory
                .getSocketFactory();
        Registry<ConnectionSocketFactory> registry = RegistryBuilder
                .<ConnectionSocketFactory>create().register(NET_HTTP, factory)
                .register(NET_HTTPS, sslFactory).build();
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(
                registry);
        // 将最大连接数增加
        cm.setMaxTotal(maxTotal);
        // 将每个路由基础的连接增加
        cm.setDefaultMaxPerRoute(maxPerRoute);
        // HttpHost httpHost = new HttpHost(hostname, port);
        // 将目标主机的最大连接数增加
        // cm.setMaxPerRoute(new HttpRoute(httpHost), maxRoute);

        // 请求重试处理
        HttpRequestRetryHandler httpRequestRetryHandler = (exception, executionCount, context) -> {
            if (executionCount >= retryTimes) {// 如果已经重试了5次，就放弃
                return false;
            }
            if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
                return true;
            }
            if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
                return false;
            }
            if (exception instanceof InterruptedIOException) {// 超时
                return false;
            }
            if (exception instanceof UnknownHostException) {// 目标服务器不可达
                return false;
            }
            if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
                return false;
            }
            if (exception instanceof SSLException) {// SSL握手异常
                return false;
            }

            HttpClientContext clientContext = HttpClientContext
                    .adapt(context);
            HttpRequest request = clientContext.getRequest();
            // 如果请求是幂等的，就再次尝试
            if (!(request instanceof HttpEntityEnclosingRequest)) {
                return true;
            }
            return false;
        };

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .setRetryHandler(httpRequestRetryHandler).build();
        // .build();

        this.httpClient = httpClient;
        return httpClient;
    }

    private void setPostParams(HttpPost httPost,
                               Map<String, Object> params) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            nvps.add(new BasicNameValuePair(key, params.get(key).toString()));
        }
        try {
            httPost.setEntity(new UrlEncodedFormEntity(nvps, charset));
        } catch (UnsupportedEncodingException e) {
            log.error("set Post Params error {}", JSON.toJSONString(params), e);
        }
    }

    /**
     * get请求
     *
     * @param url
     * @param headers
     * @return
     */
    public String httpGet(String url, Map<String, Object> headers) {
        CloseableHttpClient httpClient = getHttpClient();
        HttpRequest httpGet = new HttpGet(url);
        if (headers != null && !headers.isEmpty()) {
            httpGet = setHeaders(headers, httpGet);
        }
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute((HttpGet) httpGet);

            if (!"200".equals(response.getStatusLine().getStatusCode())) {
                log.info("== [网络异常] == {}", JSON.toJSONString(response));
                throw new RuntimeException("网络异常");
            }

            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    /**
     * post请求,使用json格式传参
     *
     * @param url
     * @param headers
     * @param data
     * @return
     */
    public String httpPost(String url, Map<String, Object> headers, String data) {
        CloseableHttpClient httpClient = getHttpClient();
        HttpRequest request = new HttpPost(url);
        if (headers != null && !headers.isEmpty()) {
            request = setHeaders(headers, request);
        }
        CloseableHttpResponse response = null;

        try {
            HttpPost httpPost = (HttpPost) request;
            httpPost.setEntity(new StringEntity(data, ContentType.create("application/json", "UTF-8")));
            response = httpClient.execute(httpPost);

            if (!"200".equals(response.getStatusLine().getStatusCode())) {
                throw new RuntimeException("网络异常");
            }

            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }

    /**
     * 使用表单键值对传参
     */
    public String PostForm(String url, Map<String, Object> headers, List<NameValuePair> data) {

        log.info("== [发送请求] ==\n url = {}, parameters = {}", url, JSON.toJSONString(data));

        CloseableHttpClient httpClient = getHttpClient();
        HttpRequest request = new HttpPost(url);
        if (headers != null && !headers.isEmpty()) {
            request = setHeaders(headers, request);
        }
        CloseableHttpResponse response = null;
        UrlEncodedFormEntity uefEntity;
        try {
            HttpPost httpPost = (HttpPost) request;
            uefEntity = new UrlEncodedFormEntity(data, "UTF-8");
            httpPost.setEntity(uefEntity);

            // httpPost.setEntity(new StringEntity(data, ContentType.create("application/json", "UTF-8")));
            response = httpClient.execute(httpPost);

            if (200 != response.getStatusLine().getStatusCode()) {
                throw new RuntimeException("网络异常");
            }

            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }

    /**
     * GET请求URL获取内容
     *
     * @param url
     * @return
     * @throws IOException
     * @author SHANHY
     * @create 2015年12月18日
     */
    public String post(String url, Map<String, Object> params) throws IOException {
        HttpPost httppost = new HttpPost(url);
        config(httppost);
        setPostParams(httppost, params);
        CloseableHttpResponse response = null;
        try {
            response = getHttpClient(url).execute(httppost,
                    HttpClientContext.create());
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "utf-8");
            EntityUtils.consume(entity);
            return result;
        } catch (Exception e) {
            log.error("http post error {} >> {}", url, JSON.toJSONString(params), e);
            throw e;
        } finally {
            try {
                if (response != null)
                    response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * GET请求URL获取内容
     *
     * @param url
     * @return
     * @author SHANHY
     * @create 2015年12月18日
     */
    public String get(String url) {
        HttpGet httpget = new HttpGet(url);
        config(httpget);
        CloseableHttpResponse response = null;
        try {
            response = getHttpClient(url).execute(httpget,
                    HttpClientContext.create());
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, charset);
            EntityUtils.consume(entity);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null)
                    response.close();
            } catch (IOException e) {
                log.error("http get error");
            }
        }
        return null;
    }

}
