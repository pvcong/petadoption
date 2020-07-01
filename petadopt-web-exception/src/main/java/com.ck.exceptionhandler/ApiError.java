package com.ck.exceptionhandler;

import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.List;

public class ApiError {
    private Timestamp timestamp;
    private HttpStatus httpStatus;
    private String message;
    private List<String> errors;

    public ApiError() {
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
