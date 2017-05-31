/**
 * Project Name:mspj-shop-api
 * File Name:BaseRequest.java
 * Package Name:com.mspj.shop.api.common.base.http
 * Date:2016年5月31日下午4:58:49
 * Copyright (c) 2016, 蚂上配件 Ltd. All Rights Reserved.
 */
package com.core.support.web.domain;


import com.core.constants.AppConstant;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: BaseRequest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 *
 * @author 半轴
 * @date: 2016年5月31日 下午4:58:49 <br/>
 * @version
 * @since JDK 1.8
 */
@Data
@NoArgsConstructor
public class BasePageRequest extends BaseRequest {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int currentPage = 0; // 当前页数

    private int pageSize = AppConstant.DEFAULT_PAGE_SIZE; // 每页显示记录的条数

    public BasePageRequest(int currentPage, int pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }
}
