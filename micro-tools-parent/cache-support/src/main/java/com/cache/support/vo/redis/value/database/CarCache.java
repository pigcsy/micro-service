package com.cache.support.vo.redis.value.database;

import com.cache.support.vo.redis.value.base.BaseRedisVo;
import lombok.Data;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author csy
 * @date 2017/3/28 下午2:26
 */
@Data
public class CarCache extends BaseRedisVo {
    private static final long serialVersionUID = 8258427314289612091L;
    /**
     * 汽车类型id,唯一标志
     */
    private Integer carId;

    /**
     * 汽车品牌logo,只有level1
     */
    private String brandLogo;

    /**
     * 车型厂商
     */
    private String company;

    /**
     * 车型品牌
     */
    private String brand;

    /**
     * 车系名称
     */
    private String series;

    /**
     * 年款
     */
    private String year;

    /**
     * 信息名称,不同层级显示不同类型名称
     */
    private String name;

    /**
     * 对应车型的level1的id,level2以下含leve2车型有值
     */
    private Integer level1Id;

    /**
     * 对应车型的leve2的id, leve3以下含level3车型有值
     */
    private Integer level2Id;

    /**
     * 对应车型的level3的id, level4以下含level4车型有值
     */
    private Integer level3Id;

    /**
     * 信息层级
     */
    private Byte level;

    /**
     * 父项id
     */
    private Integer pid;

    /**
     * 首字母
     */
    private String firstWord;

    /**
     * 进口或者国产
     */
    private String importInfo;

    /**
     * 车型全拼
     */
    private String fullPhonetic;

    /**
     * 车型首拼
     */
    private String firstPhonetic;

    /**
     * 销售名称
     */
    private String marketName;

    /**
     * 排量
     */
    private String carDispatchment;

    /**
     * 汽车VIN码(对应车型）
     */
    private String carVinCode;

    /**
     * 是否是最后层级(1是0否)
     */
    private byte isLast;

    /**
     * 发动机信息
     */
    private String engineInfo;

    /**
     * 直接子集id
     */
    private List<Integer> directChildrenId;
}
