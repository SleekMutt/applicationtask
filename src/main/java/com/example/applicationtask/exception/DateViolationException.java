package com.example.applicationtask.exception;

public class DateViolationException extends RuntimeException{
  public DateViolationException(String message) {
    super(message);
  }
}