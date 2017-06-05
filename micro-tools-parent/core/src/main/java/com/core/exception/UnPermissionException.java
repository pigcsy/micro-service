
package com.core.exception;


public class UnPermissionException extends MicroException {

    public UnPermissionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public UnPermissionException(String message) {
        super(ErrorHolder.getMessage(ErrorHolder.CodeTemp.NO_PERMISSION, message));
        this.setErrorCode(ErrorHolder.getCode(ErrorHolder.CodeTemp.NO_PERMISSION));
    }

    /**
     * @param message
     * @param errorCode
     */
    public UnPermissionException(String message, String errorCode) {
        super(message, errorCode);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param errorCode
     * @param cause
     */
    public UnPermissionException(String message, String errorCode, Throwable cause) {
        super(message, errorCode, cause);
        // TODO Auto-generated constructor stub
    }
}
