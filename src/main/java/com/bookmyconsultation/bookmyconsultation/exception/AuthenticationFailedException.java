package com.bookmyconsultation.bookmyconsultation.exception;

public class AuthenticationFailedException extends ApplicationException {

    private static final long serialVersionUID = 7660768556081121813L;

    public AuthenticationFailedException(ErrorCode errorCode, Object... parameters) {
        super(errorCode, parameters);
    }

}