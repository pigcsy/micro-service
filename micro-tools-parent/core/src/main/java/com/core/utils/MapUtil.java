package com.core.utils;

import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 金麒麟
 */
public class MapUtil {

    /**
     * 使用 Map按key进行排序
     *
     * @param map
     * @return
     */
    public static String sortStringByKey(Map<String, String> map, String charActer) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, String> sortMap = new TreeMap<>(new Comparator<String>() {

            @Override
            public int compare(String str1, String str2) {
                return str1.compareTo(str2);
            }
        });
        sortMap.putAll(map);

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : sortMap.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
            sb.append(charActer);
        }
        return sb.substring(0, sb.length() - 1);
    }

    @SuppressWarnings("rawtypes")
    public static String coverMap2String(Map<String, String> data) {

        // 去除Map中value为空的键值对
        Iterator<String> iter = data.keySet().iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            if (!StringUtils.isNotBlank(data.get(key))) {
                iter.remove();
                data.remove(key);
            }

        }
        //排序
        Iterator it = data.entrySet().iterator();
        StringBuffer sf = new StringBuffer();
        while (it.hasNext()) {
            Map.Entry en = (Map.Entry) it.next();
            sf.append(new StringBuilder().append((String) en.getKey()).append("=").append((String) en.getValue())
                    .append("&").toString());
        }
        return sf.substring(0, sf.length() - 1);
    }


    /**
     * 移除值为空的数据项
     *
     * @param map
     * @return
     */
    public static Map<String, String> removeNull(Map<String, String> map) {

        Map<String, String> resultMap = new HashMap<>();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (StringUtils.isNotBlank(entry.getValue())) {
                resultMap.put(entry.getKey(), entry.getValue());
            }
        }
        return resultMap;
    }

    /**
     * JSON转MAP
     *
     * @param jsonStr
     * @return
     */
    public static Map<String, Object> parseJSON2Map(String jsonStr) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (jsonStr == null || "".equals(jsonStr.trim()))
            return map;
        //最外层解析  
        JSONObject json = JSONObject.fromObject(jsonStr);
        for (Object k : json.keySet()) {
            Object v = json.get(k);
            if (v instanceof JSONNull)
                v = null;
            map.put(k.toString(), v);
        }
        return map;
    }

    /**
     * 使用 Map按value进行排序
     *
     * @param map
     * @return
     */
    public static String sortStringByValue(Map<String, String> map, String charActer) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, String> sortMap = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                return str1.compareTo(str2);
            }
        });
        sortMap.putAll(map);
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : sortMap.entrySet()) {
            sb.append(entry.getValue());
            sb.append(charActer);
        }
        return sb.toString();
    }
}
