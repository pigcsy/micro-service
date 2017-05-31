package com.system.domain.request.resourse;

import com.core.support.web.domain.BaseRequest;
import lombok.Data;

/**
 * Created by maven on 2016/11/13.
 */
@Data
public class ResourcesRequestVo extends BaseRequest {

    private static final long serialVersionUID = 8749750100897610883L;

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

    private Integer mode;

}
