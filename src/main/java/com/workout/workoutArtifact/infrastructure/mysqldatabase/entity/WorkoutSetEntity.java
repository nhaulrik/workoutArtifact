package com.workout.workoutArtifact.infrastructure.mysqldatabase.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
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
  private double weight;

  @NonNull
  @Column
  private boolean single;

  @NonNull
  @Column
  private int repetitionMaximum;

  @NonNull
  @Column
  private int setNumber;

  @NonNull
  @Column
  private Long exerciseId;

}
