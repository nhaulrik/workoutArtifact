package com.workout.workoutArtifact.domain.exercise.model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.workout.workoutArtifact.domain.exercise.model.Exercise.BodyPartsSpecification;
import com.workout.workoutArtifact.domain.exercise.model.Exercise.ExerciseIdSpecification;
import com.workout.workoutArtifact.domain.exercise.model.Exercise.MultiJointSpecification;
import com.workout.workoutArtifact.domain.exercise.model.Exercise.NameSpecification;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

public class ExerciseTest {

  @Test
  public void nameSpecification() {

    String name = "name";

    Exercise exercise = Exercise.builder()
        .muscleIds(new ArrayList<>())
        .name(name)
        .isMultiJoint(true)
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
        .muscleIds(new ArrayList<>())
        .name("name")
        .isMultiJoint(true)
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
        .muscleIds(new ArrayList<>())
        .name("name")
        .isMultiJoint(true)
        .bodyPart("bodypart")
        .build();

    Exercise.ExerciseIdSpecification exerciseIdSpecification = new ExerciseIdSpecification(exerciseId);

    assertThat(exerciseIdSpecification.isSatisfiedBy(exercise), is(true));
    assertThat(exerciseIdSpecification.getId(), is(exerciseId));
  }

  @Test
  public void multiJointSpecification() {

    Boolean isMultiJoint = true;

    Exercise exercise = Exercise.builder()
        .muscleIds(new ArrayList<>())
        .name("name")
        .isMultiJoint(isMultiJoint)
        .bodyPart("bodypart")
        .build();

    Exercise.MultiJointSpecification multiJointSpecification = new MultiJointSpecification(isMultiJoint);

    assertThat(multiJointSpecification.isSatisfiedBy(exercise), is(true));
    assertThat(multiJointSpecification.getMultiJoint(), is(isMultiJoint));
  }

}