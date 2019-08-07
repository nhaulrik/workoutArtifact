package com.workout.workoutArtifact.infrastructure.mysqldatabase.entity;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@Table(name = "session")
public class SessionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @CreationTimestamp
  private LocalDateTime creationDateTime;

  @Column
  private String location;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "sessionEntity")
  private List<WorkoutSetEntity> workoutSetEntities;

}
