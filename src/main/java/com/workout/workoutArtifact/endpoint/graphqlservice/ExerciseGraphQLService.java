package com.workout.workoutArtifact.endpoint.graphqlservice;

import com.workout.workoutArtifact.endpoint.configuration.GraphQLSPQRConfig;
import com.workout.workoutArtifact.endpoint.dto.ExerciseDto;
import com.workout.workoutArtifact.endpoint.facade.ExerciseFacade;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import com.workout.workoutArtifact.specification.MatchAllSpecification;
import com.workout.workoutArtifact.specification.MatchNoneSpecification;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExerciseGraphQLService implements GraphQLSPQRConfig.GraphQLService{

  private final ExerciseFacade exerciseFacade;

  @GraphQLQuery(name = "exercises")
  public List<ExerciseDto> getExercises(
      @GraphQLArgument(name = "names") List<String> names,
      @GraphQLArgument(name = "types") List<String> types,
      @GraphQLArgument(name = "bodyparts") List<String> bodyParts
  ) {

    List<AbstractSpecification<ExerciseDto>> exerciseDtoSpecifications = new ArrayList<>();
    if (names != null) { exerciseDtoSpecifications.add(new ExerciseDto.NameSpecification(names)); }
    if (types != null) { exerciseDtoSpecifications.add(new ExerciseDto.TypesSpecification(types)); }
    if (bodyParts != null) { exerciseDtoSpecifications.add(new ExerciseDto.BodyPartsSpecification(bodyParts)); }

    AbstractSpecification aggregatedSpecification = exerciseDtoSpecifications.stream().reduce(AbstractSpecification::and).orElse(new MatchAllSpecification());

    return exerciseFacade.getExercises(aggregatedSpecification);
  }


}
