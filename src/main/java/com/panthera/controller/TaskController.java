package com.panthera.controller;

import com.panthera.repository.TaskRepository;
import com.panthera.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import javax.validation.Valid;

@RestController
public class TaskController {
  @Autowired
  private TaskRepository taskRepository;

  @GetMapping("/tasks")
  public Page<Task> getTasks(Pageable pageable) {
    return taskRepository.findAll(pageable);
  }

  @PostMapping("/tasks")
  public Task createTask(@Valid @RequestBody Task task) {
    return taskRepository.save(task);
  }
}
