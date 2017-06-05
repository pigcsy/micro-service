package com.core.exception;


public class GatewayException extends MicroException {

    public GatewayException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param
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
