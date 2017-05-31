package com.core.exception;

import com.core.exception.ErrorHolder.CodeTemp;

import java.util.ArrayList;
import java.util.List;

public class MicroException extends RuntimeException {

    private static final long serialVersionUID = -360277845666981697L;

    private String errorCode = CodeTemp.UNKNOW.getCode();

    private List<MicroException> relationExceptions;


    public MicroException(String message, Throwable cause) {
        super(message, cause);
    }

    public MicroException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public MicroException(String message) {
        super(message);
    }

    public MicroException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void addExceptions(MicroException exception) {
        relationExceptions = relationExceptions == null ? new ArrayList<>() : relationExceptions;
        relationExceptions.add(exception);
    }

}
