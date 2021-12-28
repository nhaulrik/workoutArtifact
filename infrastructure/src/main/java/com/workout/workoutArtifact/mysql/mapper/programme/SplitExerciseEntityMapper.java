package com.workout.workoutArtifact.mysql.mapper.programme;

import com.workout.workoutArtifact.mysql.entity.programme.SplitExerciseEntity;
import com.workout.workoutArtifact.programme.SplitExercise;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SplitExerciseEntityMapper {

  private final SplitExerciseSetEntityMapper splitExerciseSetEntityMapper;


  public SplitExercise toDomain(SplitExerciseEntity splitExerciseEntity) {
    return SplitExercise.instantiate(
        splitExerciseEntity.getId(),
        splitExerciseEntity.getSplitExerciseNumber(),
        splitExerciseEntity.getSplitExerciseSetEntitySet().stream()
            .map(splitExerciseSetEntityMapper::toDomain).collect(Collectors.toList()));
  }

  public SplitExerciseEntity toEntity(SplitExercise splitExercise) {
    return new SplitExerciseEntity(
        splitExercise.getId().toString(),
        splitExercise.getSplitExerciseNumber(),
        splitExercise.getSplitExerciseSets().stream().map(splitExerciseSetEntityMapper::toEntity)
            .collect(Collectors.toSet()));
  }
}
