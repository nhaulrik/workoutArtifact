package com.workout.workoutArtifact.endpoint.graphqlservice;

import com.workout.workoutArtifact.endpoint.configuration.GraphQLSPQRConfig;
import com.workout.workoutArtifact.endpoint.dto.ExerciseDto;
import com.workout.workoutArtifact.endpoint.dto.MuscleDto;
import com.workout.workoutArtifact.endpoint.dto.MuscleDto.ExerciseIdSpecification;
import com.workout.workoutArtifact.endpoint.facade.MuscleFacade;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import com.workout.workoutArtifact.specification.MatchAllSpecification;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MuscleGraphQLService implements GraphQLSPQRConfig.GraphQLService {

  private final MuscleFacade muscleFacade;

  @GraphQLQuery(name = "muscles")
  public List<MuscleDto> getMuscles(
      @GraphQLArgument(name = "names") List<String> names
  ) {

    List<AbstractSpecification<MuscleDto>> muscleDtoSpecification = new ArrayList<>();
    if (names != null) { muscleDtoSpecification.add(new MuscleDto.NameSpecification(names)); }

    AbstractSpecification aggregatedSpecification = muscleDtoSpecification.stream().reduce(AbstractSpecification::and).orElse(new MatchAllSpecification());

    return muscleFacade.getMuscles(aggregatedSpecification);
  }

  @GraphQLQuery(name = "muscles")
  public List<MuscleDto> getMuscles(
      @GraphQLContext ExerciseDto exerciseDto,
      @GraphQLArgument(name = "names") List<String> names
  ) {

    List<AbstractSpecification<MuscleDto>> muscleDtoSpecification = new ArrayList<>();
    if (names != null) { muscleDtoSpecification.add(new MuscleDto.NameSpecification(names)); }
    if (exerciseDto.getId() != null) { muscleDtoSpecification.add(new ExerciseIdSpecification(exerciseDto.getId())); }

    AbstractSpecification aggregatedSpecification = muscleDtoSpecification.stream().reduce(AbstractSpecification::and).orElse(new MatchAllSpecification());

    return muscleFacade.getMuscles(aggregatedSpecification);
  }

}
