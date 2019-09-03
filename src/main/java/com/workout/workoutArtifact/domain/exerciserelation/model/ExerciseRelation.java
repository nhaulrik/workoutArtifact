package com.workout.workoutArtifact.domain.exerciserelation.model;

import com.workout.workoutArtifact.specification.AbstractSpecification;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Value;

@Data
@Builder
public class ExerciseRelation {


  @NonNull
  private Long exerciseId;

  @NonNull
  private List<Long> relatedMuscleIds;

  @Value
  public static class IdsSpecification extends AbstractSpecification<ExerciseRelation> {
    private final List<Long> exerciseIds;

    @Override
    public boolean isSatisfiedBy(ExerciseRelation exerciseRelation) {      return exerciseIds.contains(exerciseRelation.getRelatedMuscleIds());
      // TODO: 27-08-2019 this may not work and should default to true.
    }
  }
}
