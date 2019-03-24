package com.workout.workoutArtifact.backend.mysqldatabase.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = "exerciseSet")

@Entity
@Table(name = "muscle")
public class MuscleEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NonNull
  @Column
  private String name;

  @NonNull
  @Column
  private String bodyPart;

  @ManyToMany(mappedBy = "muscleEntities", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Set<ExerciseEntity> exerciseSet = new HashSet<>();

}
