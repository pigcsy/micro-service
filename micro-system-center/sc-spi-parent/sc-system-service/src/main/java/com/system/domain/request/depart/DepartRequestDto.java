/**
 * Project Name:saas-admin-api
 * File Name:LoginRequest.java
 * Package Name:com.saas.admin.domain.request
 * Date:2016年10月19日下午1:40:42
 * Copyright (c) 2016, 蚂上配件 Ltd. All Rights Reserved.
 */
package com.system.domain.request.depart;


import com.core.support.web.domain.BaseRequest;
import lombok.Data;

/**
 * ClassName: AdminStaffRequest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 *
 * @author maven
 * @since JDK 1.8
 */
@Data
public class DepartRequestDto extends BaseRequest {

    private Integer departId;

    private String parent;

    private String name;

    private Byte type;

}
