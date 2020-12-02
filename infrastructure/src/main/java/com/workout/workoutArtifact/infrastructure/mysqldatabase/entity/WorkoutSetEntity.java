package com.workout.workoutArtifact.infrastructure.mysqldatabase.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
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
  private String id;

  @Column
  private Integer repetitions;

  @Column
  private Double weight;

  @Column
  private Boolean single;

  @Column
  private Integer repetitionMaximum;

  @Column
  private Integer setNumber;

  @ManyToOne
  @JoinColumn(name = "workout_exercise_id", foreignKey = @ForeignKey(name = "FK_workoutset_workout_exercise_id"))
  private WorkoutExerciseEntity workoutExerciseEntity;

  @ManyToOne
  @JoinColumn(name = "exercise_id", foreignKey = @ForeignKey(name = "FK_workoutset_exercise_id"))
  private ExerciseEntity exerciseEntity;

  @ManyToOne
  @JoinColumn(name = "session_id", foreignKey = @ForeignKey(name = "FK_workoutset_session_id"))
  private SessionEntity sessionEntity;

}
