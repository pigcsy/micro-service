package com.core.constants.enums;

public class ResourcesEnum {

    public enum ResourcesPidType {
        // '菜单级别：0第一级，1第二级，2第三级，3页面资源'
        FIRST("第一级", (byte) 0), SECOND("第二级", (byte) 1), THIRD("第三级", (byte) 2), PAGERESOURCES("页面资源", (byte) 3),;
        private String name;
        private Byte value;

        ResourcesPidType(String name, Byte value) {
            this.name = name;
            this.value = value;
        }

        public static ResourcesPidType toEnumByV(byte value) {
            for (ResourcesPidType en : ResourcesPidType.values()) {
                if (en.getValue() == value) {
                    return en;
                }
            }
            return null;
        }

        public String getName() {
            return name;
        }

        public Byte getValue() {
            return value;
        }
    }

    public enum ResourcesTypeEn {
        //类型 0 系统 1 菜单 2 资源URL
        SYSTEM("系统", (byte) 0), MENU("菜单", (byte) 1), RESOURCES("资源URL", (byte) 2);
        private String name;
        private byte value;

        ResourcesTypeEn(String name, Byte value) {
            this.name = name;
            this.value = value;
        }

        public static ResourcesTypeEn toEnumByV(byte value) {
            for (ResourcesTypeEn en : ResourcesTypeEn.values()) {
                if (en.getValue() == value) {
                    return en;
                }
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


    public enum ResourcesStatus {
        //	0 否 1是
        NO("否", (byte) 0), YES("是", (byte) 1),;
        private String name;
        private byte value;

        ResourcesStatus(String name, Byte value) {
            this.name = name;
            this.value = value;
        }

        public static ResourcesStatus toEnumByV(byte value) {
            for (ResourcesStatus en : ResourcesStatus.values()) {
                if (en.getValue() == value) {
                    return en;
                }
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

}
