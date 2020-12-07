//package com.workout.workoutArtifact.graphql.graphqlservice;
//
//import com.workout.workoutArtifact.graphql.dto.SessionDto;
//import com.workout.workoutArtifact.graphql.dto.WorkoutExerciseDto;
//import com.workout.workoutArtifact.graphql.dto.WorkoutSetDto;
//import com.workout.workoutArtifact.graphql.configuration.GraphQLSPQRConfig.GraphQLService;
//import io.leangen.graphql.annotations.GraphQLArgument;
//import io.leangen.graphql.annotations.GraphQLContext;
//import io.leangen.graphql.annotations.GraphQLMutation;
//import io.leangen.graphql.annotations.GraphQLQuery;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.UUID;
//import java.util.stream.Collectors;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import specification.AbstractSpecification;
//import specification.MatchAllSpecification;
//import specification.MatchNoneSpecification;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class WorkoutSetGraphQLService implements GraphQLService {
//
//  private final WorkoutSetFacade workoutSetFacade;
//
//  @GraphQLMutation(name = "addWorkoutSetList")
//  public List<UUID> addWorkoutSetList(
//      @GraphQLArgument(name = "workoutSet") List<WorkoutSetDto> workoutSetDtos
//  ) {
//    List<UUID> workoutSetIds = new ArrayList<>();
//    workoutSetDtos.forEach(workoutSetDto -> {
//      UUID workoutSetId = workoutSetFacade.addWorkoutSet(workoutSetDto);
//      workoutSetIds.add(workoutSetId);
//    });
//    return workoutSetIds;
//  }
//
//  @GraphQLMutation(name = "addWorkoutSet")
//  public UUID postWorkoutSet(
//      @GraphQLArgument(name = "id") UUID id,
//      @GraphQLArgument(name = "setNumber") Integer setNumber,
//      @GraphQLArgument(name = "weight") Double weight,
//      @GraphQLArgument(name = "repetitions") Integer repetitions,
//      @GraphQLArgument(name = "repetitionMaximum") Integer repetitionMaximum,
//      @GraphQLArgument(name = "single") Boolean single,
//      @GraphQLArgument(name = "workoutExerciseId") UUID workoutExerciseId,
//      @GraphQLArgument(name = "sessionId") UUID sessionId
//  ) {
//    return workoutSetFacade.postWorkoutSet(
//        id,
//        setNumber,
//        weight,
//        repetitions,
//        repetitionMaximum,
//        single,
//        workoutExerciseId,
//        sessionId
//    );
//  }
//
//  @GraphQLMutation(name = "deleteWorkoutSet")
//  public Boolean deleteWorkoutSet(
//      @GraphQLArgument(name = "id") UUID id
//  ) {
//    workoutSetFacade.deleteWorkoutSet(id);
//    return true;
//  }
//
//  @GraphQLQuery(name = "workoutSet")
//  public List<WorkoutSetDto> getWorkoutSet(
//      @GraphQLArgument(name = "ids") List<String> ids
//  ) {
//
//    List<AbstractSpecification<WorkoutSetDto>> workoutSetDtoSpecifications = new ArrayList<>();
//    if (ids != null) {
//      workoutSetDtoSpecifications.add(new WorkoutSetDto.IdsSpecification(ids.stream().map(UUID::fromString).collect(Collectors.toList())));
//    }
//
//    AbstractSpecification aggregatedSpecification = workoutSetDtoSpecifications.stream().reduce(AbstractSpecification::and).orElse(new MatchAllSpecification());
//
//    return workoutSetFacade.getWorkoutSets(aggregatedSpecification);
//  }
//
//
//  @GraphQLQuery(name = "workoutSet")
//  public List<WorkoutSetDto> getWorkoutSet(
//      @GraphQLArgument(name = "ids") List<String> ids,
//      @GraphQLContext SessionDto sessionDto
//  ) {
//    List<AbstractSpecification<WorkoutSetDto>> workoutSetDtoSpecifications = new ArrayList<>();
//    if (sessionDto != null) {
//      //workoutSetDtoSpecifications.add(new SessionIdsSpecification(Arrays.asList(sessionDto.getId())));
//    }
//    if (ids != null) {
//      workoutSetDtoSpecifications.add(new WorkoutSetDto.IdsSpecification(ids.stream().map(UUID::fromString).collect(Collectors.toList())));
//    }
//
//    AbstractSpecification aggregatedSpecification = workoutSetDtoSpecifications.stream().reduce(AbstractSpecification::and).orElse(new MatchNoneSpecification<>());
//
//    return workoutSetFacade.getWorkoutSets(aggregatedSpecification);
//  }
//
//  @GraphQLQuery(name = "workoutSet")
//  public List<WorkoutSetDto> getWorkoutSet(
//      @GraphQLArgument(name = "ids") List<String> ids,
//      @GraphQLContext WorkoutExerciseDto workoutExerciseDto
//  ) {
//    List<AbstractSpecification<WorkoutSetDto>> workoutSetDtoSpecifications = new ArrayList<>();
//    if (workoutExerciseDto != null) {
//      //workoutSetDtoSpecifications.add(new SessionIdsSpecification(Arrays.asList(sessionDto.getId())));
//    }
//    if (ids != null) {
//      workoutSetDtoSpecifications.add(new WorkoutSetDto.IdsSpecification(ids.stream().map(UUID::fromString).collect(Collectors.toList())));
//    }
//    if (workoutExerciseDto != null && workoutExerciseDto.getSessionId() != null) {
//      workoutSetDtoSpecifications.add(new WorkoutSetDto.WorkoutExerciseIdsSpecification(Arrays.asList(workoutExerciseDto.getId())));
//    }
//
//    AbstractSpecification aggregatedSpecification = workoutSetDtoSpecifications.stream().reduce(AbstractSpecification::and).orElse(new MatchNoneSpecification<>());
//
//    return workoutSetFacade.getWorkoutSets(aggregatedSpecification);
//  }
//
//}
