package com.ck.exceptionhandler;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message){
        super(message);
    }
}
