//package com.workout.workoutArtifact.endpoint.mapper;
//
//import static org.hamcrest.CoreMatchers.instanceOf;
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.MatcherAssert.assertThat;
//
//import com.workout.workoutArtifact.domain.exercise.model.Exercise;
//import com.workout.workoutArtifact.domain.exercise.model.Exercise.BodyPartsSpecification;
//import com.workout.workoutArtifact.domain.exercise.model.Exercise.ExerciseIdSpecification;
//import com.workout.workoutArtifact.domain.exercise.model.Exercise.NameSpecification;
//import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
//import com.workout.workoutArtifact.domain.specification.AndSpecification;
//import com.workout.workoutArtifact.endpoint.dto.ExerciseDto;
//import com.workout.workoutArtifact.endpoint.mapper.specification.ExerciseDtoSpecificationMapper;
//import java.util.Arrays;
//import java.util.UUID;
//import org.junit.Test;
//
//public class ExerciseDtoSpecificationMapperTest {
//
//  private final ExerciseDtoSpecificationMapper exerciseDtoSpecificationMapper = new ExerciseDtoSpecificationMapper();
//
//  @Test
//  public void mapsNameSpecification() {
//
//    String someName = "some_name";
//
//    AbstractSpecification<Exercise> nameSpecification = exerciseDtoSpecificationMapper.toExerciseSpecification(new ExerciseDto.NameSpecification(
//        Arrays.asList(someName)));
//
//    assertThat(nameSpecification, is(instanceOf(Exercise.NameSpecification.class)));
//    assertThat(((NameSpecification) nameSpecification).getNames().get(0), is(someName));
//  }
//
//  @Test
//  public void mapsExerciseIdSpecification() {
//
//    UUID exerciseId = UUID.randomUUID();
//
//    AbstractSpecification<Exercise> exerciseIdSpecification = exerciseDtoSpecificationMapper.toExerciseSpecification(new ExerciseDto.ExerciseIdSpecification(exerciseId));
//
//    assertThat(exerciseIdSpecification, is(instanceOf(Exercise.ExerciseIdSpecification.class)));
//    assertThat(((ExerciseIdSpecification) exerciseIdSpecification).getId(), is(exerciseId));
//  }
//
//
//  @Test
//  public void mapsBodyPartsSpecification() {
//
//    String someBodyPart = "some_body_part";
//
//    AbstractSpecification<Exercise> bodyPartSpecification = exerciseDtoSpecificationMapper.toExerciseSpecification(new ExerciseDto.BodyPartsSpecification(
//        Arrays.asList(someBodyPart)));
//
//    assertThat(bodyPartSpecification, is(instanceOf(Exercise.BodyPartsSpecification.class)));
//    assertThat(((BodyPartsSpecification) bodyPartSpecification).getBodyParts().get(0), is(someBodyPart));
//  }
//
//
//  @Test
//  public void mapsAndSpecification() {
//
//    String someName = "some_name";
//    String someBodyPart = "chest";
//
//    AbstractSpecification<ExerciseDto> nameSpecification = new ExerciseDto.NameSpecification(Arrays.asList(someName));
//    AbstractSpecification<ExerciseDto> bodyPartSpecification = new ExerciseDto.BodyPartsSpecification(Arrays.asList(someBodyPart));
//
//    AndSpecification andSpecification = new AndSpecification(nameSpecification, bodyPartSpecification);
//
//    AbstractSpecification domainSpecification = exerciseDtoSpecificationMapper.toExerciseSpecification(andSpecification);
//
//    assertThat(domainSpecification, is(instanceOf(AndSpecification.class)));
//  }
//
//  // TODO: 24-08-2019 Class under test is not fully covered
//}