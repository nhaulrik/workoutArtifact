package com.workout.workoutArtifact.endpoint.graphqlservice;


import com.workout.workoutArtifact.endpoint.dto.UserDto;
import com.workout.workoutArtifact.endpoint.dto.UserDto.FirstNameSpecification;
import com.workout.workoutArtifact.endpoint.dto.UserDto.Gender;
import com.workout.workoutArtifact.endpoint.dto.UserDto.IdsSpecification;
import com.workout.workoutArtifact.endpoint.dto.UserDto.LastNameSpecification;
import com.workout.workoutArtifact.endpoint.facade.UserFacade;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import com.workout.workoutArtifact.specification.MatchAllSpecification;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserGraphQLService {

  private final UserFacade userFacade;

  @GraphQLQuery(name = "users")
  public List<UserDto> getUsers(
      @GraphQLArgument(name = "ids") List<Long> ids,
      @GraphQLArgument(name = "firstNames") List<String> firstNames,
      @GraphQLArgument(name = "lastNames") List<String> lastNames
  ) {

    List<AbstractSpecification<UserDto>> userDtoSpecifications = new ArrayList<>();


    if (ids != null) { userDtoSpecifications.add(new IdsSpecification(ids)); }
    if (firstNames != null) { userDtoSpecifications.add(new FirstNameSpecification(firstNames)); }
    if (lastNames != null) { userDtoSpecifications.add(new LastNameSpecification(lastNames)); }

    AbstractSpecification aggregatedSpecification = userDtoSpecifications.stream().reduce(AbstractSpecification::and).orElse(new MatchAllSpecification());

    return userFacade.getUsers(aggregatedSpecification);
  }

  @GraphQLMutation(name = "addUser")
  public String addUser(
      @GraphQLArgument(name = "firstName") String firstName,
      @GraphQLArgument(name = "lastName") String lastName,
      @GraphQLArgument(name = "birthday") String birthday,
      @GraphQLArgument(name = "gender") String gender
  ) {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate parsedDate = LocalDate.parse(birthday, dateTimeFormatter);

    UserDto userDto = UserDto.builder()
        .birthday(parsedDate)
        .firstName(firstName)
        .lastName(lastName)
        .gender(Gender.valueOf(gender.toUpperCase()))
        .build();

    return userFacade.addUser(userDto);
  }
}
