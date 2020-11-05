package com.workout.workoutArtifact.endpoint.dto;

import com.workout.workoutArtifact.specification.AbstractSpecification;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder
public class UserDto {

  private String firstName;
  private String lastName;
  private LocalDate birthday;
  private Gender gender;
  private Long id;

  public enum Gender{
    MALE,
    FEMALE
  }

  @Value
  public static class IdsSpecification extends AbstractSpecification<UserDto> {

    private final List<Long> ids;

    @Override
    public boolean isSatisfiedBy(UserDto userDto) {
      return ids.contains(userDto.getId());
    }
  }

  @Value
  public static class FirstNameSpecification extends AbstractSpecification<UserDto> {

    private final List<String> firstNames;

    @Override
    public boolean isSatisfiedBy(UserDto userDto) {
      return firstNames.contains(userDto.getFirstName());
    }
  }

  @Value
  public static class LastNameSpecification extends AbstractSpecification<UserDto> {

    private final List<String> lastNames;

    @Override
    public boolean isSatisfiedBy(UserDto userDto) {
      return lastNames.contains(userDto.getLastName());
    }
  }

}
