package com.workout.workoutArtifact.mysql.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@RequiredArgsConstructor
@Entity
@Table(name = "workoutset")
@Getter
@Setter
public class WorkoutSetEntity {

  @Id
  private String id;

  @CreationTimestamp
  @Column(name = "create_date")
  private LocalDateTime createDate;

  @UpdateTimestamp
  @Column(name = "modify_date")
  private LocalDateTime modifyDate;

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

  public UUID getId() {
    return UUID.fromString(this.id);
  }

}
