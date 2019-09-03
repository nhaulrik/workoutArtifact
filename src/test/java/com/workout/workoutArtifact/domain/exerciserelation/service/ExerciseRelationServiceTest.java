package com.workout.workoutArtifact.domain.exerciserelation.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import com.workout.workoutArtifact.domain.exerciserelation.model.ExerciseRelation;
import com.workout.workoutArtifact.domain.exerciserelation.model.ExerciseRelation.IdsSpecification;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.ExerciseRelationEntityRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ExerciseRelationServiceTest {

  private final ExerciseRelationEntityRepository exerciseRelationEntityRepository = mock(ExerciseRelationEntityRepository.class);
  private final ExerciseRelationService exerciseRelationService = new ExerciseRelationService(exerciseRelationEntityRepository);

  @Test
  public void getExerciseRelations() {

    Long someExerciseId = 1111L;
    Long someRelatedMuscleId = 1234L;
    ExerciseRelation.IdsSpecification idsSpecification = new IdsSpecification(Arrays.asList(someExerciseId));

    ExerciseRelation exerciseRelation = ExerciseRelation.builder()
        .exerciseId(someExerciseId)
        .relatedMuscleIds(Arrays.asList(someRelatedMuscleId))
        .build();

    doReturn(Arrays.asList(exerciseRelation))
        .when(exerciseRelationEntityRepository).getExerciseRelations(idsSpecification);

    List<ExerciseRelation> exerciseRelationList = exerciseRelationService.getRelatedMuscleIds(idsSpecification);

    assertThat(exerciseRelationList.size(), is(1));
    assertThat(exerciseRelationList.get(0).getExerciseId(), is(someExerciseId));
    assertThat(exerciseRelationList.get(0).getRelatedMuscleIds().get(0), is(someRelatedMuscleId));
  }
}