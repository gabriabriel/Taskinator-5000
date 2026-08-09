package com.taskinator5000_api.exception;

public class TaskNotFoundExeption extends RuntimeException {
    public TaskNotFoundExeption(String message) {
        super(message);
    }
}
