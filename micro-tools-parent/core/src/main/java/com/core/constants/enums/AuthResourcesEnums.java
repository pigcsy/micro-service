package com.core.constants.enums;

public class AuthResourcesEnums {

    public enum TypeEnum {
        SYSTEM("系统", (short) 0), MENU("菜单", (short) 1), RESOURCE("资源", (short) 2);
        // 成员变量
        private String name;
        private short value;

        // 构造方法
        private TypeEnum(String name, short value) {
            this.name = name;
            this.value = value;
        }

        // 覆盖方法
        @Override
        public String toString() {
            return this.value + "_" + this.name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public short getValue() {
            return value;
        }

        public void setValue(short value) {
            this.value = value;
        }
    }

    /**
     * 菜单级别
     *
     * @author Administrator
     */
    public enum LevelEnum {
        FIRST("第一级", 0), SECOND("第二级", 1), THIRD("第三级", 2);
        // 成员变量
        private String name;
        private int value;

        // 构造方法
        private LevelEnum(String name, int value) {
            this.name = name;
            this.value = value;
        }

        // 覆盖方法
        @Override
        public String toString() {
            return this.value + "_" + this.name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

}
