package com.panthera.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tasks")
public class Task extends AuditModel {
  @Id
  @GeneratedValue(generator = "task_generator")
  @SequenceGenerator(
      name = "task_generator",
      sequenceName = "task_dequence",
      initialValue = 1000
  )
  private Long id;

  @NotBlank
  @Size(min = 3, max = 100)
  private String short_description;

  @Column(columnDefinition = "text")
  private String description;
  // comments (comment object)
  // created_by (user object)
  // updated_by (user object)

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getShort_Description() {
    return short_description;
  }

  public void setShort_description(String short_description) {
    this.short_description = short_description;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
