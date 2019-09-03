package com.workout.workoutArtifact.infrastructure.mysqldatabase;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.workout.workoutArtifact.domain.exerciserelation.model.ExerciseRelation;
import com.workout.workoutArtifact.domain.exerciserelation.model.ExerciseRelation.IdsSpecification;
import com.workout.workoutArtifact.specification.MatchAllSpecification;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExerciseRelationEntityRepositoryTest {

  @Autowired
  private ExerciseRelationEntityRepository exerciseRelationEntityRepository;

  @Test
  public void getExerciseRelations() {

    Long dumbellPulloverExerciseId = 1L;
    ExerciseRelation.IdsSpecification idsSpecification = new IdsSpecification(Arrays.asList(dumbellPulloverExerciseId));

    List<ExerciseRelation> exerciseRelations = exerciseRelationEntityRepository.getExerciseRelations(idsSpecification);

    assertThat(exerciseRelations.size(), is(1));
    assertThat(exerciseRelations.get(0).getExerciseId(), is(dumbellPulloverExerciseId));
    assertThat(exerciseRelations.get(0).getRelatedMuscleIds().size(), is(3));
    assertThat(exerciseRelations.get(0).getRelatedMuscleIds().contains(1), is(true));
    assertThat(exerciseRelations.get(0).getRelatedMuscleIds().contains(4), is(true));
    assertThat(exerciseRelations.get(0).getRelatedMuscleIds().contains(8), is(true));
  }

  @Test
  public void getAllExerciseRelations() {

    List<ExerciseRelation> exerciseRelationList = exerciseRelationEntityRepository.getExerciseRelations(new MatchAllSpecification());

    assertThat(exerciseRelationList.isEmpty(), is(false));
  }
}