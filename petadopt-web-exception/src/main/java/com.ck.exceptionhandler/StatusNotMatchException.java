package com.ck.exceptionhandler;

public class StatusNotMatchException extends RuntimeException {
    public StatusNotMatchException(String statusUpdate, String statusCurrent){
        super("Status update is ID = " +statusUpdate + "  not match to status current ID = " + statusCurrent + "");
    }
}
