package com.cache.support.vo.redis.value.demo;

import com.cache.support.vo.redis.value.base.BaseRedisVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ${DESCRIPTION}
 *
 * @author csy
 * @date 2017/3/13 上午11:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemoSmsCodeVo extends BaseRedisVo {
    private static final long serialVersionUID = 3216049549344586805L;
    private String mobile;
    private String type;
}
