package com.example.application.exception;

public class EmployeeAlreadyExistsException extends ServiceLayerException {

    public EmployeeAlreadyExistsException(String message) {
        super(message);
    }
}
