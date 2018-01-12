package com.cur1osity.task2restapi.service;

import com.cur1osity.task2restapi.config.AdminConfig;
import com.cur1osity.task2restapi.domain.Mail;
import com.cur1osity.task2restapi.domain.Task;
import com.cur1osity.task2restapi.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class TaskService {

    private static final String SUBJECT = "New TASK was created!";

    @Autowired
    private TaskRepository repository;

    @Autowired
    private SimpleEmailService emailService;

    @Autowired
    private AdminConfig adminConfig;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task getTask(Long id) {
        return repository.getOne(id);
    }

    public Task saveTask(final Task task) {

        if (task.getStartDate() == null) {
            task.setStartDate(dateFormatter().format(LocalDateTime.now()));
        }

        ofNullable(task).ifPresent(
                taskX -> emailService.send(
                        new Mail(
                                adminConfig.getAdminMail(),
                         //       adminConfig.getSecondEmail(),
                                SUBJECT,
                                "[Task title]: " + task.getTitle() + "\n" +
                        "[Task desc]: " + task.getDescription())));

        return repository.save(task);
    }

    public Task saveTaskWithId(Long id, Task task) {

        task.setId(id);

        if (task.getStartDate() == null) {
            task.setStartDate(repository.getOne(id).getStartDate());
        }

        if (task.getUpdatedDate() == null) {
            task.setUpdatedDate(dateFormatter().format(LocalDateTime.now()));
        }

        if (task.isCompleted()) {
            task.setEndDate(dateFormatter().format(LocalDateTime.now()));
        }

        return repository.save(task);
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
