/**
 * Project Name:saas-admin-api
 * File Name:LoginRequest.java
 * Package Name:com.saas.admin.domain.request
 * Date:2016年10月19日下午1:40:42
 * Copyright (c) 2016, 蚂上配件 Ltd. All Rights Reserved.
 */
package com.system.domain.request.resourse;

import com.core.support.web.domain.BasePageRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: CompanyRepairRequest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 *
 * @author maven
 * @date: 2016年10月20日 上午9:35:02 <br/>
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourcesListRequestVo extends BasePageRequest {


    private static final long serialVersionUID = 9087235961215856588L;
    private Integer resourceId;

    private Integer parent;

    private String name;

    private Byte level;

    private String url;

    private Byte sort;

    private String code;

    private Byte type;

    private String page;

    private Byte status;

    private String system;

    private String startTime;

    private String endTime;

    private String keyword;


    public ResourcesListRequestVo(int currentPage, int pageSize) {
        super(currentPage, pageSize);
    }
}
