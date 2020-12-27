package com.workout.workoutArtifact.graphql.graphqlservice;

import com.workout.workoutArtifact.domain.muscle.model.Muscle;
import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import com.workout.workoutArtifact.domain.specification.MatchAllSpecification;
import com.workout.workoutArtifact.domain.specification.MatchNoneSpecification;
import com.workout.workoutArtifact.graphql.configuration.GraphQLSPQRConfig.GraphQLService;
import com.workout.workoutArtifact.graphql.dto.ExerciseDto;
import com.workout.workoutArtifact.graphql.dto.MuscleDto;
import com.workout.workoutArtifact.graphql.fetcher.MuscleFetcher;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MuscleGraphQLService implements GraphQLService {

  private final MuscleFetcher muscleFetcher;

//  @GraphQLMutation(name = "addMuscle")
//  public Boolean addMuscle(
//      @GraphQLArgument(name = "name") String name,
//      @GraphQLArgument(name = "bodyPart") String bodyPart) {
//
//    MuscleDto muscleDto = MuscleDto.builder()
//        .name(name)
//        .bodyPart(bodyPart)
//        .build();
//
//    muscleFetcher.addMuscles(Arrays.asList(muscleDto));
//    return true;
//  }


  @GraphQLQuery(name = "muscles")
  public List<MuscleDto> getMuscles(
      @GraphQLArgument(name = "ids") List<UUID> ids,
      @GraphQLArgument(name = "names") List<String> names,
      @GraphQLArgument(name = "bodyParts") List<String> bodyParts
  ) {

    List<AbstractSpecification<Muscle>> muscleSpecification = new ArrayList<>();
    if (ids != null) { muscleSpecification.add(new Muscle.IdsSpecification(ids));}
    if (names != null) { muscleSpecification.add(new Muscle.NameSpecification(names));}
    if (bodyParts != null) { muscleSpecification.add(new Muscle.BodyPartSpecification(names));}

    AbstractSpecification aggregatedSpecification = muscleSpecification.stream().reduce(AbstractSpecification::and).orElse(new MatchAllSpecification());

    return muscleFetcher.getMuscles(aggregatedSpecification);
  }

  @GraphQLQuery(name = "muscles")
  public List<MuscleDto> getMuscles(
      @GraphQLContext ExerciseDto exerciseDto,
      @GraphQLArgument(name = "ids") List<UUID> ids,
      @GraphQLArgument(name = "names") List<String> names,
      @GraphQLArgument(name = "bodyParts") List<String> bodyParts
  ) {

    List<AbstractSpecification<Muscle>> muscleSpecifications = new ArrayList<>();
    if (ids != null) { muscleSpecifications.add(new Muscle.IdsSpecification(ids));}
    if (names != null) { muscleSpecifications.add(new Muscle.NameSpecification(names));}
    if (bodyParts != null) { muscleSpecifications.add(new Muscle.BodyPartSpecification(names));}
    if (exerciseDto != null) { muscleSpecifications.add(new Muscle.IdsSpecification(exerciseDto.getMuscleIds()));}

    AbstractSpecification aggregatedSpecification = muscleSpecifications.stream().reduce(AbstractSpecification::and).orElse(new MatchNoneSpecification());

    List<MuscleDto> muscleDtos = muscleFetcher.getMuscles(aggregatedSpecification);
    return muscleDtos;
  }

}
