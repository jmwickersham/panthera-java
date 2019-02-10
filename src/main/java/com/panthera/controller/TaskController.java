package com.panthera.controller;

import com.panthera.exception.ResourceNotFoundException;
import com.panthera.repository.TaskRepository;
import com.panthera.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import javax.validation.Valid;

@RestController
public class TaskController {
  @Autowired
  private TaskRepository taskRepository;

  @GetMapping("/api/tasks")
  public Page<Task> getTasks(Pageable pageable) {
    return taskRepository.findAll(pageable);
  }

  @GetMapping("/api/tasks/{taskId}")
  public Task getTask(@PathVariable Long taskId) {
    return taskRepository.findById(taskId)
        .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + taskId));
  }

  @PostMapping("/api/tasks")
  public Task createTask(@Valid @RequestBody Task task) {
    return taskRepository.save(task);
  }

  @PutMapping("/api/tasks/{taskId}")
  public Task updateTask(@PathVariable Long taskId, @Valid @RequestBody Task taskRequest) {
    return taskRepository.findById(taskId)
        .map(task -> {
          task.setShort_description(taskRequest.getShort_Description());
          task.setDescription(taskRequest.getDescription());
          return taskRepository.save(task);
        }).orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + taskId));
  }

  @DeleteMapping("/api/tasks/{taskId}")
  public ResponseEntity<?> deleteTask(@PathVariable Long taskId) {
    return taskRepository.findById(taskId)
        .map(task -> {
          taskRepository.delete(task);
          return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + taskId));
  }
}
