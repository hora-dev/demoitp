package com.onebit.demoitp.infra.adapter.inbound.controller;

import com.onebit.demoitp.application.service.exception.EmpresaNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmpresaNotFoundException.class)
    public ResponseEntity<String> handleEmpresaNotFoundException(EmpresaNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
