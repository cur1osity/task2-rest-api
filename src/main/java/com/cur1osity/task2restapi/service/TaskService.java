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

    public Task updateTaskWithId(Long id, Task task) throws TaskNotFoundException {

        if(repository.existsById(id)) {

            task.setId(id);

            if(task.getUpdatedDate() == null) {
                task.setUpdatedDate(dateFormatter().format(LocalDateTime.now()));
            }

            return repository.save(task);
        }

        throw new TaskNotFoundException();
    }

    public boolean isTaskExist(Long id) {
        return repository.existsById(id);
    }

    public void deleteTask(Long id) {
        repository.deleteById(id);
    }

    public void deleteAllTask() {
        repository.deleteAllInBatch();
    }

    private DateTimeFormatter dateFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

}
