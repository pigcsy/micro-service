package com.common.support.enums.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ${DESCRIPTION}
 *
 * @author csy
 * @date 2017/4/13 上午9:17
 */
public class PushLogEnum {

    /**
     * 推送通知系统来源: 易修通等
     */
    @Getter
    @AllArgsConstructor
    public enum SourceEn {
        ;
        private String name;
        private byte value;

        public static SourceEn toEnumByV(byte v) {
            for (SourceEn en : SourceEn.values()) {
                if (en.getValue() == v)
                    return en;
            }
            return null;
        }
    }

    /**
     * 推送状态
     */
    @Getter
    @AllArgsConstructor
    public enum PushStatusEn {
        WAITING("待推送", (byte) 0), SUCCESS("发送成功", (byte) 1), FAILED("发送失败", (byte) 2), ALREADY_DEAL("已处理", (byte) 3);
        private String name;
        private byte value;

        public static PushStatusEn toEnumByV(byte v) {
            for (PushStatusEn en : PushStatusEn.values()) {
                if (en.getValue() == v)
                    return en;
            }
            return null;
        }
    }

    /**
     * 推送通道类型
     */
    @Getter
    @AllArgsConstructor
    public enum ChannelTypeEn {
        BAIDU("百度", (byte) 0);
        private String name;
        private byte value;

        public static ChannelTypeEn toEnumByV(byte v) {
            for (ChannelTypeEn en : ChannelTypeEn.values()) {
                if (en.getValue() == v)
                    return en;
            }
            return null;
        }
    }

    /**
     * 推送日志状态
     */
    @Getter
    @AllArgsConstructor
    public enum StatusEn {
        NEVER_READ("未读", (byte) 0), ALREADY_READ("已读", (byte) 1), DELETE("删除", (byte) 2);

        private String name;
        private byte value;

        public static StatusEn toEnumByV(byte v) {
            for (StatusEn en : StatusEn.values()) {
                if (en.getValue() == v)
                    return en;
            }
            return null;
        }
    }

}
