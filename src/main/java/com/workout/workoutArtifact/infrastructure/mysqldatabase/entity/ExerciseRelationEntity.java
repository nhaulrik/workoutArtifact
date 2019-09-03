package com.workout.workoutArtifact.infrastructure.mysqldatabase.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "exercise_muscle")
@Data
public class ExerciseRelationEntity {

  @EmbeddedId
  private CompositeIdEntity compositeIdEntity;

  @Data
  @Embeddable
  public static class CompositeIdEntity implements Serializable {

    @Column(name = "muscle_id")
    private Long muscleId;

    @Column(name = "exercise_id")
    private Long exerciseId;
  }
}