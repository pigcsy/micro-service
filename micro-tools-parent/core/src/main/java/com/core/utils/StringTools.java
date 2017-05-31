package com.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ${DESCRIPTION}
 *
 * @author 轴承
 * @date 2017/4/13 下午4:58
 */
public class StringTools {

    public static String format(String target, Map<String, Object> map) {
        if (StringUtils.isBlank(target))
            return "";
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            target = target.toString().replaceAll("#\\{" + entry.getKey() + "}", entry.getValue().toString());
        }

        return target;
    }

    /**
     * 根据正则取出对应变量值
     *
     * @param regex    eg. (^.*(?<year>\d{4}款?)\s+(?<target>\d\.\d(T|L))(\s+.+$))
     * @param variable eg. ${year}/${target}
     * @return
     */
    public static String subString(String regex, String variable, String string) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        String route = matcher.replaceFirst(variable);
        return route;
    }

    public static void main(String[] args) {
        String carInfo = "上海通用 雪佛兰迈锐宝 三厢 2014款 1.6T 手动 SL 舒适版";
        System.out.println(StringTools.subString("^.*(?<year>\\d{4})(款|年).*$", "${year}", carInfo));

    }
}
