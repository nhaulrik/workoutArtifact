package com.workout.workoutArtifact.graphql.model;

import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import com.workout.workoutArtifact.graphql.dto.WorkoutExerciseDto;
import com.workout.workoutArtifact.graphql.dto.WorkoutSetDto;
import com.workout.workoutArtifact.graphql.dto.mapper.WorkoutExerciseDtoMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.WorkoutExerciseEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.WorkoutExerciseSpecificationMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.WorkoutExerciseJpaRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkoutExerciseFetcher {

  private final WorkoutExerciseJpaRepository workoutExerciseJpaRepository;
  private final WorkoutExerciseSpecificationMapper workoutExerciseSpecificationMapper;
  private final WorkoutExerciseDtoMapper workoutExerciseDtoMapper;


  public List<WorkoutExerciseDto> getWorkoutExercises(AbstractSpecification aggregatedSpecification) {

    Specification jpaSpecification = workoutExerciseSpecificationMapper.toJpaSpecification(aggregatedSpecification);

    List<WorkoutExerciseEntity> workoutExerciseEntities = workoutExerciseJpaRepository.findAll(jpaSpecification);
    List<WorkoutExerciseDto> workoutExerciseDtos = workoutExerciseEntities.stream().map(workoutExerciseDtoMapper::toDto).collect(Collectors.toList());

    return workoutExerciseDtos;
  }

}
