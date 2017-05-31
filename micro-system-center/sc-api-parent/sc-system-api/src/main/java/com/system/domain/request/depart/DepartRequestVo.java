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

import javax.validation.constraints.NotNull;

/**
 * ClassName: AdminStaffRequest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 *
 * @author maven
 * @since JDK 1.8
 */
@Data
public class DepartRequestVo extends BaseRequest {

    @NotNull(message = "部门未取得", groups = {ValiDepartDetail.class, ValiDepartDelete.class})
    private Integer departId;

    private String parent;

    private String name;

    private Byte type;


    /**
     * ClassName: ValiAdminDepartDetail <br/>
     * Function: 验证部门详情. <br/>
     * Reason: TODO ADD REASON(可选). <br/>
     *
     * @author maven
     * @version AdminStaffRequest
     * @date: 2016年10月26日 下午8:16:58 <br/>
     * @since JDK 1.8
     */
    public interface ValiDepartDetail {
    }

    /**
     * ClassName: ValiAdminDepartDelete <br/>
     * Function: 验证部门删除. <br/>
     * Reason: TODO ADD REASON(可选). <br/>
     *
     * @author maven
     * @version AdminStaffRequest
     * @date: 2016年10月26日 下午8:16:58 <br/>
     * @since JDK 1.8
     */
    public interface ValiDepartDelete {
    }

    /**
     * ClassName: ValiAdminDepartEdit <br/>
     * Function: 验证部门编辑. <br/>
     * Reason: TODO ADD REASON(可选). <br/>
     *
     * @author maven
     * @version AdminStaffRequest
     * @date: 2016年10月26日 下午8:16:58 <br/>
     * @since JDK 1.8
     */
    public interface ValiDepartEdit {
    }

}
