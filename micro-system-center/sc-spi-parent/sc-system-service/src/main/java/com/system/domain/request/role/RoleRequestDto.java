/**
 * Project Name:saas-admin-api
 * File Name:LoginRequest.java
 * Package Name:com.saas.admin.domain.request
 * Date:2016年10月19日下午1:40:42
 * Copyright (c) 2016, 蚂上配件 Ltd. All Rights Reserved.
 */
package com.system.domain.request.role;

import com.core.support.web.domain.BaseRequest;
import lombok.Data;

import java.sql.Timestamp;

/**
 * ClassName: AdminStaffRequest <br/>
 *
 * @author maven
 * @date: 2016年10月26日 下午8:14:22 <br/>
 * @since JDK 1.8
 */
@Data
public class RoleRequestDto extends BaseRequest {

    private String roleName;
    private String roleRemark;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Integer roleId;

    private String resourceIds;


}
