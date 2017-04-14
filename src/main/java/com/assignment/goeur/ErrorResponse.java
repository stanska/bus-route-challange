package com.assignment.goeur;

public class ErrorResponse {
    public ErrorResponse(String message, Integer status) {
        this.message = message;
        this.status = status;
    }

    private String message;
    private Integer status;

    public String getMessage() {
        return  message;
    }

    public Integer getStatus() {
        return status;
    }
}
