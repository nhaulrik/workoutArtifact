package com.workout.workoutArtifact.endpoint.graphqlservice;

import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import com.workout.workoutArtifact.domain.specification.MatchAllSpecification;
import com.workout.workoutArtifact.endpoint.configuration.GraphQLSPQRConfig.GraphQLService;
import com.workout.workoutArtifact.endpoint.dto.ExerciseDto;
import com.workout.workoutArtifact.endpoint.dto.MuscleDto;
import com.workout.workoutArtifact.endpoint.facade.MuscleFacade;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MuscleGraphQLService implements GraphQLService {

  private final MuscleFacade muscleFacade;

  @GraphQLMutation(name = "addMuscle")
  public Boolean addMuscle(
      @GraphQLArgument(name = "name") String name,
      @GraphQLArgument(name = "bodyPart") String bodyPart) {

    MuscleDto muscleDto = MuscleDto.builder()
        .name(name)
        .bodyPart(bodyPart)
        .build();

    muscleFacade.addMuscles(Arrays.asList(muscleDto));
    return true;
  }


  @GraphQLQuery(name = "muscles")
  public List<MuscleDto> getMuscles(
      @GraphQLArgument(name = "names") List<String> names
  ) {

    List<AbstractSpecification<MuscleDto>> muscleDtoSpecification = new ArrayList<>();
    if (names != null) {
      muscleDtoSpecification.add(new MuscleDto.NameSpecification(names));
    }

    AbstractSpecification aggregatedSpecification = muscleDtoSpecification.stream().reduce(AbstractSpecification::and).orElse(new MatchAllSpecification());

    return muscleFacade.getMuscles(aggregatedSpecification);
  }

  @GraphQLQuery(name = "muscles")
  public List<MuscleDto> getMuscles(
      @GraphQLContext ExerciseDto exerciseDto,
      @GraphQLArgument(name = "names") List<String> names
  ) {

    List<AbstractSpecification<MuscleDto>> muscleDtoSpecification = new ArrayList<>();
    if (names != null) {
      muscleDtoSpecification.add(new MuscleDto.NameSpecification(names));
    }

    AbstractSpecification aggregatedSpecification = muscleDtoSpecification.stream().reduce(AbstractSpecification::and).orElse(new MatchAllSpecification());

    List<MuscleDto> muscleDtos = muscleFacade.getMuscles(aggregatedSpecification);
    return muscleDtos;
  }

}
