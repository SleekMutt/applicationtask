package com.example.applicationtask.controller;

import com.example.applicationtask.dto.ErrorResponse;
import com.example.applicationtask.exception.DateViolationException;
import com.example.applicationtask.exception.UnderAgeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ControllerExceptionHandler {
  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<ErrorResponse> handleNoSuchElementException(NoSuchElementException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(true, List.of(ex.getMessage())));
  }

  @ExceptionHandler(UnderAgeException.class)
  public ResponseEntity<ErrorResponse> handleUnderAgeException(UnderAgeException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(true, List.of(ex.getMessage())));
  }

  @ExceptionHandler(DateViolationException.class)
  public ResponseEntity<ErrorResponse> handleDateViolationException(DateViolationException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(true, List.of(ex.getMessage())));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(true, errors.entrySet().stream()
            .map(entry -> entry.getKey() + " " + entry.getValue())
            .toList()));
  }
}