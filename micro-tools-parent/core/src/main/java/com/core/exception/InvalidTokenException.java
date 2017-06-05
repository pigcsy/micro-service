
package com.core.exception;


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
