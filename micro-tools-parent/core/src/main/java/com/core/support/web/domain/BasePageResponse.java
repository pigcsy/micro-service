/**
 * Project Name:mspj-shop-api
 * File Name:BaseRequest.java
 * Package Name:com.mspj.shop.api.common.base.http
 * Date:2016年5月31日下午4:58:49
 * Copyright (c) 2016, 蚂上配件 Ltd. All Rights Reserved.
 */
package com.core.support.web.domain;

import lombok.Data;

import java.util.List;

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
public class BasePageResponse<T> extends BaseResponse {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private boolean nextPage;
    private List<T> list;
    private long total;
    private long totalPage;
    private long currentPage;

    public BasePageResponse() {
        super();
    }

    public BasePageResponse(boolean nextPage, long total, long totalPage, long currentPage) {
        super();
        this.nextPage = nextPage;
        this.total = total;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
    }

    public BasePageResponse(boolean nextPage, List<T> list, long total, long totalPage, long currentPage) {
        super();
        this.nextPage = nextPage;
        this.list = list;
        this.total = total;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
    }

    public boolean isNextPage() {
        return this.nextPage;
    }

    public void setNextPage(boolean nextPage) {
        this.nextPage = nextPage;
    }

}
