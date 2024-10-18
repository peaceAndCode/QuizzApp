package com.peaceandcode.quizapp.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomExceptionObject {
    private int statusCode;
    private String message;
    private Timestamp timestamp;
    private String exceptionName;
}
