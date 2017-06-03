package com.common.support.enums;

/**
 * 系统
 *
 * @author Administrator
 */
public enum SystemEn {
    ADMIN(1, "员工", "AD_"), PLATFORM(2, "平台", "PF_"), MICRO_USER(3, "用户", "MICRO_U_"), TOURIST(90, "游客", "T_");
    private int code;
    private String mean;
    // 对应角色前缀
    private String rolePrefix;

    private SystemEn(int code, String mean, String rolePrefix) {
        this.code = code;
        this.mean = mean;
        this.rolePrefix = rolePrefix;
    }

    public static SystemEn toEnum(Integer code) {
        for (SystemEn en : SystemEn.values()) {
            if (en.getCode() == code)
                return en;
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getMean() {
        return mean;
    }

    public String getRolePrefix() {
        return rolePrefix;
    }

}
