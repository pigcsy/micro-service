package com.system.domain.response.role;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ClassName: AdminDepartResult <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 *
 * @author "kaiguan"
 * @date: 2016年10月27日 下午4:15:31 <br/>
 * @since JDK 1.8
 */
@Data
public class RoleResult implements Serializable {


    private static final long serialVersionUID = 8519894623363072092L;

    private String roleName;
    private String roleRemark;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Integer roleId;


}