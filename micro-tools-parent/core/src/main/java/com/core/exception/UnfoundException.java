package com.core.exception;


public class UnfoundException extends MicroException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public UnfoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public UnfoundException(String message) {
        super(ErrorHolder.getMessage(ErrorHolder.CodeTemp.UNFOUND, message));
        // TODO Auto-generated constructor stub
        this.setErrorCode(ErrorHolder.getCode(ErrorHolder.CodeTemp.UNFOUND));
    }

    /**
     * @param message
     * @param errorCode
     */
    public UnfoundException(String message, String errorCode) {
        super(message, errorCode);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param errorCode
     * @param cause
     */
    public UnfoundException(String message, String errorCode, Throwable cause) {
        super(message, errorCode, cause);
        // TODO Auto-generated constructor stub
    }

}
