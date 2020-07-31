package com.ck.exceptionhandler;

import java.util.List;

public class RequestValidateException extends RuntimeException {
    private List<String> listError;
    public RequestValidateException(String message, List<String> listError){
       super(message);
       this.listError = listError;
    }

    public List<String> getListError() {
        return listError;
    }

    public void setListError(List<String> listError) {
        this.listError = listError;
    }
}
