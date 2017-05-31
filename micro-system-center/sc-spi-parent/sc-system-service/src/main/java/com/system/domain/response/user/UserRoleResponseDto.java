package com.system.domain.response.user;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by maven on 2017/3/13.
 */
@Data
public class UserRoleResponseDto implements Serializable {
    private Integer roleId;
    private Integer staffId;

}
