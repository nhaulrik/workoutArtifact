package com.workout.workoutArtifact.infrastructure.mysqldatabase.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "session")
public class SessionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @CreationTimestamp
  private LocalDateTime creationDateTime;

  @NonNull
  @Column
  private String location;

  @NonNull
  @OneToMany(mappedBy = "sessionEntity", fetch = FetchType.EAGER)
  private Set<WorkoutSetEntity> workoutSetEntities = new HashSet<>();

}
