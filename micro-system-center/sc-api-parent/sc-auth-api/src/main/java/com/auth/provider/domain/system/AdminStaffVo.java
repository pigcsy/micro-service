package com.auth.provider.domain.system;

import lombok.Data;

@Data
public class AdminStaffVo {
    private Integer staffId;
    private String code;
    private String pwd;
    private Byte status;
    private String staffName;
    private String staffPhone;
    private Integer systemId;

    private int dailyAccessNum;
    private int minuteAccessNum;

}
