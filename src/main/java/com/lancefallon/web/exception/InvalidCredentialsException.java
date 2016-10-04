package com.lancefallon.web.exception;

public class InvalidCredentialsException extends AbstractException {
    private static final long serialVersionUID = 1L;
    
    private CustomErrorMessage error;
    
    public InvalidCredentialsException(CustomErrorMessage error){
        this.error = error; 
    }
    
    public CustomErrorMessage getError(){
        return this.error;
    }
}
