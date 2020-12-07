package com.workout.workoutArtifact.graphql.configuration;

import com.workout.workoutArtifact.graphql.graphqlservice.ExerciseGraphQLService;
import com.workout.workoutArtifact.graphql.graphqlservice.MuscleGraphQLService;
import com.workout.workoutArtifact.graphql.graphqlservice.SessionGraphQLService;
import com.workout.workoutArtifact.graphql.graphqlservice.UserGraphQLService;
import com.workout.workoutArtifact.graphql.graphqlservice.WorkoutExerciseGraphQLService;
import com.workout.workoutArtifact.graphql.graphqlservice.WorkoutSetGraphQLService;
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
      WorkoutExerciseGraphQLService workoutExerciseGraphQLService
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
            workoutExerciseGraphQLService
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

