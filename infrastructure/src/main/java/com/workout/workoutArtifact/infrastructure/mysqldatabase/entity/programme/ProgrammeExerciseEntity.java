package com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.programme;

import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseEntity;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "programme_exercise")
public class ProgrammeExerciseEntity {

  @Id
  private String id;
  @Column
  private Integer number;
  @Column
  private Integer RPE;
  @Column
  private Integer repetitions;
  @Column
  private Integer setAmount;
  @Column
  private Integer rest;

  @ManyToOne
  @JoinColumn(name = "split_id", foreignKey = @ForeignKey(name = "FK_programme_split_id"))
  private SplitEntity splitEntity;

  @ManyToOne
  @JoinColumn(name = "exercise_id", foreignKey = @ForeignKey(name = "FK_programme_exercise_id"))
  private ExerciseEntity exerciseEntity;

  public UUID getId() {
    return UUID.fromString(this.id);
  }

}
