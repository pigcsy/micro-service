package com.cache.support.vo.redis.value.yunpush;

import com.cache.support.vo.redis.value.base.BaseRedisVo;
import lombok.Data;

/**
 * ${DESCRIPTION}
 *
 * @author csy
 * @date 2017/4/11 下午7:11
 */
@Data
public class BaiduYunPushVo extends BaseRedisVo {
    private static final long serialVersionUID = 2057248738228211817L;

    public static final int TO_SINGLE = 1;
    public static final int TO_ALL = 2;

    private Integer id;
    private String apiKey;
    private String secretKey;
    private Byte deviceType;
    private String pushChannelID;
    private String message;
    private String productName;
    private int pushType;
}
