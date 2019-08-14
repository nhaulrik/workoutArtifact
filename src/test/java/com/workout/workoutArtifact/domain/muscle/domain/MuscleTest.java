package com.workout.workoutArtifact.domain.muscle.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.workout.workoutArtifact.infrastructure.common.enums.BodyPartEnum;
import com.workout.workoutArtifact.infrastructure.common.enums.MuscleEnum;
import com.workout.workoutArtifact.domain.muscle.model.Muscle;
import com.workout.workoutArtifact.domain.muscle.model.Muscle.BodyPartSpecification;
import com.workout.workoutArtifact.domain.muscle.model.Muscle.NameSpecification;
import java.util.Arrays;
import org.junit.Test;

public class MuscleTest {

  @Test
  public void muscleNameSpecificationNotSatisfied() {
    String someName = "muscle1";

    Muscle.NameSpecification nameSpecification = new NameSpecification(Arrays.asList(someName));

    Muscle muscle = Muscle.builder()
        .name(MuscleEnum.CHEST.toString())
        .bodyPart(BodyPartEnum.CHEST)
        .build();

    assertThat(nameSpecification.isSatisfiedBy(muscle), is(false));
  }

  @Test
  public void muscleNameSpecificationIsSatisfied() {
    MuscleEnum chest = MuscleEnum.CHEST;

    Muscle.NameSpecification nameSpecification = new NameSpecification(Arrays.asList(chest.name()));

    Muscle muscle = Muscle.builder()
        .name(chest.toString())
        .bodyPart(BodyPartEnum.CHEST)
        .build();

    assertThat(nameSpecification.isSatisfiedBy(muscle), is(true));
  }

  @Test
  public void bodyPartSpecificationNotSatisfied() {
    String someName = "muscle1";

    Muscle.BodyPartSpecification bodyPartSpecification = new BodyPartSpecification(Arrays.asList(someName));

    Muscle muscle = Muscle.builder()
        .name(MuscleEnum.CHEST.toString())
        .bodyPart(BodyPartEnum.CHEST)
        .build();

    assertThat(bodyPartSpecification.isSatisfiedBy(muscle), is(false));
  }

  @Test
  public void bodyPartSpecificationIsSatisfied() {
    BodyPartEnum chest = BodyPartEnum.CHEST;

    Muscle.BodyPartSpecification bodyPartSpecification = new BodyPartSpecification(Arrays.asList(chest.name()));

    Muscle muscle = Muscle.builder()
        .name(MuscleEnum.CHEST.toString())
        .bodyPart(BodyPartEnum.CHEST)
        .build();

    assertThat(bodyPartSpecification.isSatisfiedBy(muscle), is(true));
  }
}