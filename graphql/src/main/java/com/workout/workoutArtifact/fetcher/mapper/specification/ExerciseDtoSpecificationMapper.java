//package com.workout.workoutArtifact.graphql.fetcher.mapper.specification;
//
//import com.workout.workoutArtifact.specification.AbstractSpecification;
//import com.workout.workoutArtifact.specification.AndSpecification;
//import com.workout.workoutArtifact.specification.MatchAllSpecification;
//import com.workout.workoutArtifact.specification.NotSpecification;
//import com.workout.workoutArtifact.specification.OrSpecification;
//import com.workout.workoutArtifact.graphql.dto.ExerciseDto;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ExerciseDtoSpecificationMapper {
//
//  public AbstractSpecification<Exercise> toExerciseSpecification(AbstractSpecification<ExerciseDto> exerciseDtoSpecification) {
//
//    if (exerciseDtoSpecification instanceof AndSpecification) {
//      return ((AndSpecification<ExerciseDto>) exerciseDtoSpecification).getSet().stream().map(this::toExerciseSpecification).reduce(AbstractSpecification::and).orElseThrow(() -> new RuntimeException("Missing specifications"));
//    } else if (exerciseDtoSpecification instanceof OrSpecification) {
//      return ((OrSpecification<ExerciseDto>) exerciseDtoSpecification).getSet().stream().map(this::toExerciseSpecification).reduce(AbstractSpecification::or).orElseThrow(() -> new RuntimeException("Missing specifications"));
//    } else if (exerciseDtoSpecification instanceof NotSpecification) {
//      return new NotSpecification<>(toExerciseSpecification(((NotSpecification<ExerciseDto>) exerciseDtoSpecification).getSpecification()));
//    } else if (exerciseDtoSpecification instanceof ExerciseDto.NameSpecification) {
//      return new Exercise.NameSpecification(((ExerciseDto.NameSpecification) exerciseDtoSpecification).getNames());
//    } else if (exerciseDtoSpecification instanceof ExerciseDto.BodyPartsSpecification) {
//      return new Exercise.BodyPartsSpecification(((ExerciseDto.BodyPartsSpecification) exerciseDtoSpecification).getBodyParts());
//    } else if (exerciseDtoSpecification instanceof ExerciseDto.ExerciseIdSpecification) {
//      return new Exercise.ExerciseIdSpecification(((ExerciseDto.ExerciseIdSpecification) exerciseDtoSpecification).getExerciseId());
//    }
//    return new MatchAllSpecification();
//  }
//}
