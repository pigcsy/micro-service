package com.cache.support.vo.redis.value.base;

import lombok.Data;

import java.io.Serializable;

/**
 * ${DESCRIPTION}
 *
 * @author csy
 * @date 2017/3/13 上午11:21
 */
@Data
public class BaseRedisVo implements Serializable {
    private static final long serialVersionUID = 3013403520005715340L;
    protected long createTm = System.currentTimeMillis();
}
