package com.cache.support.vo.redis.value.database;

import com.cache.support.vo.redis.value.base.BaseRedisVo;
import lombok.Data;

/**
 * ${DESCRIPTION}
 *
 * @author csy
 * @date 2017/3/31 下午1:31
 */
@Data
public class ServiceCache extends BaseRedisVo {
    private static final long serialVersionUID = -6137401611346241469L;
    private Integer id;

    private String code;

    private String name;

    private Integer pid;

    private Integer sort;

    private Byte level;

    private Byte isLast;

    private String desc;

    private byte standardFlag;

    private ServiceCache parent;

    private Byte status;
}
