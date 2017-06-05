
package com.core.exception;


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
