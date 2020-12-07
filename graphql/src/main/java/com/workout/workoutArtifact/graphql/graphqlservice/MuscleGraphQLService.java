//package com.workout.workoutArtifact.graphql.graphqlservice;
//
//import com.workout.workoutArtifact.graphql.dto.ExerciseDto;
//import com.workout.workoutArtifact.graphql.dto.MuscleDto;
//import com.workout.workoutArtifact.graphql.configuration.GraphQLSPQRConfig.GraphQLService;
//import io.leangen.graphql.annotations.GraphQLArgument;
//import io.leangen.graphql.annotations.GraphQLContext;
//import io.leangen.graphql.annotations.GraphQLMutation;
//import io.leangen.graphql.annotations.GraphQLQuery;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import specification.AbstractSpecification;
//import specification.MatchAllSpecification;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class MuscleGraphQLService implements GraphQLService {
//
//  private final MuscleFacade muscleFacade;
//
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
//    muscleFacade.addMuscles(Arrays.asList(muscleDto));
//    return true;
//  }
//
//
//  @GraphQLQuery(name = "muscles")
//  public List<MuscleDto> getMuscles(
//      @GraphQLArgument(name = "names") List<String> names
//  ) {
//
//    List<AbstractSpecification<MuscleDto>> muscleDtoSpecification = new ArrayList<>();
//    if (names != null) {
//      muscleDtoSpecification.add(new MuscleDto.NameSpecification(names));
//    }
//
//    AbstractSpecification aggregatedSpecification = muscleDtoSpecification.stream().reduce(AbstractSpecification::and).orElse(new MatchAllSpecification());
//
//    return muscleFacade.getMuscles(aggregatedSpecification);
//  }
//
//  @GraphQLQuery(name = "muscles")
//  public List<MuscleDto> getMuscles(
//      @GraphQLContext ExerciseDto exerciseDto,
//      @GraphQLArgument(name = "names") List<String> names
//  ) {
//
//    List<AbstractSpecification<MuscleDto>> muscleDtoSpecification = new ArrayList<>();
//    if (names != null) {
//      muscleDtoSpecification.add(new MuscleDto.NameSpecification(names));
//    }
//
//    AbstractSpecification aggregatedSpecification = muscleDtoSpecification.stream().reduce(AbstractSpecification::and).orElse(new MatchAllSpecification());
//
//    List<MuscleDto> muscleDtos = muscleFacade.getMuscles(aggregatedSpecification);
//    return muscleDtos;
//  }
//
//}
