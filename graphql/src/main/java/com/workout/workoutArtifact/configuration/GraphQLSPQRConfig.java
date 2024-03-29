package com.workout.workoutArtifact.configuration;

import com.workout.workoutArtifact.graphqlservice.BodyMeasurementGraphQLService;
import com.workout.workoutArtifact.graphqlservice.ExerciseGraphQLService;
import com.workout.workoutArtifact.graphqlservice.MuscleGraphQLService;
import com.workout.workoutArtifact.graphqlservice.ProgrammeGraphQLService;
import com.workout.workoutArtifact.graphqlservice.SessionGraphQLService;
import com.workout.workoutArtifact.graphqlservice.UserGraphQLService;
import com.workout.workoutArtifact.graphqlservice.WorkoutExerciseGraphQLService;
import com.workout.workoutArtifact.graphqlservice.WorkoutSetGraphQLService;
import com.workout.workoutArtifact.intelligence.session.SessionIntelligenceGraphQLService;
import com.workout.workoutArtifact.intelligence.workoutexercise.ExerciseBasedWorkoutExerciseIntelligenceGraphQLService;
import com.workout.workoutArtifact.intelligence.workoutexercise.SessionBasedWorkoutExerciseIntelligenceGraphQLService;
import graphql.GraphQL;
import graphql.analysis.MaxQueryComplexityInstrumentation;
import graphql.analysis.MaxQueryDepthInstrumentation;
import graphql.execution.batched.BatchedExecutionStrategy;
import graphql.execution.instrumentation.ChainedInstrumentation;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.query.AnnotatedResolverBuilder;
import io.leangen.graphql.metadata.strategy.query.PublicResolverBuilder;
import io.leangen.graphql.metadata.strategy.value.jackson.JacksonValueMapperFactory;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GraphQLSPQRConfig {

  public interface GraphQLService {}

  @Bean
  public GraphQL graphQL(
      ExerciseGraphQLService exerciseGraphQLService,
      MuscleGraphQLService muscleGraphQLService,
      WorkoutSetGraphQLService workoutSetGraphQLService,
      SessionGraphQLService sessionGraphQLService,
      UserGraphQLService userGraphQLService,
      WorkoutExerciseGraphQLService workoutExerciseGraphQLService,
      BodyMeasurementGraphQLService bodyMeasurementGraphQLService,
      SessionBasedWorkoutExerciseIntelligenceGraphQLService sessionBasedWorkoutExerciseIntelligenceGraphQLService,
      ExerciseBasedWorkoutExerciseIntelligenceGraphQLService exerciseBasedWorkoutExerciseIntelligenceGraphQLService,
      ProgrammeGraphQLService programmeGraphQLService,
      SessionIntelligenceGraphQLService sessionIntelligenceGraphQLService
  ) {
    GraphQLSchema schema = new GraphQLSchemaGenerator()
        .withResolverBuilders(
            new AnnotatedResolverBuilder(),
            new PublicResolverBuilder("com.workout.workoutartifact"))
        .withOperationsFromSingletons(
            exerciseGraphQLService,
            muscleGraphQLService,
            workoutSetGraphQLService,
            sessionGraphQLService,
            userGraphQLService,
            workoutExerciseGraphQLService,
            bodyMeasurementGraphQLService,
            sessionBasedWorkoutExerciseIntelligenceGraphQLService,
            exerciseBasedWorkoutExerciseIntelligenceGraphQLService,
            programmeGraphQLService,
            sessionIntelligenceGraphQLService
        )
        .withValueMapperFactory(new JacksonValueMapperFactory())
        .generate();
    return GraphQL.newGraphQL(schema)
        .queryExecutionStrategy(new BatchedExecutionStrategy())
        .instrumentation(new ChainedInstrumentation(Arrays.asList(
            new MaxQueryComplexityInstrumentation(200),
            new MaxQueryDepthInstrumentation(20)
        )))
        .build();
  }

}

