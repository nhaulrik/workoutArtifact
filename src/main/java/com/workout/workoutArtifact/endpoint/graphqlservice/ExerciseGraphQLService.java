package com.workout.workoutArtifact.endpoint.graphqlservice;

import com.workout.workoutArtifact.endpoint.configuration.GraphQLSPQRConfig;
import com.workout.workoutArtifact.endpoint.dto.ExerciseDto;
import com.workout.workoutArtifact.endpoint.dto.ExerciseDto.IsCompoundSpecification;
import com.workout.workoutArtifact.endpoint.dto.ExerciseDto.MuscleRelation;
import com.workout.workoutArtifact.endpoint.dto.WorkoutSetDto;
import com.workout.workoutArtifact.endpoint.facade.ExerciseFacade;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import com.workout.workoutArtifact.specification.MatchAllSpecification;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
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

  private final ExerciseFacade exerciseFacade;

  @GraphQLMutation(name = "addExercise")
  public boolean addExercise(
      @GraphQLArgument(name = "id") Long id,
      @GraphQLArgument(name = "name") String name,
      @GraphQLArgument(name = "bodyPart") String bodyPart,
      @GraphQLArgument(name = "isCompound") Boolean isCompound
  ) {

    ExerciseDto exerciseDto = ExerciseDto.builder()
        .id(id)
        .name(name)
        .bodyPart(bodyPart)
        .isCompound(isCompound)
        .build();

    exerciseFacade.addExercise(exerciseDto);
    return true;
  }

  @GraphQLQuery(name = "exercises")
  public List<ExerciseDto> getExercises(
      @GraphQLArgument(name = "names") List<String> names,
      @GraphQLArgument(name = "isCompound") Boolean isCompound,
      @GraphQLArgument(name = "bodyparts") List<String> bodyParts
  ) {

    List<AbstractSpecification<ExerciseDto>> exerciseDtoSpecifications = new ArrayList<>();
    if (names != null) { exerciseDtoSpecifications.add(new ExerciseDto.NameSpecification(names.stream().map(String::toUpperCase).collect(Collectors.toList()))); }
    if (isCompound != null) { exerciseDtoSpecifications.add(new IsCompoundSpecification(isCompound)); }
    if (bodyParts != null) { exerciseDtoSpecifications.add(new ExerciseDto.BodyPartsSpecification(bodyParts.stream().map(String::toUpperCase).collect(Collectors.toList()))); }

    AbstractSpecification aggregatedSpecification = exerciseDtoSpecifications.stream().reduce(AbstractSpecification::and).orElse(new MatchAllSpecification());

    return exerciseFacade.getExercises(aggregatedSpecification);
  }

  @GraphQLQuery(name = "exercises")
  public List<ExerciseDto> getExercises(
      @GraphQLContext WorkoutSetDto workoutSetDto,
      @GraphQLArgument(name = "names") List<String> names,
      @GraphQLArgument(name = "isCompound") Boolean isCompound,
      @GraphQLArgument(name = "bodyparts") List<String> bodyParts
  ) {

    List<AbstractSpecification<ExerciseDto>> exerciseDtoSpecifications = new ArrayList<>();
    if (workoutSetDto != null) { exerciseDtoSpecifications.add(new ExerciseDto.ExerciseIdSpecification(workoutSetDto.getExerciseId())); }
    if (names != null) { exerciseDtoSpecifications.add(new ExerciseDto.NameSpecification(names)); }
    if (isCompound!= null) { exerciseDtoSpecifications.add(new IsCompoundSpecification(isCompound)); }
    if (bodyParts != null) { exerciseDtoSpecifications.add(new ExerciseDto.BodyPartsSpecification(bodyParts)); }

    AbstractSpecification aggregatedSpecification = exerciseDtoSpecifications.stream().reduce(AbstractSpecification::and).orElse(new MatchAllSpecification());

    return exerciseFacade.getExercises(aggregatedSpecification);
  }


}
