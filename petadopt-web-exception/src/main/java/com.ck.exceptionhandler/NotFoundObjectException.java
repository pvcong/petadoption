package com.ck.exceptionhandler;

public class NotFoundObjectException extends RuntimeException {
    public NotFoundObjectException(){ super("NOT FOUND OBJECT ON DATABASE");};
}
