package com.bookmyconsultation.bookmyconsultation.exception;

public class AuthorizationFailedException extends ApplicationException {

    private static final long serialVersionUID = 6409417559920703198L;

    public AuthorizationFailedException(ErrorCode errorCode, Object... parameters) {
        super(errorCode, parameters);
    }

}
