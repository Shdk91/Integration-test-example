package com.example.integrationtestexample.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handleException(Exception exception) {
        exception.printStackTrace();
        return ResponseEntity.internalServerError().body("Неизветсная ошибка");
    }
}
