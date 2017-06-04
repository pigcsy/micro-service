/**
 * Project Name:-shop-api
 * File Name:ErrorCode.java
 * Package Name:com..shop.api.common.exception
 * Date:2016年5月31日下午4:21:24
 * Copyright (c) 2016, 蚂上配件 Ltd. All Rights Reserved.
 */
package com.core.exception;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * ClassName: ErrorCode <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 *
 * @author 半轴
 * @date: 2016年5月31日 下午4:21:24 <br/>
 * @since JDK 1.8
 */
public class ErrorHolder {

    public static String getCode(CodeTemp templ) {
        return templ.getCode();
    }

    public static String getMessage(CodeTemp templ, String... params) {
        String regContent = templ.getTemp();
        int index = 0;
        while (regContent.indexOf("{}") >= 0) {
            if (ArrayUtils.isEmpty(params) || params.length <= index) {
                if (regContent.indexOf("{}") >= 0) {
                    regContent = regContent.replaceAll("\\{\\}", "");
                }
                break;
            }
            regContent = StringUtils.replaceOnce(regContent, "{}", params[index]);
            index++;
        }

        return regContent;
    }

    /**
     * ClassName: CodeTempl <br/>
     * Function: TODO ADD FUNCTION. <br/>
     * Reason: TODO ADD REASON(可选). <br/>
     * 错误编码， 系统级别 10001开始
     * 逻辑级别20001开始
     *
     * @author 半轴
     * @version ErrorCode
     * @date: 2016年5月31日 下午4:31:33 <br/>
     * @since JDK 1.8
     */
    public enum CodeTemp {
        SUCCESS("00000", "成功"),
        UNKNOW("10000", "未知错误,{}"),
        GATEWAY("502", "网关错误"),

        ILLEGAL_ACCESS("10001", "非法访问,{}"),
        INVALID_TOKEN("10002", "登录失效"),
        INVALID_AUTHENTICATION("10006", "鉴权失败"),

        UN_LOGIN("10003", "未登录"),
        NO_PERMISSION("10004", "无权访问"),
        //参数错误
        ILLEGAL_ARGUMENT("20000", "{}"),
        //逻辑错误
        LOGIC_ERR("30000", "{}"),
        //已存在
        HAS_EXISTED("31001", "{}"),
        //未找到
        UNFOUND("31002", "{}"),
        //重复修改
        REPEAT_MODIFY("31003", "{}"),

        UNKNOWN_ANDROID_SOURCE("31200", "当前版本存在风险,请前往官网下载"),
        //支付错误
        PAY_ERR("33001", "{}"),;;
        String code;
        String temp;

        CodeTemp(String code, String temp) {
            this.temp = temp;
            this.code = code;
        }

        public static CodeTemp toEnum(String code) {
            for (CodeTemp enums : CodeTemp.values()) {
                if (enums.getCode().equals(code)) {
                    return enums;
                }
            }
            return CodeTemp.UNKNOW;
        }

        public String getCode() {
            return code;
        }

        public String getTemp() {
            return temp;
        }

    }
}
