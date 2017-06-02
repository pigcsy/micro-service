package com.support.manager;

import com.cache.support.redis.RedisCacheManager;
import com.cache.support.vo.redis.key.RedisKeyTemplate;
import com.common.support.enums.SystemEn;
import com.core.base.AbstractManager;
import com.core.exception.IllegalAccessException;
import com.core.utils.DateUtils;
import com.support.domain.vo.AccessTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * ${DESCRIPTION}
 *
 * @author csy
 * @date 2017/4/20 下午5:54
 */
@Component
@Slf4j
public class AccessManager extends AbstractManager {
    public final static String DAILY = "1";
    public final static String RATE = "2";
    // private String key;
    // private String type;
    // private AccessTime accessTime;
    @Autowired
    private RedisCacheManager cacheManager;
    private Map<String, Integer> expireMap = new HashMap<>();

    @PostConstruct
    public void init() {
        expireMap.put(DAILY, RedisKeyTemplate.DAILY_ACCESS_NUM.getExpire());
        expireMap.put(RATE, RedisKeyTemplate.MINUTE_ACCESS_NUM.getExpire());
    }

    // public AccessManager buildByCustomId(String customId, String type, SystemEn system) {
    // 	// return new AccessManager(generateKey(customId, type,system), type);
    // 	return null;
    // }
    //
    // public AccessManager buildByIp(String ip, String type, SystemEn system) {
    // 	// return new AccessManager(generateKey(ip, type,system), type);
    // 	return null;
    // }

    // public void setAccessTime(AccessTime accessTime) {
    // 	this.accessTime = accessTime;
    // }

    private String generateKey(String gatewayName, String keyword, String type, SystemEn systemEn) {
        if (DAILY.equalsIgnoreCase(type)) {
            return RedisKeyTemplate.DAILY_ACCESS_NUM.getRedisKey(gatewayName, String.valueOf(systemEn.getCode()), keyword);
        } else {
            return RedisKeyTemplate.MINUTE_ACCESS_NUM.getRedisKey(gatewayName, String.valueOf(systemEn.getCode()), keyword);
        }
    }

    /**
     * 获取日访问量 getAccessTimeNum:(这里用一句话描述这个方法的作用). <br/>
     */
    public long getAccessTimeNum(AccessTime accessTime) {
        return accessTime.getAccessNum();
    }

    /**
     * 增加日访问量 increaseAccessNum:(这里用一句话描述这个方法的作用). <br/>
     */
    public void increaseAccessNum(String gateway, String key, String type) {
        AccessTime accessTime = getAccessTime(gateway, key, type);
        cacheManager.put(key, new AccessTime(accessTime.getGateway(), getAccessTimeNum(accessTime) + 1, new Date()), expireMap.get(type));
    }

    /**
     * 获取访问量 getAccessTime:(这里用一句话描述这个方法的作用). <br/>
     */
    public AccessTime getAccessTime(String gateway, String key, String type) {
        AccessTime accessTime;
        accessTime = cacheManager.get(key, AccessTime.class);
        if (accessTime == null) {
            accessTime = new AccessTime();
            accessTime.setGateway(gateway);
        }
        if (DAILY.equals(type) && !DateUtils.date2String(accessTime.getLastAccessTime(), "yyyy-MM-dd")
                .equals(DateUtils.date2String(new Date(), "yyyy-MM-dd"))) {// 判断跨天
            accessTime.setAccessNum(0);
        }
        return accessTime;
    }

    /**
     * 获取访问量 getAccessTime:(这里用一句话描述这个方法的作用). <br/>
     */
    public void checkAccessLimit(String gateway, String key, String type, long maxNum) {
        AccessTime accessTime = getAccessTime(gateway, key, type);
        // 每分钟访问量
        if (DAILY.equalsIgnoreCase(type)) {
            logger.info("检测日访问量是否超限Key【{}】，日访问量【{}】，上次访问时间【{}】", key, getAccessTimeNum(accessTime),
                    accessTime.getLastAccessTime());
        } else {
            logger.info("检测访问频率是否超限Key【{}】，访问频率【{}】，上次访问时间【{}】", key, getAccessTimeNum(accessTime),
                    accessTime.getLastAccessTime());
        }
        if (getAccessTimeNum(accessTime) > maxNum && maxNum >= 0) {
            if (DAILY.equalsIgnoreCase(type)) {
                throw new IllegalAccessException("访问次数超限");
            } else {
                throw new IllegalAccessException("访问频率超限");
            }
        }
    }

    /**
     * 访问控制
     *
     * @param gateway
     * @param customerId
     * @param systemEn
     * @param minLimitNum   每分钟限制
     * @param dailyLimitNum 每天限制
     */
    public void checkAccessLimit(String gateway, String customerId, SystemEn systemEn, long minLimitNum, long dailyLimitNum) {
        String minKey = generateKey(gateway, customerId, RATE, systemEn);
        checkAccessLimit(gateway, minKey, RATE, minLimitNum);
        String dailyKey = generateKey(gateway, customerId, DAILY, systemEn);
        checkAccessLimit(gateway, dailyKey, DAILY, dailyLimitNum);
    }

    /**
     * 增加访问俩
     *
     * @param gateway
     * @param customerId
     * @param systemEn
     */
    public void increaseAccessAll(String gateway, String customerId, SystemEn systemEn) {
        String minKey = generateKey(gateway, customerId, RATE, systemEn);
        increaseAccessNum(gateway, minKey, RATE);
        String dailyKey = generateKey(gateway, customerId, DAILY, systemEn);
        increaseAccessNum(gateway, dailyKey, DAILY);
    }
}
