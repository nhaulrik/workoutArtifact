package com.workout.workoutArtifact.graphql.graphqlservice;


import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import com.workout.workoutArtifact.domain.specification.MatchAllSpecification;
import com.workout.workoutArtifact.domain.user.model.User;
import com.workout.workoutArtifact.graphql.dto.SessionDto;
import com.workout.workoutArtifact.graphql.dto.UserDto;
import com.workout.workoutArtifact.graphql.fetcher.UserFetcher;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserGraphQLService {

  private final UserFetcher userFetcher;

  @GraphQLQuery(name = "users")
  public List<UserDto> getUsers(
      @GraphQLArgument(name = "ids") List<String> ids,
      @GraphQLArgument(name = "firstNames") List<String> firstNames,
      @GraphQLArgument(name = "lastNames") List<String> lastNames
  ) {

    List<AbstractSpecification<User>> userDtoSpecifications = new ArrayList<>();

    if (ids != null) {
      userDtoSpecifications.add(new User.IdsSpecification(ids.stream().map(UUID::fromString).collect(Collectors.toList())));
    }
    if (firstNames != null) {
      userDtoSpecifications.add(new User.FirstNameSpecification(firstNames));
    }
    if (lastNames != null) {
      userDtoSpecifications.add(new User.LastNameSpecification(lastNames));
    }

    AbstractSpecification aggregatedSpecification = userDtoSpecifications.stream().reduce(AbstractSpecification::and).orElse(new MatchAllSpecification());

    return userFetcher.getUsers(aggregatedSpecification);
  }

  @GraphQLQuery(name = "user")
  public List<UserDto> getSessions(
      @GraphQLContext SessionDto sessionDto
  ) {
    AbstractSpecification idsSpecification = new User.IdsSpecification(Arrays.asList(sessionDto.getUserId()));
    return userFetcher.getUsers(idsSpecification);
  }

//  @GraphQLMutation(name = "createSession")
//  public List<UUID> createSession(
//      @GraphQLArgument(name = "userIds") List<String> userIds,
//      @GraphQLArgument(name = "date") String dateString) {
//
//    Assert.notNull(userIds, "userIds required");
//    Assert.notNull(dateString, "dateString required");
//
//    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
//    LocalDateTime parsedTime = LocalDateTime.parse(dateString, dateTimeFormatter);
//
//    List<UUID> userUUIDs = userIds.stream().map(UUID::fromString).collect(Collectors.toList());
//
//    return userFetcher.createSession(parsedTime, userUUIDs);
//  }

}
