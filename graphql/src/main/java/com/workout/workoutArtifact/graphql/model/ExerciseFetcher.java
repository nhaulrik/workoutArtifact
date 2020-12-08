package com.workout.workoutArtifact.graphql.model;

import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import com.workout.workoutArtifact.graphql.dto.ExerciseDto;
import com.workout.workoutArtifact.graphql.dto.mapper.ExerciseDtoMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.ExerciseSpecificationMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.ExerciseJpaRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExerciseFetcher {

  private final ExerciseJpaRepository ExerciseJpaRepository;
  private final ExerciseSpecificationMapper ExerciseSpecificationMapper;
  private final ExerciseDtoMapper ExerciseDtoMapper;

  public List<ExerciseDto> getExercises(AbstractSpecification aggregatedSpecification) {

    Specification jpaSpecification = ExerciseSpecificationMapper.toJpaSpecification(aggregatedSpecification);

    List<ExerciseEntity> exerciseEntities = ExerciseJpaRepository.findAll(jpaSpecification);
    List<ExerciseDto> exerciseDtos = exerciseEntities.stream().map(ExerciseDtoMapper::toDto).collect(Collectors.toList());

    return exerciseDtos;
  }
}
