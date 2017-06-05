package com.core.exception;


public class UnLoginException extends MicroException {

    public UnLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public UnLoginException(String message) {
        super(ErrorHolder.getMessage(ErrorHolder.CodeTemp.UN_LOGIN, message));
        this.setErrorCode(ErrorHolder.getCode(ErrorHolder.CodeTemp.UN_LOGIN));
    }

    /**
     * @param message
     * @param errorCode
     */
    public UnLoginException(String message, String errorCode) {
        super(message, errorCode);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param errorCode
     * @param cause
     */
    public UnLoginException(String message, String errorCode, Throwable cause) {
        super(message, errorCode, cause);
        // TODO Auto-generated constructor stub
    }
}
