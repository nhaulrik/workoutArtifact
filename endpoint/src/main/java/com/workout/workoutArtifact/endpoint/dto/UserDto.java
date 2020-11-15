package com.workout.workoutArtifact.endpoint.dto;

import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder
public class UserDto {

  private UUID id;
  private String firstName;
  private String lastName;
  private LocalDate birthday;
  private Gender gender;

  public enum Gender {
    MALE,
    FEMALE
  }

  @Value
  public static class IdsSpecification extends AbstractSpecification<UserDto> {

    private final List<UUID> ids;

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
