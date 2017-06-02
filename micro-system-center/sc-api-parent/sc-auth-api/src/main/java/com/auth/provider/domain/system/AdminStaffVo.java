package com.auth.provider.domain.system;

import lombok.Data;

@Data
public class AdminStaffVo {
    private Integer userId;
    private String code;
    private String pwd;
    private Byte status;
    private String userName;
    private String userPhone;
    private Integer systemId;

    private int dailyAccessNum;
    private int minuteAccessNum;

}
