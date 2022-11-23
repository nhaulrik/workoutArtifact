package com.workout.workoutArtifact.mysql.entity.programme;

import com.workout.workoutArtifact.mysql.entity.ExerciseEntity;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "split_exercise_set")
public class SplitExerciseSetEntity {

  @Id
  private String id;
  private Integer repetitionMaximum;

  @NonNull
  @OneToOne
  @JoinColumn(foreignKey = @ForeignKey(name = "fk_exercise"))
  private ExerciseEntity exerciseEntity;

  public SplitExerciseSetEntity(String id, Integer repetitionMaximum, ExerciseEntity exerciseEntity) {
    this.id = id;
    this.repetitionMaximum = repetitionMaximum;
    this.exerciseEntity = exerciseEntity;
  }

  public UUID getId() {
    return UUID.fromString(this.id);
  }
}
