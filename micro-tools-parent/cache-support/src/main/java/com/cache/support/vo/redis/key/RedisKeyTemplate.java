package com.cache.support.vo.redis.key;

import com.core.exception.LogicException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * redis中key, field, expire模板
 *
 * @author csy
 * @date 2017/3/13 上午11:00
 */
public class RedisKeyTemplate {

    /**
     * 验证码在redis中的key和过期时间 <br/>
     * YXT_SMS_CODE_{}_TYPE_{} => 调用 getRedisKey 将得到类似于 {{YXT_SMS_CODE_15658896952_TYPE_1}} 的key [redis中的key]
     * 900 => 设置过期时间为900秒
     * <p>
     * {@link RedisKeyConstBuilder}
     */
    public static final RedisKeyConstBuilder DEMO_SMS_CODE = RedisKeyConstBuilder.create("YDEMO_SMS_CODE_{}_TYPE_{}", 900);

    /**
     * hash中存储 <br/>
     * DEMO_CAR_BRAND => 为hash中的key
     * {} => 为hash中的field
     * 车辆信息[哈希]
     */
    public static final RedisKeyConstBuilder DEMO_CAR_BRAND = RedisKeyConstBuilder.create("DEMO_CAR_BRAND", "{}");

    /**
     * 权限资源数据
     */
    public static final RedisKeyConstBuilder AUTHORIZATION_METADATA_SOURCE = RedisKeyConstBuilder.create("AUTHORIZATION_METADATA_SOURCE", -1);

    /**
     * 邮件发送失败信息在redis中的队列
     */
    // public static final RedisKeyConstBuilder MAIL_SEND_FAILED_LIST_KEY = RedisKeyConstBuilder.create("MAIL_SEND_FAILED_LIST_KEY", -1);
    public static final RedisKeyConstBuilder MAIL_SEND_FAILED_LIST_KEY = RedisKeyConstBuilder.create("MAIL_SEND_FAILED_LIST_KEY", -1);

    /**
     * 手机短消息发送失败信息在redis中的队列
     */
    public static final RedisKeyConstBuilder SMS_SEND_FAILED_LIST_KEY = RedisKeyConstBuilder.create("SMS_SEND_FAILED_LIST_KEY", -1);

    /**
     * VIN码在redis中的队列
     */
    // public static final RedisKeyConstBuilder YXT_VIN_CAR_RELATION = RedisKeyConstBuilder.create("YXT_VIN_CAR_RELATION", -1);

    /**
     * 配件信息hash表
     */
    public static final RedisKeyConstBuilder PART_INFO_HASH = RedisKeyConstBuilder.create("PART_INFO_HASH", "{}");

    /**
     * 车辆信息hash表
     */
    public static final RedisKeyConstBuilder CAR_INFO_HASH = RedisKeyConstBuilder.create("CAR_INFO_HASH", "{}");

    /**
     * vin-car 关联关系
     */
    public static final RedisKeyConstBuilder VIN_CAR_RELATION = RedisKeyConstBuilder.create("VIN_CAR_RELATION", "{}");
    /**
     * 省市区信息hash表
     */
    public static final RedisKeyConstBuilder REGION_INFO_HASH = RedisKeyConstBuilder.create("REGION_INFO_HASH", "{}");

    /**
     * 云推送
     */
    public static final RedisKeyConstBuilder BAIDU_YUN_PUSH_LIST = RedisKeyConstBuilder.create("BAIDU_YUN_PUSH_LIST", -1);

    /**
     * 微服务-每日访问频率
     */
    public static final RedisKeyConstBuilder DAILY_ACCESS_NUM = RedisKeyConstBuilder.create("{}_MICRO_DAILY_ACCESS_NUM_{}_{}", 60 * 60 * 24);

    /**
     * 微服务-每分钟访问频率
     */
    public static final RedisKeyConstBuilder MINUTE_ACCESS_NUM = RedisKeyConstBuilder.create("{}_MICRO_MINUTE_ACCESS_NUM_{}_{}", 60);
    /**
     *
     */
    public static class RedisKeyConstBuilder implements Serializable {
        private static final long serialVersionUID = -788058748951890721L;
        /**
         * 缓存中的key
         */
        private String key;

        /**
         * hashmap中key的field
         */
        private String field;

        /**
         * 缓存中key对应的过期时间
         */
        private int expire;

        public RedisKeyConstBuilder() {
        }

        public RedisKeyConstBuilder(String key, int expire) {
            this.key = key;
            this.expire = expire;
        }

        public RedisKeyConstBuilder(String key, String field) {
            this.key = key;
            this.field = field;
        }

        public static RedisKeyConstBuilder create() {
            return new RedisKeyConstBuilder();
        }

        public static RedisKeyConstBuilder create(String key, int expire) {
            return new RedisKeyConstBuilder(key, expire);
        }

        public static RedisKeyConstBuilder create(String key, String field) {
            return new RedisKeyConstBuilder(key, field);
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public int getExpire() {
            return expire;
        }

        public void setExpire(int expire) {
            this.expire = expire;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getRedisKey(String... replaceStr) {
            return replaceConstStr(getKey(), replaceStr);
        }

        public String getRedisField(String... replaceStr) {
            return replaceConstStr(getField(), replaceStr);
        }

        public String getFuzzyRedisKey(String... replaceStr) {
            String result = getKey();
            int index = 0;
            while (result.indexOf("{}") >= 0) {
                if (index >= replaceStr.length) {
                    result = StringUtils.replaceOnce(result, "{}", "*");
                    index++;
                    continue;
                }
                result = StringUtils.replaceOnce(result, "{}", replaceStr[index]);
                index++;
            }
            return result;
        }

        private String replaceConstStr(String string, String... replaceStr) {
            if (string == null)
                return null;
            String result = string;
            int index = 0;
            while (result.indexOf("{}") >= 0) {
                if (ArrayUtils.isEmpty(replaceStr) || replaceStr.length < index) {
                    throw new LogicException("缓存参数错误", "-1");
                }
                result = StringUtils.replaceOnce(result, "{}", replaceStr[index]);
                index++;
            }
            return result;
        }
    }
}
