package com.workout.workoutArtifact.backend.mysqldatabase.entity;

import com.workout.workoutArtifact.endpoint.specification.WorkoutSetSpecification;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@EqualsAndHashCode(exclude = "exerciseEntity")
@RequiredArgsConstructor

@Entity
@Table(name = "workoutset")
public class WorkoutSetEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NonNull
  @Column
  private int repetitions;

  @NonNull
  @Column
  private boolean single;

  @NonNull
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn
  private ExerciseEntity exerciseEntity;

}
