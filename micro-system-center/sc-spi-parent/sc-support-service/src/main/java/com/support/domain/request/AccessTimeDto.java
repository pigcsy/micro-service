/**
 * Project Name:mspj-shop-api
 * File Name:Token.java
 * Package Name:com.mspj.shop.api.authentication
 * Date:2016年6月1日下午1:31:30
 * Copyright (c) 2016, 蚂上配件 Ltd. All Rights Reserved.
 */
package com.support.domain.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author csy
 * @since JDK 1.8
 */
@Getter
@Setter
public class AccessTimeDto implements Serializable {

    private String gateway;
    private long accessNum;
    private Date lastAccessTime;

    public AccessTimeDto() {
        accessNum = 0;
        lastAccessTime = new Date();
    }

    public AccessTimeDto(String gateway, long accessNum, Date lastAccessTime) {
        this.gateway = gateway;
        this.accessNum = accessNum;
        this.lastAccessTime = lastAccessTime;
    }
}
