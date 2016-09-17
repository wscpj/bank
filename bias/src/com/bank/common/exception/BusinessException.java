package com.bank.common.exception;


public class BusinessException extends Exception {
    private static final long serialVersionUID = 6207936304809473506L;
    private int code;
    private String errorMessage;

    public BusinessException() {
        super();
    }

    public BusinessException(int code, String errorMessage) {
        super();
        this.code = code;
        this.errorMessage = errorMessage;
    }
    /**
     * @param message
     */
    public BusinessException(String message) {
        super(message);
        this.errorMessage = message;
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
