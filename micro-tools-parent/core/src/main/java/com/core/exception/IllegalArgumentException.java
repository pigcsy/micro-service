/**
 * Project Name:-shop-api
 * File Name:IllegalParamsException.java
 * Package Name:com..shop.api.common.exception
 * Date:2016年5月31日上午10:38:25
 * Copyright (c) 2016, 蚂上配件 Ltd. All Rights Reserved.
 */
package com.core.exception;

/**
 * ClassName: IllegalParamsException <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 *
 * @author 半轴
 * @date: 2016年5月31日 上午10:38:25 <br/>
 * @since JDK 1.8
 */
public class IllegalArgumentException extends MicroException {

    public IllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public IllegalArgumentException(String message) {
        super(ErrorHolder.getMessage(ErrorHolder.CodeTemp.ILLEGAL_ARGUMENT, message));
        this.setErrorCode(ErrorHolder.getCode(ErrorHolder.CodeTemp.ILLEGAL_ARGUMENT));
    }

    /**
     * @param message
     * @param errorCode
     */
    public IllegalArgumentException(String message, String errorCode) {
        super(message, errorCode);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param errorCode
     * @param cause
     */
    public IllegalArgumentException(String message, String errorCode, Throwable cause) {
        super(message, errorCode, cause);
        // TODO Auto-generated constructor stub
    }
}
