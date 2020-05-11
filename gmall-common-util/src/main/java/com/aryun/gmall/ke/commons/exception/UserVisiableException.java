package com.aryun.gmall.ke.commons.exception;

import java.text.MessageFormat;

public class UserVisiableException extends RuntimeException {
    private static final long serialVersionUID = 7984566227527019038L;
    private Integer errorCode = Integer.valueOf(500);

    public UserVisiableException() {
    }

    public UserVisiableException(Integer errorCode, String exMessage) {
        super(exMessage);
        this.errorCode = errorCode;
    }

    public UserVisiableException(Integer errorCode, String exMessage, Object... args) {
        super(MessageFormat.format(exMessage, args));
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return this.errorCode;
    }
}
