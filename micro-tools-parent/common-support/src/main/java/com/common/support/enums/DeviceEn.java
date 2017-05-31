package com.common.support.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ${DESCRIPTION}
 *
 * @author csy
 * @date 2017/4/11 下午4:30
 */
@Getter
@AllArgsConstructor
public enum DeviceEn {
    PC("PC", (byte) 1), WP("window_phone", (byte) 2), ANDROID("安卓", (byte) 3), IOS("IOS", (byte) 4), ADMIN("后台管理员", (byte) 90),
    SYSTEM("系统", (byte) 99);
    private String name;
    private byte value;

    public static DeviceEn toEnumByV(byte v) {
        for (DeviceEn en : DeviceEn.values()) {
            if (en.getValue() == v)
                return en;
        }
        return null;
    }
}
