package com.cur1osity.task2restapi.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class TaskConflictException  extends Exception{
}

