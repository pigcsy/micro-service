/**
 * Project Name:mspj-shop-api
 * File Name:IllegalRequestException.java
 * Package Name:com.mspj.shop.api.common.exception
 * Date:2016年5月31日上午10:39:22
 * Copyright (c) 2016, 蚂上配件 Ltd. All Rights Reserved.
 */
package com.core.exception;

/**
 * ClassName: IllegalRequestException <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 *
 * @author 半轴
 * @date: 2016年5月31日 上午10:39:22 <br/>
 * @since JDK 1.8
 */
public class GatewayException extends MicroException {

    public GatewayException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public GatewayException() {
        super(ErrorHolder.getMessage(ErrorHolder.CodeTemp.GATEWAY, ErrorHolder.CodeTemp.GATEWAY.getTemp()));
        this.setErrorCode(ErrorHolder.getCode(ErrorHolder.CodeTemp.GATEWAY));
    }

    /**
     * @param message
     * @param errorCode
     */
    public GatewayException(String message, String errorCode) {
        super(message, errorCode);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param errorCode
     * @param cause
     */
    public GatewayException(String message, String errorCode, Throwable cause) {
        super(message, errorCode, cause);
        // TODO Auto-generated constructor stub
    }
}
