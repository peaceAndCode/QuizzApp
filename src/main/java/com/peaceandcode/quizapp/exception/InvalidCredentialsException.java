package com.peaceandcode.quizapp.exception;

import java.io.Serial;

public class InvalidCredentialsException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    public InvalidCredentialsException(final String message) {
        super(message);
    }
}
