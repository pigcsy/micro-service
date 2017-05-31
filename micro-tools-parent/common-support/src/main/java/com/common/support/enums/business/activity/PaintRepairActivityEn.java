package com.common.support.enums.business.activity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ${DESCRIPTION}
 *
 * @author csy
 * @date 2017/5/8 下午2:01
 */
public class PaintRepairActivityEn {

    @Getter
    @AllArgsConstructor
    public enum StatusEnum {
        ALREADY_SIGN_UP("已报名", (byte)0), SUCCESS("报名成功", (byte)1), FAIL("报名失败", (byte)9)
        ;
        private String name;
        private byte value;

        public static StatusEnum toEnumByV(byte v) {
        		for (StatusEnum en : StatusEnum.values()) {
        			if (en.getValue() == v)
        				return en;
        		}
        		return null;
        	}
    }

}
