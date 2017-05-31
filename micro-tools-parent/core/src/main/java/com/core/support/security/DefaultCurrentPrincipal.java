package com.core.support.security;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;

@Data
public class DefaultCurrentPrincipal implements Serializable {
    private Integer uid;
    private Integer systemId;
    private String name = "未知";
    private Map<String,String> additionalInfo;
    private Integer systemType;
    private int dailyAccessNum;
    private int minuteAccessNum;

    public DefaultCurrentPrincipal(Integer uid, String name, Integer systemId, Integer systemType) {
        super();
        this.systemId = systemId;
        this.uid = uid;
        this.name = name;
        this.systemType = systemType;
    }

    public DefaultCurrentPrincipal(Integer uid, String name, Integer systemId, Integer systemType, int dailyAccessNum, int minuteAccessNum) {
        super();
        this.systemId = systemId;
        this.uid = uid;
        this.name = name;
        this.systemType = systemType;
        this.dailyAccessNum = dailyAccessNum;
        this.minuteAccessNum = minuteAccessNum;
    }

    public DefaultCurrentPrincipal() {
        super();
    }

	@Override
	public String toString() {
		return "DefaultCurrentPrincipal [uid=" + uid + ", systemId=" + systemId + ", name=" + name + ", additionalInfo="
				+ additionalInfo + ", systemType=" + systemType + ", dailyAccessNum=" + dailyAccessNum
				+ ", minuteAccessNum=" + minuteAccessNum + "]";
	}

   

}
