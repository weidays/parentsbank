package com.weidays.parentsbank.server.common.exception;
public class FileMissException extends RuntimeException {  
    private static final long serialVersionUID = 1L;  
      
    public FileMissException(String errorMessage) {  
        super(errorMessage);  
    }  
}  