/**
 * Project Name:saas-pc-api
 * File Name:LoginResponse.java
 * Package Name:com.saas.pc.domain.response
 * Date:2016年10月18日下午5:01:12
 * Copyright (c) 2016, 蚂上配件 Ltd. All Rights Reserved.
 */
package com.system.domain.response.menu;

import com.core.support.web.domain.BaseResponse;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * ClassName: LoginResponse <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 *
 * @author csy
 * @date: 2016年10月18日 下午5:01:12 <br/>
 * @since JDK 1.8
 */
@Data
public class MenuResponseVo extends BaseResponse {

    private Integer staffId;

    private String code;

    private String name;

    private String mobile;

    private Date loginTm;

    private String roster;

    // 用户菜单资源列表
    private List<Menu> menus;

    // 用户权限
    private Set<Role> role;

    private String token;

}
