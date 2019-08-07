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
@EqualsAndHashCode(exclude = "exerciseEntity")
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

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinTable(name = "workoutset_exercise",
  joinColumns = @JoinColumn(name = "workoutset_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "exercise_id", referencedColumnName = "id"))
  private ExerciseEntity exerciseEntity;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "session_id")
  private SessionEntity sessionEntity;

}
