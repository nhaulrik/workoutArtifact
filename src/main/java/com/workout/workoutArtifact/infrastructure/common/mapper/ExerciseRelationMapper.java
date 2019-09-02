package com.workout.workoutArtifact.infrastructure.common.mapper;

import com.workout.workoutArtifact.domain.exerciserelation.model.ExerciseRelation;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseRelationEntity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ExerciseRelationMapper {

  public List<ExerciseRelation> toDomainObject(List<ExerciseRelationEntity> exerciseRelationEntities) {

    Map<Integer, List<Integer>> exerciseRelationMap = new HashMap<>();

    exerciseRelationEntities.stream()
        .map(entity -> exerciseRelationMap.get(entity.getCompositeIdEntity().getExerciseId()) != null ?
            exerciseRelationMap.get(entity.getCompositeIdEntity().getExerciseId()).add(entity.getCompositeIdEntity().getMuscleId()) :
            exerciseRelationMap.put(entity.getCompositeIdEntity().getExerciseId(), new ArrayList<>(Arrays.asList(entity.getCompositeIdEntity().getMuscleId()))))
        .collect(Collectors.toList());

    return exerciseRelationMap.keySet().stream()
        .map(key -> ExerciseRelation.builder()
            .exerciseId(key)
            .relatedMuscleIds(exerciseRelationMap.get(key))
            .build())
        .collect(Collectors.toList());
  }

}
