package com.wwl.sse.model;

public class SseBizException extends RuntimeException {

    private int code = 500;

    public SseBizException(int code, String message) {
        super(message);
        this.code = code;
    }

    public SseBizException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}