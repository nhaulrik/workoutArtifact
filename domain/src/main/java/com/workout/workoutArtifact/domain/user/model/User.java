package com.workout.workoutArtifact.domain.user.model;

import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Value;
import org.apache.logging.log4j.util.Strings;
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

  public void updateFirstName(String firstName) {
    Assert.isTrue(!Strings.isBlank(firstName), "firstName is required");
    this.firstName = firstName;
  }

  public void updateLastName(String lastName) {
    Assert.isTrue(!Strings.isBlank(lastName), "lastName is required");
    this.lastName = lastName;
  }

  public void updateBirthday(LocalDate birthday) {
    Assert.notNull(birthday, "birthday is required");
    this.birthday = birthday;
  }

  public void updateGender(Gender gender) {
    Assert.notNull(gender, "gender is required");
    this.gender = gender;
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
