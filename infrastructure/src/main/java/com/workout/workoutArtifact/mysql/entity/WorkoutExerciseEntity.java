package com.workout.workoutArtifact.mysql.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@RequiredArgsConstructor
@Entity
@Table(name = "workout_exercise")
@Getter
@Setter
public class WorkoutExerciseEntity {

  @Id
  private String id;

  @CreationTimestamp
  @Column(name = "create_date")
  private LocalDateTime createDate;

  @UpdateTimestamp
  @Column(name = "modify_date")
  private LocalDateTime modifyDate;


  @Column
  private Integer exerciseNumber;

  @Column
  private Boolean isWarmup;

  @OneToMany(mappedBy = "workoutExerciseEntity", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<WorkoutSetEntity> workoutSets = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "session_id", foreignKey = @ForeignKey(name = "FK_workout_exercise_session_id"))
  SessionEntity sessionEntity;

  @ManyToOne
  @JoinColumn(name = "exercise_id", foreignKey = @ForeignKey(name = "FK_workout_exercise_exercise_id"))
  private ExerciseEntity exerciseEntity;

  public UUID getId() {
    return UUID.fromString(this.id);
  }

}
