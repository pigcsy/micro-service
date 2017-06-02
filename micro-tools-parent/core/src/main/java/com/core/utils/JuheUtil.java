package com.core.utils;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author cy
 * @version $Id: JuheUtil, v0.1 2016年12月05日 15:13 cy Exp $
 */
@Component
public class JuheUtil {

    public static final String DEF_CHARSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 10000;
    public static final int DEF_READ_TIMEOUT = 10000;
    public static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";


    /**
     * redis的key生成策略
     *
     * @param plateNumber
     * @param cityCode
     * @param engineNumber
     * @param vehicleNumber
     * @return
     */
    public static String getRedisCacheKey(String plateNumber, String cityCode, String engineNumber, String vehicleNumber, String repoId) {
        return plateNumber + "_" + cityCode + "_" + engineNumber + "_" + vehicleNumber + "_" + repoId;
    }

    public static String getHasAppPusheKey(String plateNumber, String cityCode, String engineNumber, String vehicleNumber) {
        return plateNumber + "_" + cityCode + "_" + engineNumber + "_" + vehicleNumber + "_" + "Key";
    }

    /**
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return 网络请求字符串
     * @throws Exception
     */
    public static String net(String strUrl, Map params, String method, Boolean isTimeout) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if (method == null || method.equals("GET")) {
                strUrl = strUrl + "?" + urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if (method == null || method.equals("GET")) {
                conn.setRequestMethod("GET");
            } else {
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            if (isTimeout) {
                conn.setConnectTimeout(DEF_CONN_TIMEOUT);
                conn.setReadTimeout(DEF_READ_TIMEOUT);
            }
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params != null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    return rs;
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHARSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }

    //将map型转为请求参数型
    public static String urlencode(Map<String, Object> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
