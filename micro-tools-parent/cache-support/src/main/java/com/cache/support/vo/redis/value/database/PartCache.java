package com.cache.support.vo.redis.value.database;

import com.cache.support.vo.redis.value.base.BaseRedisVo;
import lombok.Data;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author csy
 * @date 2017/3/28 下午2:24
 */
@Data
public class PartCache extends BaseRedisVo {
    private static final long serialVersionUID = -1898461082663615989L;
    /**
     * 配件分类id
     */
    private Integer id;

    /**
     * 配件分类名称
     */
    private String name;

    /**
     * 配件分类父项id
     */
    private Integer pid;

    /**
     * 是否删除
     */
    private Byte isDel;

    /**
     * 排序
     */
    private String sort;

    /**
     * 别名
     */
    private String tag;

    /**
     * 是否是最后一级
     */
    private Byte isLast;

    /**
     * 描述
     */
    private String description;

    /**
     * 直接子集id
     */
    private List<Integer> directChildrenId;
}
