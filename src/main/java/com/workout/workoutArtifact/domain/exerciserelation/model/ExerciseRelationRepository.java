package com.workout.workoutArtifact.domain.exerciserelation.model;

import com.workout.workoutArtifact.specification.Specification;
import java.util.List;

public interface ExerciseRelationRepository {

  List<ExerciseRelation> getExerciseRelations(Specification<ExerciseRelation> exerciseRelationSpecification);

}
