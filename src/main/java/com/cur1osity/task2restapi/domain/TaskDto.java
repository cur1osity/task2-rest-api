package com.cur1osity.task2restapi.domain;

import java.time.LocalDateTime;

public class TaskDto {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime startDate;
    private boolean completed;
    private LocalDateTime endDate;


    public TaskDto() {
    }

    public TaskDto(Long id, String title, String description, LocalDateTime startDate, boolean completed, LocalDateTime endDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.completed = completed;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}
