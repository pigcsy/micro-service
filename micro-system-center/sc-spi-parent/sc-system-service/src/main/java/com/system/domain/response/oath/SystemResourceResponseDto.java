package com.system.domain.response.oath;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by maven on 2017/3/13.
 */
@Data
public class SystemResourceResponseDto implements Serializable {
    private Integer oauthSystemResourceId;
    private Integer systemId;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Integer resourceId;

}
