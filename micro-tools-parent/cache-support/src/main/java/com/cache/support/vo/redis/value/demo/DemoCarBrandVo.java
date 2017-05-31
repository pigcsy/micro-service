package com.cache.support.vo.redis.value.demo;

import com.cache.support.vo.redis.value.base.BaseRedisVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ${DESCRIPTION}
 *
 * @author csy
 * @date 2017/3/13 上午11:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemoCarBrandVo extends BaseRedisVo {
    private static final long serialVersionUID = 7644280962936071032L;
    private Integer id;
    private String brand;
    private String name;
}
