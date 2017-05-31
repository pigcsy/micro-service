package redis;

import com.cache.support.redis.RedisCacheManager;
import com.cache.support.vo.redis.key.RedisKeyTemplate;
import com.cache.support.vo.redis.value.demo.DemoCarBrandVo;
import com.cache.support.vo.redis.value.demo.DemoSmsCodeVo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ${DESCRIPTION}
 *
 * @author csy
 * @date 2017/3/13 上午11:27
 */
public class TestRedis {
    @Autowired
    private RedisCacheManager redisCacheManager;

    /**
     * 存储普通的key-value值
     */
    public void testRedis() {
        DemoSmsCodeVo smsCodeVo = new DemoSmsCodeVo("156588086564", "1");
        String key = RedisKeyTemplate.DEMO_SMS_CODE.getRedisKey("156588086564", "1");
        redisCacheManager.put(key, smsCodeVo, 500);
    }

    /**
     * 存储hash
     */
    public void testRedisHash() {
        DemoCarBrandVo brandVo = new DemoCarBrandVo(1, "大众", "2016 1.6L 自动风尚版");
        String key = RedisKeyTemplate.DEMO_CAR_BRAND.getKey();
        String field = RedisKeyTemplate.DEMO_CAR_BRAND.getRedisField(brandVo.getId().toString());
        redisCacheManager.putHash(key, field, brandVo);
    }
}
