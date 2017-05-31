package com.system.domain.response.role;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by maven on 2017/3/13.
 */
@Data
public class RoleResourcesResponseVo implements Serializable {
    List<Integer> resourcesList;
    private Integer roleId;
    private Integer systemId;
    private Integer resourceId;

}
