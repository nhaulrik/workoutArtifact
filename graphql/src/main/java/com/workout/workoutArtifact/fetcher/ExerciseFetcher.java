package com.workout.workoutArtifact.fetcher;

import com.workout.workoutArtifact.dto.ExerciseDto;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import com.workout.workoutArtifact.dto.mapper.ExerciseDtoMapper;
import com.workout.workoutArtifact.mysql.entity.ExerciseEntity;
import com.workout.workoutArtifact.mysql.mapper.ExerciseSpecificationMapper;
import com.workout.workoutArtifact.mysql.repository.ExerciseJpaRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExerciseFetcher {

  private final ExerciseJpaRepository ExerciseJpaRepository;
  private final ExerciseSpecificationMapper exerciseSpecificationMapper;
  private final ExerciseDtoMapper ExerciseDtoMapper;

  public List<ExerciseDto> getExercises(AbstractSpecification aggregatedSpecification) {

    Specification jpaSpecification = exerciseSpecificationMapper.toJpaSpecification(aggregatedSpecification);

    List<ExerciseEntity> exerciseEntities = ExerciseJpaRepository.findAll(jpaSpecification);
    List<ExerciseDto> exerciseDtos = exerciseEntities.stream().map(ExerciseDtoMapper::toDto).collect(Collectors.toList());

    return exerciseDtos;
  }
}
