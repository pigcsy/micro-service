package com.system.domain.request.user;

import com.core.support.web.domain.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * ClassName: AdminStaffRequest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 *
 * @author maven
 * @date: 2016年10月26日 下午8:14:22 <br/>
 * @since JDK 1.8
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserRequestDto extends BaseRequest {

    private static final long serialVersionUID = -9182181736766153067L;
    private Integer staffId;
    private String code;
    private String pwd;
    private String staffRoster;
    private String staffName;
    private Integer departId;
    private String staffPost;
    private String staffPhone;
    private String staffEmail;
    private Date insTm;
    private Byte leader;
    private Byte status;
    private String createTime;
    private String updateTime;
    private Integer systemId;
    private String roleIdList;


}
