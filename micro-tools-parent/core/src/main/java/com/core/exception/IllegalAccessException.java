
package com.core.exception;


public class IllegalAccessException extends MicroException {

    public IllegalAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public IllegalAccessException(String message) {
        super(ErrorHolder.getMessage(ErrorHolder.CodeTemp.ILLEGAL_ACCESS, message));
        this.setErrorCode(ErrorHolder.getCode(ErrorHolder.CodeTemp.ILLEGAL_ACCESS));
    }

    /**
     * @param message
     * @param errorCode
     */
    public IllegalAccessException(String message, String errorCode) {
        super(message, errorCode);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param errorCode
     * @param cause
     */
    public IllegalAccessException(String message, String errorCode, Throwable cause) {
        super(message, errorCode, cause);
        // TODO Auto-generated constructor stub
    }
}
