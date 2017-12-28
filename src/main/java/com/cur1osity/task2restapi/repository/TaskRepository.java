package com.cur1osity.task2restapi.repository;

import com.cur1osity.task2restapi.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
