package com.workout.workoutArtifact.infrastructure.mysqldatabase.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Entity
@Table(name = "workoutset")
@Getter
@Setter
public class WorkoutSetEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private int repetitions;

  @Column
  private double weight;

  @Column
  private boolean single;

  @Column
  private int repetitionMaximum;

  @Column
  private int setNumber;

  @ManyToOne
  @JoinColumn
  private ExerciseEntity exerciseEntity;

  @ManyToOne
  @JoinColumn
  private SessionEntity sessionEntity;

}
