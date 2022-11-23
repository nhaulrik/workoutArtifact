package com.workout.workoutArtifact.fetcher;

import com.workout.workoutArtifact.specification.AbstractSpecification;
import com.workout.workoutArtifact.dto.WorkoutExerciseDto;
import com.workout.workoutArtifact.dto.mapper.WorkoutExerciseDtoMapper;
import com.workout.workoutArtifact.mysql.entity.WorkoutExerciseEntity;
import com.workout.workoutArtifact.mysql.mapper.WorkoutExerciseSpecificationMapper;
import com.workout.workoutArtifact.mysql.repository.WorkoutExerciseJpaRepository;
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
