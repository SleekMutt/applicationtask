package com.example.applicationtask.exception;

public class UnderAgeException extends RuntimeException{
  public UnderAgeException(String message) {
    super(message);
  }
}