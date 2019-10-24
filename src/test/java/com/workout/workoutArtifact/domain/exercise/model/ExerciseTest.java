package com.workout.workoutArtifact.domain.exercise.model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.workout.workoutArtifact.domain.exercise.model.Exercise.BodyPartsSpecification;
import com.workout.workoutArtifact.domain.exercise.model.Exercise.ExerciseIdSpecification;
import com.workout.workoutArtifact.domain.exercise.model.Exercise.IsCompoundSpecification;
import com.workout.workoutArtifact.domain.exercise.model.Exercise.NameSpecification;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

public class ExerciseTest {

  @Test
  public void nameSpecification() {

    String name = "name";

    Exercise exercise = Exercise.builder()
        .muscleRelations(new ArrayList<>())
        .name(name)
        .isCompound(true)
        .bodyPart("some_bodypart")
        .build();

    Exercise.NameSpecification nameSpecification = new NameSpecification(Arrays.asList(name));

    assertThat(nameSpecification.isSatisfiedBy(exercise), is(true));
    assertThat(nameSpecification.getNames(), is(Arrays.asList(name)));
  }

  @Test
  public void bodyPartSpecification() {

    String bodyPart = "bodyPart";

    Exercise exercise = Exercise.builder()
        .muscleRelations(new ArrayList<>())
        .name("name")
        .isCompound(true)
        .bodyPart(bodyPart)
        .build();

    Exercise.BodyPartsSpecification bodyPartsSpecification = new BodyPartsSpecification(Arrays.asList(bodyPart));

    assertThat(bodyPartsSpecification.isSatisfiedBy(exercise), is(true));
    assertThat(bodyPartsSpecification.getBodyParts(), is(Arrays.asList(bodyPart)));
  }

  @Test
  public void exerciseIdSpecification() {

    Long exerciseId = 1L;

    Exercise exercise = Exercise.builder()
        .id(exerciseId)
        .muscleRelations(new ArrayList<>())
        .name("name")
        .isCompound(true)
        .bodyPart("bodypart")
        .build();

    Exercise.ExerciseIdSpecification exerciseIdSpecification = new ExerciseIdSpecification(exerciseId);

    assertThat(exerciseIdSpecification.isSatisfiedBy(exercise), is(true));
    assertThat(exerciseIdSpecification.getId(), is(exerciseId));
  }

  @Test
  public void isCompoundSpecification() {

    Boolean isCompound = true;

    Exercise exercise = Exercise.builder()
        .muscleRelations(new ArrayList<>())
        .name("name")
        .isCompound(isCompound)
        .bodyPart("bodypart")
        .build();

    IsCompoundSpecification isCompoundSpecification = new IsCompoundSpecification(isCompound);

    assertThat(isCompoundSpecification.isSatisfiedBy(exercise), is(true));
    assertThat(isCompoundSpecification.getIsCompound(), is(isCompound));
  }

}