package com.ck.exceptionhandler;

public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException(String fileName){ super("File not found " +    fileName);}
}
