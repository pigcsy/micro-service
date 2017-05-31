/**
 * Project Name:saas-admin-api
 * File Name:LoginRequest.java
 * Package Name:com.saas.admin.domain.request
 * Date:2016年10月19日下午1:40:42
 * Copyright (c) 2016, 蚂上配件 Ltd. All Rights Reserved.
 */
package com.system.domain.request.role;

import com.core.support.web.domain.BasePageRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: AdminDepartRpcRequest <br/>
 *
 * @author maven
 * @date: 2016年10月27日 下午5:58:41 <br/>
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleListRequestVo extends BasePageRequest {

    private Integer roleId;

    private String roleName;

    private String roleRemark;

    private String resourceIdList;

    public RoleListRequestVo(int currentPage, int pageSize) {
        super(currentPage, pageSize);
    }

}
