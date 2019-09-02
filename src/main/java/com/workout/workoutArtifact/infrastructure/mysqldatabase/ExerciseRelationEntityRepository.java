package com.workout.workoutArtifact.infrastructure.mysqldatabase;

import com.workout.workoutArtifact.domain.exerciserelation.model.ExerciseRelation;
import com.workout.workoutArtifact.domain.exerciserelation.model.ExerciseRelationRepository;
import com.workout.workoutArtifact.infrastructure.common.mapper.ExerciseRelationMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseRelationEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper
    .ExerciseRelationSpecificationMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.ExerciseRelationJpaRepository;
import com.workout.workoutArtifact.specification.Specification;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ExerciseRelationEntityRepository implements ExerciseRelationRepository {

  private final ExerciseRelationJpaRepository exerciseRelationJpaRepository;
  private final ExerciseRelationSpecificationMapper exerciseRelationSpecificationMapper;
  private final ExerciseRelationMapper exerciseRelationMapper;

  public List<ExerciseRelation> getExerciseRelations(Specification<ExerciseRelation> specification) {


    org.springframework.data.jpa.domain.Specification<ExerciseRelationEntity> jpaSpecification = exerciseRelationSpecificationMapper.toJpaSpecification(specification);

    List<ExerciseRelationEntity> exerciseRelationEntities = exerciseRelationJpaRepository.findAll(jpaSpecification);

    return exerciseRelationMapper.toDomainObject(exerciseRelationEntities);
  }

}
