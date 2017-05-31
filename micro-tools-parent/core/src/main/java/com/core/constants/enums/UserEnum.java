package com.core.constants.enums;

import java.util.HashMap;
import java.util.Map;

public class UserEnum {
    // 0-修理厂，1-车主，2-经销商，3-保险公司，4-员工(备用)，
    public enum UserType {
        REPAIR("员工", (byte) 1), CAROWNER("", (byte) 1), DEALER("经销商", (byte) 2), INSURER("保险公司",
                (byte) 3), STAFF("员工", (byte) 4), SYSTEM("系统", (byte) 99), OPENAPI("openAPi", (byte) 100);
        private String name;
        private byte value;

        UserType(String name, byte value) {
            this.name = name;
            this.value = value;
        }

        public static UserType toEnumByV(byte v) {
            for (UserType en : UserType.values()) {
                if (en.getValue() == v)
                    return en;
            }
            return null;
        }

        public String getName() {
            return name;
        }

        public byte getValue() {
            return value;
        }

    }

    /**
     * 用户状态：0-正常，1-审核中，2-审核失败，3-禁用，4-未审核，5.删除(删除后不可回复)
     */
    public enum UserStatusEn {
        NORMAL("正常", (byte) 0), CHECKING("审核中", (byte) 1), CHECK_FAIL("审核失败", (byte) 2),
        DISABLED("禁用", (byte) 3), UN_CHECK("未审核", (byte) 4), DELETE("删除", (byte) 5);

        private String name;
        private byte value;

        UserStatusEn(String name, byte value) {
            this.name = name;
            this.value = value;
        }

        public static UserStatusEn toEnumByV(byte v) {
            for (UserStatusEn en : UserStatusEn.values()) {
                if (en.getValue() == v)
                    return en;
            }
            return null;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public byte getValue() {
            return value;
        }

        public void setValue(byte value) {
            this.value = value;
        }
    }

    /**
     * 用户操作枚举 <br/>
     * 1禁用，0删除，2启用
     */
    public enum UserOperaEn {
        DISABLED("禁用", (byte) 1), DELETE("删除", (byte) 0), ENABLED("启用", (byte) 2);
        private String name;
        private byte value;

        UserOperaEn(String name, byte value) {
            this.name = name;
            this.value = value;
        }

        public static UserOperaEn toEnumByV(byte v) {
            for (UserOperaEn en : UserOperaEn.values()) {
                if (en.getValue() == v)
                    return en;
            }
            return null;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public byte getValue() {
            return value;
        }

        public void setValue(byte value) {
            this.value = value;
        }
    }

    public enum StaffStatus {
        HASLFT("已离职", (byte) 0), PRACTICE("实习期", (byte) 1), FULLWOKER("正式工", (byte) 2);
        private String name;
        private byte value;

        StaffStatus(String name, byte value) {
            this.name = name;
            this.value = value;
        }

        public static StaffStatus toEnumByV(byte v) {
            for (StaffStatus en : StaffStatus.values()) {
                if (en.getValue() == v)
                    return en;
            }
            return null;
        }

        public static Map<String, Object> toMap() {
            Map<String, Object> enMap = new HashMap<>();
            StaffStatus[] ens = values();
            for (StaffStatus statusEn : ens) {
                Map<String, Object> vMap = new HashMap<>();
                vMap.put("name", statusEn.getName());
                vMap.put("value", statusEn.getValue());
                enMap.put(statusEn.name(), vMap);
            }
            return enMap;
        }

        public String getName() {
            return name;
        }

        public byte getValue() {
            return value;
        }
    }
}


