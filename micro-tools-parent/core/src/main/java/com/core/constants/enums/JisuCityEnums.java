package com.core.constants.enums;

/**
 * @author cy
 * @version $Id: JisuCityEnums, v0.1 2016年12月09日 9:54 cy Exp $
 */
public enum JisuCityEnums {

    HANGZHOU("1", "hangzhou"),

    BEIJING("2", "beijing"),

    GUANGZHOU("3", "guangzhou"),

    SHANGHAI("4", "shanghai"),

    CHANGSHA("5", "changsha");
    private String code;

    private String msg;

    private JisuCityEnums(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static JisuCityEnums getEnum(String code) {
        JisuCityEnums[] list = values();
        for (JisuCityEnums emun : list) {
            if (null == emun) {
                continue;
            }

            if (code.equals(emun.getCode())) {
                return emun;
            }
        }
        return null;
    }

    /**
     * Getter method for property <tt>code</tt>.
     *
     * @return property value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Getter method for property <tt>msg</tt>.
     *
     * @return property value of msg
     */
    public String getMsg() {
        return msg;
    }
}
