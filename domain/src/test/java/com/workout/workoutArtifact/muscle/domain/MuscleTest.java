//package com.workout.workoutArtifact.muscle.domain;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.MatcherAssert.assertThat;
//
//import com.workout.workoutArtifact.common.enums.BodyPartEnum;
//import com.workout.workoutArtifact.common.enums.MuscleEnum;
//import com.workout.workoutArtifact.muscle.Muscle;
//import com.workout.workoutArtifact.muscle.Muscle.BodyPartSpecification;
//import com.workout.workoutArtifact.muscle.Muscle.NameSpecification;
//import java.util.Arrays;
//import org.junit.Test;
//
//public class MuscleTest {
//
//  @Test
//  public void muscleNameSpecificationNotSatisfied() {
//    String someName = "muscle1";
//
//    Muscle.NameSpecification nameSpecification = new NameSpecification(Arrays.asList(someName));
//
//    Muscle muscle = Muscle.builder()
//        .name(MuscleEnum.CHEST.toString())
//        .bodyPart(BodyPartEnum.CHEST.name())
//        .build();
//
//    assertThat(nameSpecification.isSatisfiedBy(muscle), is(false));
//  }
//
//  @Test
//  public void muscleNameSpecificationIsSatisfied() {
//    MuscleEnum chest = MuscleEnum.CHEST;
//
//    Muscle.NameSpecification nameSpecification = new NameSpecification(Arrays.asList(chest.name()));
//
//    Muscle muscle = Muscle.builder()
//        .name(chest.toString())
//        .bodyPart(BodyPartEnum.CHEST.name())
//        .build();
//
//    assertThat(nameSpecification.isSatisfiedBy(muscle), is(true));
//  }
//
//  @Test
//  public void bodyPartSpecificationNotSatisfied() {
//    String someName = "muscle1";
//
//    Muscle.BodyPartSpecification bodyPartSpecification = new BodyPartSpecification(Arrays.asList(someName));
//
//    Muscle muscle = Muscle.builder()
//        .name(MuscleEnum.CHEST.toString())
//        .bodyPart(BodyPartEnum.CHEST.name())
//        .build();
//
//    assertThat(bodyPartSpecification.isSatisfiedBy(muscle), is(false));
//  }
//
//  @Test
//  public void bodyPartSpecificationIsSatisfied() {
//    BodyPartEnum chest = BodyPartEnum.CHEST;
//
//    Muscle.BodyPartSpecification bodyPartSpecification = new BodyPartSpecification(Arrays.asList(chest.name()));
//
//    Muscle muscle = Muscle.builder()
//        .name(MuscleEnum.CHEST.toString())
//        .bodyPart(BodyPartEnum.CHEST.name())
//        .build();
//
//    assertThat(bodyPartSpecification.isSatisfiedBy(muscle), is(true));
//  }
//}