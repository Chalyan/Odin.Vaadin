package com.example.application.exception;

public class EmployeeDoesNotExistException extends ServiceLayerException {

    public EmployeeDoesNotExistException(String message) {
        super(message);
    }
}
