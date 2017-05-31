/**
 * Project Name:saas-admin-api
 * File Name:LoginRequest.java
 * Package Name:com.saas.admin.domain.request
 * Date:2016年10月19日下午1:40:42
 * Copyright (c) 2016, 蚂上配件 Ltd. All Rights Reserved.
 */
package com.system.domain.response.resource;


import com.core.support.web.domain.BaseResponse;
import lombok.Data;

import java.util.List;

/**
 * ClassName: EditResourceResponseListVo <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 *
 * @author maven
 * @since JDK 1.8
 */
@Data
public class EditResourceResponseListDto extends BaseResponse implements Comparable<EditResourceResponseListDto> {
    private List<EditResourceResponseListDto> subMenu;
    private Integer resourceId;
    private Integer parent;
    private String name;
    private Byte level;
    private String url;
    private Byte sort;
    private String code;
    private Byte type;
    private String page;
    private Byte status;
    private String system;

    public EditResourceResponseListDto(Integer resourceId, String name, String url, Byte sort, String page) {
        super();
        this.resourceId = resourceId;
        this.name = name;
        this.url = url;
        this.sort = sort;
        this.page = page;
    }


    @Override
    public int compareTo(EditResourceResponseListDto o) {
        if (this.getSort() == null)
            return -1;
        if (o.getSort() == null)
            return 1;
        if (this.getSort() > o.getSort()) {
            return 1;
        } else {
            return -1;
        }
    }

}
