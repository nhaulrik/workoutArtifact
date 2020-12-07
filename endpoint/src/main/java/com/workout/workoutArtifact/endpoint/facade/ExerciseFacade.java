package com.workout.workoutArtifact.endpoint.facade;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.domain.exercise.service.ExerciseService;
import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import com.workout.workoutArtifact.domain.specification.Specification;
import com.workout.workoutArtifact.endpoint.mapper.dto.ExerciseDtoMapper;
import com.workout.workoutArtifact.endpoint.mapper.specification.ExerciseDtoSpecificationMapper;
import com.workout.workoutArtifact.endpoint.dto.ExerciseDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExerciseFacade {

  private final ExerciseService exerciseService;
  private final ExerciseDtoMapper exerciseDtoMapper;
  private final ExerciseDtoSpecificationMapper exerciseDtoSpecificationMapper;

  public String addExercises(List<Exercise> exerciseList) {
    return exerciseService.addExercises(exerciseList);
  }

  public List<ExerciseDto> getExercises(AbstractSpecification<ExerciseDto> exerciseDtoSpecification) {

    Specification<Exercise> exerciseSpecification = exerciseDtoSpecificationMapper.toExerciseSpecification(exerciseDtoSpecification);

    return exerciseService.getExercises(exerciseSpecification).stream()
        .filter(exerciseSpecification::isSatisfiedBy)
        .map(exerciseDtoMapper::toDto)
        .collect(Collectors.toList());
  }

  public void addExercise(ExerciseDto exerciseDto) {
    //exerciseService.addExercises(Arrays.asList(exerciseDtoMapper.toDomainObject(exerciseDto)));
    throw new RuntimeException("not implemented");
  }
}
