package com.taskinator5000_api.exception;

public class TaskBusinessException extends RuntimeException {
    public TaskBusinessException(String message) {
        super(message);
    }
}
