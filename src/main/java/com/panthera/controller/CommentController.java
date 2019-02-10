package com.panthera.controller;

import com.panthera.model.Comment;
import com.panthera.repository.CommentRepository;
import com.panthera.repository.TaskRepository;
import com.panthera.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;

@RestController
public class CommentController {

  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private TaskRepository taskRepository;

  @GetMapping("/api/tasks/{taskId}/comments")
  public List<Comment> getCommentsByTaskId(@PathVariable Long taskId) {
    return commentRepository.findByTaskId(taskId);
  }

  @PostMapping("/api//tasks/{taskId}/comments")
  public Comment addComment(@PathVariable Long taskId, @Valid @RequestBody Comment comment) {
    return taskRepository.findById(taskId).map(
        task -> {
          comment.setTask(task);
          return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + taskId));
  }

  @PutMapping("/api/tasks/{taskId}/comments/{commentId}")
  public Comment updateComment(@PathVariable Long taskId, @PathVariable Long commentId, @Valid @RequestBody Comment commentRequest) {
    if (!taskRepository.existsById(taskId)) {
      throw new ResourceNotFoundException("Task not found with id " + taskId);
    }

    return commentRepository.findById(commentId)
        .map(comment -> {
          commentRequest.setText(commentRequest.getText());
          return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + commentId));
  }

  @DeleteMapping("/api/tasks/{taskId}/comments/{commentid}")
  public ResponseEntity<?> deleteComment(@PathVariable Long taskId, @PathVariable Long commentId) {
    if (!taskRepository.existsById(taskId)) {
      throw new ResourceNotFoundException("Task not found with id " + taskId);
    }

    return commentRepository.findById(commentId)
        .map(comment -> {
          commentRepository.delete(comment);
          return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + commentId));
  }
}
