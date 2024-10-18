package com.peaceandcode.quizapp.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.Timestamp;

@ControllerAdvice
public class GlobalExceptionHandler {
    public static ResponseEntity<CustomExceptionObject> handleException(HttpStatus status, Exception ex) {
        CustomExceptionObject exceptionObject = new CustomExceptionObject();

        exceptionObject.setMessage(ex.getMessage());
        exceptionObject.setTimestamp(new Timestamp(System.currentTimeMillis()));
        exceptionObject.setExceptionName(ex.getClass().getSimpleName());

        return new ResponseEntity<>(exceptionObject, status);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomExceptionObject> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return handleException(HttpStatus.NOT_FOUND, ex);
    }
    @ExceptionHandler(SecurityFilterException.class)
    public ResponseEntity<CustomExceptionObject> handleSecurityFilterException(SecurityFilterException ex) {
        return handleException(HttpStatus.UNAUTHORIZED, ex);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<CustomExceptionObject> handleDataIntegrityViolationException(DataIntegrityViolationException ex){
        return handleException(HttpStatus.BAD_REQUEST,ex);
    }
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<CustomExceptionObject> handleInternalAuthenticationServiceException(InternalAuthenticationServiceException ex){
        return handleException(HttpStatus.UNAUTHORIZED,ex);
    }
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<CustomExceptionObject> handleInvalidCredentialsException(InvalidCredentialsException ex){
        return handleException(HttpStatus.UNAUTHORIZED,ex);
    }

    @ExceptionHandler(ResourceNotCreated.class)
    public ResponseEntity<CustomExceptionObject> handleResourceNotCreated(ResourceNotCreated ex){
        return handleException(HttpStatus.BAD_REQUEST,ex);
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<CustomExceptionObject> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        return handleException(HttpStatus.BAD_REQUEST,ex);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<CustomExceptionObject> handleBadRequestException(BadRequestException ex){
        return handleException(HttpStatus.BAD_REQUEST,ex);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomExceptionObject> handleGenericException(Exception ex){
        return handleException(HttpStatus.INTERNAL_SERVER_ERROR,ex);
    }


}
