/**
 * Project Name:-shop-api
 * File Name:BaseResponse.java
 * Package Name:com..shop.api.common.base.http
 * Date:2016年5月31日下午4:58:55
 * Copyright (c) 2016, 蚂上配件 Ltd. All Rights Reserved.
 */
package com.core.support.web.domain;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * ClassName: BaseResponse <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 *
 * @author 半轴
 * @date: 2016年5月31日 下午4:58:55 <br/>
 * @since JDK 1.8
 */
public class BaseResponse implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
