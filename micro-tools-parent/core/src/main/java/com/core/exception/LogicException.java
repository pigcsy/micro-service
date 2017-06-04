/**
 * Project Name:-shop-api
 * File Name:LogicException.java
 * Package Name:com..shop.api.common.exception
 * Date:2016年5月31日上午10:38:43
 * Copyright (c) 2016, 蚂上配件 Ltd. All Rights Reserved.
 */
package com.core.exception;

/**
 * ClassName: LogicException <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 *
 * @author 半轴
 * @date: 2016年5月31日 上午10:38:43 <br/>
 * @since JDK 1.8
 */
public class LogicException extends MicroException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public LogicException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public LogicException(String message) {
        super(ErrorHolder.getMessage(ErrorHolder.CodeTemp.LOGIC_ERR, message));
        // TODO Auto-generated constructor stub
        this.setErrorCode(ErrorHolder.getCode(ErrorHolder.CodeTemp.LOGIC_ERR));
    }

    /**
     * @param message
     * @param errorCode
     */
    public LogicException(String message, String errorCode) {
        super(message, errorCode);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param errorCode
     * @param cause
     */
    public LogicException(String message, String errorCode, Throwable cause) {
        super(message, errorCode, cause);
        // TODO Auto-generated constructor stub
    }

}
