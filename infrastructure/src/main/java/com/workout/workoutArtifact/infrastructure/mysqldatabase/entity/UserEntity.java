package com.workout.workoutArtifact.infrastructure.mysqldatabase.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity {

  @Id
  private String id;

  @Column
  private String firstName;

  @Column
  private String lastName;

  @Column
  private LocalDate birthDay;

  @Column
  private String gender;

  @OneToMany(mappedBy = "userEntity")
  private Set<SessionEntity> sessionEntities = new HashSet<>();

  public UUID getId() {
    return UUID.fromString(this.id);
  }

  public void setId(UUID id) {
    this.id = id.toString();
  }
}
