
package com.core.exception;


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
