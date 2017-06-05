package com.core.exception;


public class MicroAuthorizationException extends MicroException {

    public MicroAuthorizationException(String message, Throwable cause) {
        super(message, cause);
        this.setErrorCode(ErrorHolder.getCode(ErrorHolder.CodeTemp.INVALID_AUTHENTICATION));
    }

    /**
     * @param
     */
    public MicroAuthorizationException() {
        super(ErrorHolder.getMessage(ErrorHolder.CodeTemp.INVALID_AUTHENTICATION, ErrorHolder.CodeTemp.INVALID_AUTHENTICATION.getTemp()));
        this.setErrorCode(ErrorHolder.getCode(ErrorHolder.CodeTemp.INVALID_AUTHENTICATION));
    }

    /**
     * @param message
     * @param errorCode
     */
    public MicroAuthorizationException(String message) {
        super(message, ErrorHolder.CodeTemp.INVALID_AUTHENTICATION.getCode());
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param errorCode
     * @param cause
     */
    public MicroAuthorizationException(String message, String errorCode) {
        super(message, errorCode);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param errorCode
     * @param cause
     */
    public MicroAuthorizationException(String message, String errorCode, Throwable cause) {
        super(message, errorCode, cause);
        // TODO Auto-generated constructor stub
    }
}
