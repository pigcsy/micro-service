/**
 * Project Name:saas-authentication
 * File Name:Role.java
 * Package Name:com.saas.authentication.domain
 * Date:2016年10月20日下午1:13:21
 * Copyright (c) 2016, 蚂上配件 Ltd. All Rights Reserved.
 */
package com.system.domain.response.menu;

import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: Role <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 *
 * @author csy
 * @date: 2016年10月20日 下午1:13:21 <br/>
 * @version
 * @since JDK 1.8
 */
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = 642538304346363259L;
    private String code;
    private String name;
    private String pattern;


}
