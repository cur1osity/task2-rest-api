package com.cur1osity.task2restapi.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class TaskNotFoundException extends Exception {

    public TaskNotFoundException() {
        super("Task doesn't exist !");
    }

    public TaskNotFoundException(String message) {
        super(message);
    }
}
