package com.workout.workoutArtifact.endpoint.graphqlservice;


import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import com.workout.workoutArtifact.domain.specification.MatchAllSpecification;
import com.workout.workoutArtifact.endpoint.dto.SessionDto;
import com.workout.workoutArtifact.endpoint.dto.UserDto;
import com.workout.workoutArtifact.endpoint.dto.UserDto.FirstNameSpecification;
import com.workout.workoutArtifact.endpoint.dto.UserDto.IdsSpecification;
import com.workout.workoutArtifact.endpoint.dto.UserDto.LastNameSpecification;
import com.workout.workoutArtifact.endpoint.facade.UserFacade;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserGraphQLService {

  private final UserFacade userFacade;

  @GraphQLQuery(name = "users")
  public List<UserDto> getUsers(
      @GraphQLArgument(name = "ids") List<String> ids,
      @GraphQLArgument(name = "firstNames") List<String> firstNames,
      @GraphQLArgument(name = "lastNames") List<String> lastNames
  ) {

    List<AbstractSpecification<UserDto>> userDtoSpecifications = new ArrayList<>();

    if (ids != null) {
      userDtoSpecifications.add(new IdsSpecification(ids.stream().map(UUID::fromString).collect(Collectors.toList())));
    }
    if (firstNames != null) {
      userDtoSpecifications.add(new FirstNameSpecification(firstNames));
    }
    if (lastNames != null) {
      userDtoSpecifications.add(new LastNameSpecification(lastNames));
    }

    AbstractSpecification aggregatedSpecification = userDtoSpecifications.stream().reduce(AbstractSpecification::and).orElse(new MatchAllSpecification());

    return userFacade.getUsers(aggregatedSpecification);
  }

  @GraphQLQuery(name = "users")
  public List<UserDto> getSessions(
      @GraphQLContext SessionDto sessionDto
  ) {
    AbstractSpecification idsSpecification = new UserDto.IdsSpecification(Arrays.asList(sessionDto.getUserId()));
    return userFacade.getUsers(idsSpecification);
  }

  @GraphQLMutation(name = "createSession")
  public List<UUID> createSession(
      @GraphQLArgument(name = "userIds") List<String> userIds,
      @GraphQLArgument(name = "date") String dateString) {

    Assert.notNull(userIds, "userIds required");
    Assert.notNull(dateString, "dateString required");

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    LocalDateTime parsedTime = LocalDateTime.parse(dateString, dateTimeFormatter);

    List<UUID> userUUIDs = userIds.stream().map(UUID::fromString).collect(Collectors.toList());

    return userFacade.createSession(parsedTime, userUUIDs);
  }

  // TODO: 15-11-2020 consider if this can be removed
//  @GraphQLMutation(name = "addUser")
//  public String addUser(
//      @GraphQLArgument(name = "firstName") String firstName,
//      @GraphQLArgument(name = "lastName") String lastName,
//      @GraphQLArgument(name = "birthday") String birthday,
//      @GraphQLArgument(name = "gender") String gender
//  ) {
//
//    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//    LocalDate parsedDate = LocalDate.parse(birthday, dateTimeFormatter);
//
//    UserDto userDto = new UserDto()
//        .id(UUID.randomUUID())
//        .birthday(parsedDate)
//        .firstName(firstName)
//        .lastName(lastName)
//        .gender(Gender.valueOf(gender.toUpperCase()))
//        .build();
//
//    return userFacade.addUser(userDto);
//  }
}
