package com.panthera.controller;

import com.panthera.model.Comment;
import com.panthera.repository.CommentRepository;
import com.panthera.repository.TaskRepository;
import com.panthera.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import javax.validation.Valid;

@RestController
public class CommentController {

  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private TaskRepository taskRepository;

  @GetMapping("/tasks/{taskId}/comments")
  public List<Comment> getCommentsByTaskId(@PathVariable Long taskId) {
    return commentRepository.findByTaskId(taskId);
  }

  @PostMapping("/tasks/{taskId}/comments")
  public Comment addComment(@PathVariable Long taskId, @Valid @RequestBody Comment comment) {
    return taskRepository.findById(taskId).map(
        task -> {
          comment.setTask(task);
          return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + taskId));
  }
}
