package com.cur1osity.task2restapi.controller;

import com.cur1osity.task2restapi.domain.Task;
import com.cur1osity.task2restapi.domain.TaskDto;
import com.cur1osity.task2restapi.mapper.TaskMapper;
import com.cur1osity.task2restapi.service.TaskConflictException;
import com.cur1osity.task2restapi.service.TaskNotFoundException;
import com.cur1osity.task2restapi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TaskService service;

    @Autowired
    private TaskMapper taskMapper;

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
    public void deleteTask(@PathVariable long id) {
        service.deleteTask(id);
    }


    @PutMapping(value = {"/{id}"}, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public TaskDto updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto) throws TaskNotFoundException {

        Task task = taskMapper.mapToTask(taskDto);

        Task taskAfterUpdate = service.updateTaskWithId(id, task);

        return taskMapper.mapToTaskDto(taskAfterUpdate);
    }

    @PatchMapping(value = {"/{id}"}, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public TaskDto updateTask2(@PathVariable Long id, @RequestBody TaskDto taskDto) throws TaskNotFoundException {

        final Task existingTask = service.getTask(id).orElseThrow(TaskNotFoundException::new);

        Task task = taskMapper.mapToTask(taskDto);

        task.setStartDate(existingTask.getStartDate());

        Task taskAfterUpdate = service.updateTaskWithId(id, task);

        return taskMapper.mapToTaskDto(taskAfterUpdate);
    }


    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createTask(@RequestBody TaskDto taskDto) throws TaskConflictException {

        final Task taskFromJSON = taskMapper.mapToTask(taskDto);

        if (taskFromJSON.getId() != null) {

            if (service.isTaskExist(taskFromJSON.getId())) {

                throw new TaskConflictException();
            }

            service.saveTask(taskFromJSON);

        } else {

            service.saveTask(taskFromJSON);
        }
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteTasks() {
        service.deleteAllTask();
    }
}
