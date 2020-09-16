package com.aryun.gmall.ke.commons.exception;

import java.io.Serializable;

public class ActivityInfoException extends RuntimeException implements Serializable {

    private String code;

    private String message;

    public ActivityInfoException(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public ActivityInfoException(String message) {
        super();
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
