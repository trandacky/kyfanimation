package com.dacky.controller;

public class BaseResponse {

    private int status = 999;
    private String message;
    private Object response;

    public BaseResponse(int status, String message, Object response) {
        this.status = status;
        this.message = message;
        this.response = response;
    }

    public BaseResponse() {}

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}
