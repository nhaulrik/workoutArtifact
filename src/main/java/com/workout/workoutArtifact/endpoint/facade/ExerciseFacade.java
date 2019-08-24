package com.workout.workoutArtifact.endpoint.facade;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.domain.exercise.service.ExerciseService;
import com.workout.workoutArtifact.endpoint.dto.ExerciseDto;
import com.workout.workoutArtifact.endpoint.mapper.ExerciseDtoSpecificationMapper;
import com.workout.workoutArtifact.infrastructure.common.mapper.ExerciseMapper;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import com.workout.workoutArtifact.specification.Specification;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExerciseFacade {

  private final ExerciseService exerciseService;
  private final ExerciseMapper mapper;
  private final ExerciseDtoSpecificationMapper exerciseDtoSpecificationMapper;

  public String addExercises(List<Exercise> exerciseList) {
    return exerciseService.addExercises(exerciseList);
  }

  public List<ExerciseDto> getExercises(AbstractSpecification<ExerciseDto> exerciseDtoSpecification) {

    Specification<Exercise> exerciseSpecification = exerciseDtoSpecificationMapper.toExerciseSpecification(exerciseDtoSpecification);

    return exerciseService.getExercises(exerciseSpecification).stream()
        .filter(exerciseSpecification::isSatisfiedBy)
        .map(mapper::toDto)
        .collect(Collectors.toList());
  }
}
