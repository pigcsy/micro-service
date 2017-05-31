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
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * ClassName: AdminStaffRequest <br/>
 *
 * @author maven
 * @date: 2016年10月26日 下午8:14:22 <br/>
 * @since JDK 1.8
 */
@Data
public class RoleRequestVo extends BaseRequest {

    @NotNull(message = "角色id未取得", groups = {ValiRoleResourceEdit.class, ValiRoleDelete.class, ValiRoleResourceList.class})
    private Integer roleId;

    @NotBlank(message = "角色名称不能为空", groups = {ValiAdminRoleEdit.class})
    private String roleName;

    private String roleRemark;

    @NotEmpty(message = "角色权限未取得", groups = {ValiRoleResourceEdit.class, ValiAdminResourceEdit.class})
    private String resourceIds;

    private Timestamp createTime;

    private Timestamp updateTime;


    /**
     * ClassName: ValiAdminRoleEdit <br/>
     * Function: 验证角色编辑. <br/>
     * Reason: TODO ADD REASON(可选). <br/>
     *
     * @author "kaiguan"
     * @version AdminRoleRequest
     * @date: 2016年10月28日 上午10:32:34 <br/>
     * @since JDK 1.8
     */
    public interface ValiAdminRoleEdit {
    }

    /**
     * ClassName: ValiAdminRoleResourceEdit <br/>
     * Function: 验证角色权限编辑. <br/>
     * Reason: TODO ADD REASON(可选). <br/>
     *
     * @author "kaiguan"
     * @version AdminRoleRequest
     * @date: 2016年10月28日 上午11:23:24 <br/>
     * @since JDK 1.8
     */
    public interface ValiRoleResourceEdit {
    }

    public interface ValiAdminResourceEdit {
    }

    /**
     * ClassName: ValiAdminRoleResourceList <br/>
     * Function: 验证角色资源查询. <br/>
     * Reason: TODO ADD REASON(可选). <br/>
     *
     * @author "kaiguan"
     * @version AdminRoleRequest
     * @date: 2016年10月28日 上午11:23:24 <br/>
     * @since JDK 1.8
     */
    public interface ValiRoleResourceList {
    }

    /**
     * ClassName: ValiAdminRoleDelete <br/>
     * Function: 验证角色权限删除. <br/>
     * Reason: TODO ADD REASON(可选). <br/>
     *
     * @author "kaiguan"
     * @version AdminRoleRequest
     * @date: 2016年10月28日 上午11:23:24 <br/>
     * @since JDK 1.8
     */
    public interface ValiRoleDelete {
    }

}
