package com.cur1osity.task2restapi.service;

import com.cur1osity.task2restapi.domain.Task;
import com.cur1osity.task2restapi.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Optional<Task> getTask(Long id) {
        return repository.findById(id);
    }

    public Task saveTask (final Task task) {

        if(task.getStartDate() == null) {
            task.setStartDate(dateFormatter().format(LocalDateTime.now()));
        }

        return repository.save(task);
    }

    public Task saveTaskWithId(Long id, Task task) throws TaskNotFoundException {

        if(isTaskExist(id)) {

            task.setId(id);

            if(task.getStartDate() == null) {
                task.setStartDate(repository.getOne(id).getStartDate());
            }

            if(task.getUpdatedDate() == null) {
                task.setUpdatedDate(dateFormatter().format(LocalDateTime.now()));
            }

            if(task.isCompleted()) {
                task.setEndDate(dateFormatter().format(LocalDateTime.now()));
            }

            return repository.save(task);
        }

        throw new TaskNotFoundException();
    }

    public void deleteTask(Long id) throws TaskNotFoundException {

        if(isTaskExist(id)) {
            repository.deleteById(id);
        }

        throw new TaskNotFoundException();
    }

    public void deleteAllTask() {
        repository.deleteAllInBatch();
    }

    public boolean isTaskExist(Long id) {
        return repository.existsById(id);
    }

    private DateTimeFormatter dateFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

}
