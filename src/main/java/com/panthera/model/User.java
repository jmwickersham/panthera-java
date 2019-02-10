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
@Table(name = "users")
public class User extends AuditModel {
  @Id
  @GeneratedValue(generator = "user_generator")
  @SequenceGenerator(
      name = "user_generator",
      sequenceName = "user_dequence",
      initialValue = 1000
  )
  private Long id;

  // username String unique
  // first_name string
  // last_name string
  // email string
  // imageURL string
  // created_by (user object)
  // updated_by (user_object)
  // hash string
  // salt string
  // jwt methods and such for passwords, tokens, etc.

  // @NotBlank
  // @Size(min = 3, max = 100)
  // private String username;

  // @Column(columnDefinition = "text")
  // private String description;
  // created_by (user object)
  // updated_by (user object)

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
