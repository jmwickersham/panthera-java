package com.panthera.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment extends AuditModel {
  @Id
  @GeneratedValue(generator = "comment_generator")
  @SequenceGenerator(
      name = "comment_generator",
      sequenceName = "comment_sequence",
      initialValue = 1000
  )
  private Long id;

  @Column(columnDefinition = "text")
  private String text;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "task_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private Task task;
  // created_by (user object)
  // updated_by (user object)

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Task getTask() {
    return task;
  }

  public void setTask(Task task) {
    this.task = task;
  }
}