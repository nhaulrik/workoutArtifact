package com.workout.workoutArtifact.domain.exerciserelation.service;

import com.workout.workoutArtifact.domain.exerciserelation.model.ExerciseRelation;
import com.workout.workoutArtifact.domain.exerciserelation.model.ExerciseRelationRepository;
import com.workout.workoutArtifact.specification.Specification;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExerciseRelationService {

  private final ExerciseRelationRepository exerciseRelationRepository;

  public List<ExerciseRelation> getRelatedMuscleIds(Specification<ExerciseRelation> exerciseRelationSpecification) {
    return exerciseRelationRepository.getExerciseRelations(exerciseRelationSpecification);
  }
}
