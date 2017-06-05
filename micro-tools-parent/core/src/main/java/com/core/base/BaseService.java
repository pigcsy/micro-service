package com.core.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * ClassName: BaseService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 *
 * @author
 * @date: 2016年10月17日 下午4:14:01 <br/>
 * @since JDK 1.8
 */
@Transactional
public abstract class BaseService {
    public Logger logger = LoggerFactory.getLogger(this.getClass());
}
