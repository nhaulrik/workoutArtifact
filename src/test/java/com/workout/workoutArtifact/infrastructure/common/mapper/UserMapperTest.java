package com.workout.workoutArtifact.infrastructure.common.mapper;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.workout.workoutArtifact.domain.user.model.User;
import com.workout.workoutArtifact.domain.user.model.User.Gender;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.UserEntity;
import java.time.LocalDate;
import java.util.UUID;
import org.junit.Test;

public class UserMapperTest {

  private final UserMapper userMapper = new UserMapper();

  private final UUID id = UUID.randomUUID();
  private final String firstName = "first_name";
  private final String lastName = "last_name";
  private final String gender = "MALE";
  private final LocalDate birthday = LocalDate.now();


  @Test
  public void entityToDomain() {

    UserEntity userEntity = new UserEntity();
    userEntity.setId(id);
    userEntity.setFirstName(firstName);
    userEntity.setLastName(lastName);
    userEntity.setGender(gender);
    userEntity.setBirthDay(birthday);

    User user = userMapper.toDomainObject(userEntity);

    assertThat(user.getBirthDay(), is(birthday));
    assertThat(user.getFirstName(), is(firstName));
    assertThat(user.getLastName(), is(lastName));
    assertThat(user.getGender().name(), is(gender));
    assertThat(user.getId(), is(id));
  }

  @Test
  public void domainToEntity() {

    User user = User.builder()
        .birthDay(birthday)
        .firstName(firstName)
        .lastName(lastName)
        .gender(Gender.valueOf(gender))
        .id(id)
        .build();

    UserEntity userEntity = userMapper.toEntity(user);

    assertThat(user.getBirthDay(), is(userEntity.getBirthDay()));
    assertThat(user.getFirstName(), is(userEntity.getFirstName()));
    assertThat(user.getLastName(), is(userEntity.getLastName()));
    assertThat(user.getGender().name(), is(userEntity.getGender()));
    assertThat(user.getId(), is(userEntity.getId()));
  }
}