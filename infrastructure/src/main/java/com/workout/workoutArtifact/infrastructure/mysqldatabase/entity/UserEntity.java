package com.workout.workoutArtifact.infrastructure.mysqldatabase.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity {

  @Id
  private String id;

  @CreationTimestamp
  @Column(name = "create_date")
  private LocalDateTime createDate;

  @UpdateTimestamp
  @Column(name = "modify_date")
  private LocalDateTime modifyDate;


  @Column
  private String firstName;

  @Column
  private String lastName;

  @Column
  private LocalDate birthDay;

  @Column
  private String gender;

  @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<SessionEntity> sessionEntities = new HashSet<>();

  @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
  private List<BodyMeasurementEntity> bodyMeasurementEntities = new ArrayList();

  public UUID getId() {
    return UUID.fromString(this.id);
  }

  public void setId(UUID id) {
    this.id = id.toString();
  }
}
