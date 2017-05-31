package com.system.domain.response.resource;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by maven on 2017/3/13.
 */
@Data
public class ResourcesResponseDto implements Serializable {
    private Integer resourceId;
    private Integer staffId;
    private Integer parent;
    private String name;
    private byte level;
    private String url;
    private Byte sort;
    private String code;
    private Byte type;
    private String page;
    private Byte status;
    private String system;
    private Byte sourceType;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String levelName;

    private String statusName;

    private String typeName;
}
