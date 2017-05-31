/**
 * Project Name:saas-admin-api
 * File Name:LoginRequest.java
 * Package Name:com.saas.admin.domain.request
 * Date:2016年10月19日下午1:40:42
 * Copyright (c) 2016, 蚂上配件 Ltd. All Rights Reserved.
 */
package com.system.domain.request.user;

import com.core.support.web.domain.BaseRequest;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * ClassName: AdminStaffRequest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 *
 * @author maven
 * @since JDK 1.8
 */
@Data
public class UserRequestVo extends BaseRequest {

    private static final long serialVersionUID = -9182181736766153067L;
    @NotNull(message = "未取得员工信息", groups = {AdminStaffDetail.class, AdminStaffDisable.class, AdminStaffRoleList.class, AdminStaffRoleEdit.class})
    private Integer staffId;
    @NotBlank(message = "工号不能为空", groups = {AdminStaffEdit.class})
    private String code;
    @NotBlank(message = "密码不能为空", groups = {})
    private String pwd;
    @NotBlank(message = "花名不能为空", groups = {AdminStaffEdit.class})
    private String staffRoster;
    @NotBlank(message = "姓名不能为空", groups = {AdminStaffEdit.class})
    private String staffName;
    @NotNull(message = "部门不能为空", groups = {AdminStaffEdit.class})
    private Integer departId;
    @NotBlank(message = "职务不能为空", groups = {AdminStaffEdit.class})
    private String staffPost;
    @NotBlank(message = "电话不能为空", groups = {AdminStaffEdit.class})
    private String staffPhone;
    private String staffEmail;
    private Date insTm;
    private Integer systemId;
    private Byte leader;
    private Byte status;
    private String createTime;
    private String updateTime;
    @NotEmpty(message = "员工权限id", groups = {AdminStaffRoleEdit.class})
    private String roleIdList;


    public interface AdminStaffEdit {
    }

    /**
     * Function: 验证员工权限. <br/>
     */
    public interface AdminStaffRoleEdit {
    }

    /**
     * Function: 验证员工详情. <br/>
     */
    public interface AdminStaffDetail {
    }

    /**
     * Function: 验证员工离职. <br/>
     */
    public interface AdminStaffDisable {
    }

    /**
     * Function: 验证员工权限列表. <br/>
     */
    public interface AdminStaffRoleList {
    }

}
