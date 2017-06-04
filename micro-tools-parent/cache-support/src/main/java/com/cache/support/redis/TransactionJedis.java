/**
 * Project Name:core
 * File Name:TransactionJedis.java
 * Package Name:com..framework.core.utils
 * Date:2016年7月20日上午11:51:48
 * Copyright (c) 2016, 蚂上配件 Ltd. All Rights Reserved.
 */
package com.cache.support.redis;

import com.core.exception.LogicException;
import org.springframework.data.redis.core.RedisOperations;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * ClassName: TransactionJedis <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 *
 * @author csy
 * @date: 2016年7月20日 上午11:51:48 <br/>
 * @since JDK 1.8
 */
public abstract class TransactionJedis {

    /**
     * watch:(这里用一句话描述这个方法的作用). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     *
     * @param operations
     * @param keys
     * @author csy
     * @date: 2016年10月17日 下午5:12:11
     */
    protected void watch(RedisOperations<String, Object> operations, List<String> keys) {
        operations.watch(keys);
    }

    /**
     * transaction:(这里用一句话描述这个方法的作用). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     *
     * @param operations
     * @author csy
     * @date: 2016年10月17日 下午5:12:24
     */
    protected abstract void transaction(RedisOperations<String, Object> operations);

    /**
     * 执行事务 exec:(这里用一句话描述这个方法的作用). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     *
     * @param tx
     * @author csy
     * @date: 2016年7月20日 下午12:04:27
     */
    protected void exec(Transaction tx) {
        if (tx.exec().size() < 1) {
            throw new LogicException("执行失败");
        }
    }

    ;

}
