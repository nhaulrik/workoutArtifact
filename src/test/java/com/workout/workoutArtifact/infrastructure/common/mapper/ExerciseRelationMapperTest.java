package com.workout.workoutArtifact.infrastructure.common.mapper;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.workout.workoutArtifact.domain.exerciserelation.model.ExerciseRelation;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseRelationEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseRelationEntity
    .CompositeIdEntity;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class ExerciseRelationMapperTest {

  private final ExerciseRelationMapper exerciseRelationMapper = new ExerciseRelationMapper();
  
  @Test
  public void mapsToDomainObject() {

    Long muscleId1 = 1L;
    Long muscleId2 = 2L;
    
    Long exerciseId1 = 10L;
    Long exerciseId2 = 20L;
    
    
    ExerciseRelationEntity exerciseRelationEntity1 = getExerciseRelationEntity(exerciseId1, muscleId1);
    ExerciseRelationEntity exerciseRelationEntity2 = getExerciseRelationEntity(exerciseId1, muscleId2);
    ExerciseRelationEntity exerciseRelationEntity3 = getExerciseRelationEntity(exerciseId2, muscleId1);
    
    List<ExerciseRelation> exerciseRelations = exerciseRelationMapper.toDomainObject(Arrays.asList(exerciseRelationEntity1, exerciseRelationEntity2, exerciseRelationEntity3));

    assertThat(exerciseRelations.get(0).getExerciseId(), is(exerciseId2));
    assertThat(exerciseRelations.get(0).getRelatedMuscleIds().contains(muscleId1), is(true));

    assertThat(exerciseRelations.get(1).getExerciseId(), is(exerciseId1));
    assertThat(exerciseRelations.get(1).getRelatedMuscleIds().contains(muscleId1), is(true));
    assertThat(exerciseRelations.get(1).getRelatedMuscleIds().contains(muscleId2), is(true));

  }
  
  private ExerciseRelationEntity getExerciseRelationEntity(Long exerciseId, Long muscleId) {
    ExerciseRelationEntity exerciseRelationEntity = new ExerciseRelationEntity();
    CompositeIdEntity compositeIdEntity = new CompositeIdEntity();
    compositeIdEntity.setMuscleId(muscleId);
    compositeIdEntity.setExerciseId(exerciseId);
    exerciseRelationEntity.setCompositeIdEntity(compositeIdEntity);
    return exerciseRelationEntity;
  }
}