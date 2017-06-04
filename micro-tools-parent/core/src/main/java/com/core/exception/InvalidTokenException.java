/**
 * Project Name:-shop-api
 * File Name:InvalidTokenException.java
 * Package Name:com..shop.api.common.exception
 * Date:2016年5月31日上午10:48:59
 * Copyright (c) 2016, 蚂上配件 Ltd. All Rights Reserved.
 */
package com.core.exception;

/**
 * ClassName: InvalidTokenException <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 *
 * @author 半轴
 * @date: 2016年5月31日 上午10:48:59 <br/>
 * @since JDK 1.8
 */
public class InvalidTokenException extends MicroException {

    public InvalidTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public InvalidTokenException(String message) {
        super(ErrorHolder.getMessage(ErrorHolder.CodeTemp.INVALID_TOKEN, message));
        this.setErrorCode(ErrorHolder.getCode(ErrorHolder.CodeTemp.INVALID_TOKEN));
    }

    /**
     * @param message
     * @param errorCode
     */
    public InvalidTokenException(String message, String errorCode) {
        super(message, errorCode);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param errorCode
     * @param cause
     */
    public InvalidTokenException(String message, String errorCode, Throwable cause) {
        super(message, errorCode, cause);
        // TODO Auto-generated constructor stub
    }
}
