package com.workout.workoutArtifact.domain.user.model;

import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.Getter;
import lombok.Value;
import org.springframework.util.Assert;

@Getter
public class User {

  private String firstName;
  private String lastName;
  private LocalDate birthday;
  private Gender gender;
  private UUID id;

  private User(
      UUID id,
      String firstName,
      String lastName,
      LocalDate birthDay,
      Gender gender
  ) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthday = birthDay;
    this.gender = gender;
  }

  public static User createNewUser(String firstName,
      String lastName,
      LocalDate birthDay,
      Gender gender) {
    return new User(
        UUID.randomUUID(),
        firstName,
        lastName,
        birthDay,
        gender
    );
  }

  public static User fromEntity(
      UUID id,
      String firstName,
      String lastName,
      LocalDate birthDay,
      Gender gender) {
    return new User(
        id,
        firstName,
        lastName,
        birthDay,
        gender
    );
  }

  public static User fromDto(
      UUID id,
      String firstName,
      String lastName,
      LocalDate birthDay,
      Gender gender
  ) {
    return new User(
        id,
        firstName,
        lastName,
        birthDay,
        gender
    );

  }

  public enum Gender {
    MALE,
    FEMALE
  }

  @Value
  public static class IdsSpecification extends AbstractSpecification<User> {

    private final List<UUID> ids;

    @Override
    public boolean isSatisfiedBy(User user) {
      return ids.contains(user.getId());
    }
  }

  @Value
  public static class FirstNameSpecification extends AbstractSpecification<User> {

    private final List<String> firstNames;

    @Override
    public boolean isSatisfiedBy(User user) {
      return firstNames.contains(user.getFirstName());
    }
  }

  @Value
  public static class LastNameSpecification extends AbstractSpecification<User> {

    private final List<String> lastNames;

    @Override
    public boolean isSatisfiedBy(User user) {
      return lastNames.contains(user.getLastName());
    }
  }
}
