package com.wwl.sse.model;

public class ApiResponse<T> {
    private int code;
    private T data;
    private String message;

    public ApiResponse() {
    }

    public ApiResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public ApiResponse<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public T getData() {
        return data;
    }

    public ApiResponse<T> setData(T data) {
        this.data = data;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ApiResponse<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public static <T> ApiResponse<T> setSuccessResult(T obj) {
        ApiResponse<T> result = new ApiResponse<>();
        result.setCode(0);
        result.setData(obj);
        result.setMessage("");
        return result;
    }

    public static <T> ApiResponse<T> setErrorResult(int code, String message) {
        ApiResponse<T> result = new ApiResponse<T>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}

