package com.springboot.blog.Exceptions;

public class UserExistanceException extends RuntimeException {
    private String message;
    public UserExistanceException(String message){
        super(message);
        this.message=message;
    }
    public String getMessage(){
        return this.message;
    }
}
