package com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.programme;

import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseEntity;
import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
public class SplitExerciseSetEntity {

  private Integer repetitionMaximum;
  @NonNull
  @OneToOne
  @JoinColumn(foreignKey = @ForeignKey(name = "fk_exercise"))
  private ExerciseEntity exerciseEntity;

}
