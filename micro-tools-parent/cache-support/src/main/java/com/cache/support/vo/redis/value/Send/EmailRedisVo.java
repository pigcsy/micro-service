package com.cache.support.vo.redis.value.Send;

import com.cache.support.vo.redis.value.base.BaseRedisVo;
import lombok.Data;

import java.util.Map;

/**
 * ${DESCRIPTION}
 *
 * @author csy
 * @date 2017/4/13 下午3:59
 */
@Data
public class EmailRedisVo extends BaseRedisVo {
    private static final long serialVersionUID = -2524850809258944052L;
    private String code;
    // private String user;
    // private String warnReason;
    // private List<String> mobiles;
    private Map<String, Object> params;
}
