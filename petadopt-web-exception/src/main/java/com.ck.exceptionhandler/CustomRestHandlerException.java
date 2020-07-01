package com.ck.exceptionhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomRestHandlerException extends ResponseEntityExceptionHandler {
    @ExceptionHandler( NotFoundObjectException.class)
    public ResponseEntity<Object> handlerNotFound(NotFoundObjectException e){
        ApiError apiError = new ApiError();
        apiError.setMessage(e.getMessage());
        List<String> errors  = new ArrayList<String>();
        errors.add("404");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        apiError.setErrors(errors);
        apiError.setTimestamp(timestamp);
        apiError.setHttpStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Object>(apiError,new HttpHeaders(),apiError.getHttpStatus());
    }
    @ExceptionHandler( UploadFileException.class)
    public ResponseEntity<Object> handlerUploadFile(UploadFileException e){
        ApiError apiError = new ApiError();
        apiError.setMessage(e.getMessage());
        List<String> errors  = new ArrayList<String>();
        errors.add("500");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        apiError.setTimestamp(timestamp);
        apiError.setErrors(errors);
        apiError.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<Object>(apiError,new HttpHeaders(),apiError.getHttpStatus());
    }
    @ExceptionHandler( FileNotFoundException.class)
    public ResponseEntity<Object> handlerUploadFile(FileNotFoundException e){
        ApiError apiError = new ApiError();
        apiError.setMessage(e.getMessage());
        List<String> errors  = new ArrayList<String>();
        errors.add("404");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        apiError.setTimestamp(timestamp);
        apiError.setErrors(errors);
        apiError.setHttpStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Object>(apiError,new HttpHeaders(),apiError.getHttpStatus());
    }

}
