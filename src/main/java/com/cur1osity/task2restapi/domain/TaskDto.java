package com.cur1osity.task2restapi.domain;

public class TaskDto {

    private Long id;
    private String title;
    private String description;
    private String startDate;
    private boolean completed;
    private String endDate;


    public TaskDto() {
    }

    public TaskDto(Long id, String title, String description, String startDate, boolean completed, String endDate) {
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

    public String getStartDate() {
        return startDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getEndDate() {
        return endDate;
    }
}
