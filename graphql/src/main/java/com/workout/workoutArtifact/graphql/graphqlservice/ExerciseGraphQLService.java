package com.workout.workoutArtifact.graphql.graphqlservice;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import com.workout.workoutArtifact.domain.specification.MatchAllSpecification;
import com.workout.workoutArtifact.graphql.configuration.GraphQLSPQRConfig;
import com.workout.workoutArtifact.graphql.dto.ExerciseDto;
import com.workout.workoutArtifact.graphql.fetcher.ExerciseFetcher;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExerciseGraphQLService implements GraphQLSPQRConfig.GraphQLService {

  private final ExerciseFetcher exerciseFetcher;

  @GraphQLQuery(name = "exercises")
  public List<ExerciseDto> getExercises(
      @GraphQLArgument(name = "names") List<String> names,
      @GraphQLArgument(name = "isCompound") Boolean isCompound,
      @GraphQLArgument(name = "bodyparts") List<String> bodyParts
  ) {

    List<AbstractSpecification<Exercise>> exerciseSpecifications = new ArrayList<>();
    if (names != null) { exerciseSpecifications.add(new Exercise.NameSpecification(names.stream().map(String::toUpperCase).collect(Collectors.toList()))); }
    if (isCompound != null) { exerciseSpecifications.add(new Exercise.IsCompoundSpecification(isCompound)); }
    if (bodyParts != null) { exerciseSpecifications.add(new Exercise.BodyPartsSpecification(bodyParts.stream().map(String::toUpperCase).collect(Collectors.toList()))); }

    AbstractSpecification aggregatedSpecification = exerciseSpecifications.stream().reduce(AbstractSpecification::and).orElse(new MatchAllSpecification());

    return exerciseFetcher.getExercises(aggregatedSpecification);
  }

}
