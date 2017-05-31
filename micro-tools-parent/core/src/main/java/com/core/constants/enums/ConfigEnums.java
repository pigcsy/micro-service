package com.core.constants.enums;

public class ConfigEnums {

    public enum PlaceEnum {
        // 0 导航 1 首页 2 底部 3 侧边
        NAV((byte) 1, "导航"), INDEX((byte) 2, "首页"), FOOTER((byte) 3, "底部"), SIDE((byte) 4, "侧边"), ABOUT((byte) 5, "关于网站"), BANNER((byte) 6, "首页广告"), OTHER((byte) -1,
                "未知");
        private byte code;
        private String mean;

        private PlaceEnum(byte code, String mean) {
            this.code = code;
            this.mean = mean;
        }

        public static PlaceEnum toEnum(byte code) {
            for (PlaceEnum en : PlaceEnum.values()) {
                if (en.getCode() == code)
                    return en;
            }
            return OTHER;
        }

        public byte getCode() {
            return code;
        }

        public String getMean() {
            return mean;
        }
    }

    public enum TypeEnum {
        // 0 文字 1 图片 2视频
        WORD((byte) 0, "文字"), IMG((byte) 1, "图片"), VIDEO((byte) 2, "视频"),;
        private byte code;
        private String mean;

        private TypeEnum(byte code, String mean) {
            this.code = code;
            this.mean = mean;
        }

        public static TypeEnum toEnum(byte code) {
            for (TypeEnum en : TypeEnum.values()) {
                if (en.getCode() == code)
                    return en;
            }
            return null;
        }

        public byte getCode() {
            return code;
        }

        public String getMean() {
            return mean;
        }
    }

    public enum EntityTypeEnum {
        // 0 导航 1 首页 2 底部 3 侧边
        ARTICLE((byte) 0, "文章"), ARTICLE_TYPE((byte) 1, "文章分类");
        private byte code;
        private String mean;

        private EntityTypeEnum(byte code, String mean) {
            this.code = code;
            this.mean = mean;
        }

        public static EntityTypeEnum toEnum(byte code) {
            for (EntityTypeEnum en : EntityTypeEnum.values()) {
                if (en.getCode() == code)
                    return en;
            }
            return null;
        }

        public byte getCode() {
            return code;
        }

        public String getMean() {
            return mean;
        }
    }

}
