/**
 * Project Name:mspj-shop-api
 * File Name:BaseRequest.java
 * Package Name:com.mspj.shop.api.common.base.http
 * Date:2016年5月31日下午4:58:49
 * Copyright (c) 2016, 蚂上配件 Ltd. All Rights Reserved.
 */
package com.system.domain;

import com.core.support.web.domain.BaseRequest;
import lombok.Data;

/**
 * ClassName: BaseRequest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 *
 * @author csy
 * @date: 2016年5月31日 下午4:58:49 <br/>
 * @since JDK 1.8
 */
@Data
public class DefaultRequestVo extends BaseRequest {

    private Integer entityId;


}
