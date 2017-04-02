package com.exchange.exceptions;

/**
 * Created by greenlucky on 4/2/17.
 */
public class UnAuthorizeException extends RuntimeException{

    private String message;

    public UnAuthorizeException(String s) {
        super(s);
        message = s;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
