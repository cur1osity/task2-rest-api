package com.cur1osity.task2restapi.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TaskDto {

    private Long id;
    @NotNull(message = "NotNull.taskDto.title")
    @Size(min = 1, max = 64, message = "Size.taskDto.title")
    private String title;
    @NotNull(message = "NotNull.taskDto.description")
    @Size(min = 1, max = 255, message = "Size.taskDto.description")
    private String description;
    private String startDate;
    private String updatedDate;
    private boolean completed;
    private String endDate;


    public TaskDto() {
    }

    public TaskDto(Long id, String title, String description, String startDate, String updatedDate, boolean completed, String endDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.updatedDate = updatedDate;
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

    public String getUpdatedDate() {
        return updatedDate;
    }
}
