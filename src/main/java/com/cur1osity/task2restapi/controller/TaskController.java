package com.cur1osity.task2restapi.controller;

import com.cur1osity.task2restapi.domain.MessageDto;
import com.cur1osity.task2restapi.domain.Task;
import com.cur1osity.task2restapi.domain.TaskDto;
import com.cur1osity.task2restapi.mapper.TaskMapper;
import com.cur1osity.task2restapi.service.TaskNotFoundException;
import com.cur1osity.task2restapi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TaskService service;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDto> getTasks() {
        List<Task> allTasks = service.getAllTasks();
        return taskMapper.mapToTaskDtoList(allTasks);
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public TaskDto getTask(@PathVariable Long id) throws TaskNotFoundException {
        final Task task = service.getTask(id).orElseThrow(TaskNotFoundException::new);
        return taskMapper.mapToTaskDto(task);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteTask(@PathVariable Long id) throws TaskNotFoundException {

        service.deleteTask(id);
    }

    @PatchMapping(value = {"/{id}"}, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public TaskDto updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto) throws TaskNotFoundException {
        Task task = taskMapper.mapToTask(taskDto);
        Task taskAfterUpdate = service.saveTaskWithId(id, task);
        return taskMapper.mapToTaskDto(taskAfterUpdate);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createTask(@Valid @RequestBody TaskDto taskDto) {
        final Task taskFromJSON = taskMapper.mapToTask(taskDto);
        service.saveTask(taskFromJSON);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteTasks() {
        service.deleteAllTask();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public MessageDto handleValidationException(MethodArgumentNotValidException ex) {
        Locale locale = LocaleContextHolder.getLocale();
        String code = ex.getBindingResult().getFieldError().getDefaultMessage();
        return new MessageDto(messageSource.getMessage(code, null, locale));
    }
}
