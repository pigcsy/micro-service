package com.system.domain.response.resource;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by maven on 2017/3/13.
 */
@Data
public class ResourcesResult implements Serializable {
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

    private String levelName;

    private String statusName;

    private String typeName;
}
