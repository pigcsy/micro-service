package com.cache.support.vo.redis.value.database;

import com.cache.support.vo.redis.value.base.BaseRedisVo;
import lombok.Data;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author csy
 * @date 2017/3/29 下午4:22
 */
@Data
public class RegionCache extends BaseRedisVo {
    private static final long serialVersionUID = -8029914914207175742L;
    private Integer id;
    private Integer pid;
    private String name;
    private String firstWord;
    private String phonetic;
    private Byte level;
    private List<Integer> directChildrenId;
}
