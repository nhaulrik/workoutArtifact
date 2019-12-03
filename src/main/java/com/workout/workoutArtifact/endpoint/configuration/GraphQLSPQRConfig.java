package com.workout.workoutArtifact.endpoint.configuration;

import com.workout.workoutArtifact.endpoint.graphqlservice.ExerciseGraphQLService;
import com.workout.workoutArtifact.endpoint.graphqlservice.MuscleGraphQLService;
import com.workout.workoutArtifact.endpoint.graphqlservice.SessionGraphQLService;
import com.workout.workoutArtifact.endpoint.graphqlservice.UserGraphQLService;
import com.workout.workoutArtifact.endpoint.graphqlservice.WorkoutSetGraphQLService;
import graphql.GraphQL;
import graphql.analysis.MaxQueryComplexityInstrumentation;
import graphql.analysis.MaxQueryDepthInstrumentation;
import graphql.execution.batched.BatchedExecutionStrategy;
import graphql.execution.instrumentation.ChainedInstrumentation;
import graphql.execution.instrumentation.Instrumentation;
import graphql.execution.instrumentation.dataloader.DataLoaderDispatcherInstrumentation;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.query.AnnotatedResolverBuilder;
import io.leangen.graphql.metadata.strategy.query.PublicResolverBuilder;
import io.leangen.graphql.metadata.strategy.value.jackson.JacksonValueMapperFactory;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.beans.factory.annotation.Autowired;
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
      UserGraphQLService userGraphQLService
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
            userGraphQLService
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

