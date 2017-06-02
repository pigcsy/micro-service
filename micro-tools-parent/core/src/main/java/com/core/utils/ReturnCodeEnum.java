package com.core.utils;

/**
 * @author 金麒麟
 *         响应码
 */
public enum ReturnCodeEnum {
    SUCCESS("00000", "成功"),
    UNKNOW("10000", "未知错误"),
    INVAILD("10001", "非法访问"),
    LOGIN_EXPIRE("10002", "登录失效"),
    UNLOGIN("10003", "未登录"),
    NOAUTH("10004", "无权限"),
    PARAMER_ERROR("20000 ", "参数错误"),
    LOGIC_ERROR("30000 ", "参数错误");

    private String code;
    private String msg;

    ReturnCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
