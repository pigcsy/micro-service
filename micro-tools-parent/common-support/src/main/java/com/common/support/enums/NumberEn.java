package com.common.support.enums;

/**
 * ${DESCRIPTION}
 *
 * @author csy
 * @date 2017/3/31 上午11:03
 */
public enum NumberEn {
    ZERO((byte) 0), ONE((byte) 1), TWO((byte) 2), THREE((byte) 3), FOUR((byte) 4), FIVE((byte) 5), SIX((byte) 6);
    private Byte value;

    NumberEn(Byte value) {
        this.value = value;
    }

    public static NumberEn toEnumByV(byte v) {
        for (NumberEn en : NumberEn.values()) {
            if (en.getValue() == v)
                return en;
        }
        return null;
    }

    public Byte getValue() {
        return value;
    }

    public void setValue(Byte value) {
        this.value = value;
    }
}
