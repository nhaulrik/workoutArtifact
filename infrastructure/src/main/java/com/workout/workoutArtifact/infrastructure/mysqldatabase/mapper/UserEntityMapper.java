package com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper;

import com.workout.workoutArtifact.domain.user.model.User;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserEntityMapper {

  public User toDomainObject(UserEntity userEntity) {
    return User.builder()
        .birthDay(userEntity.getBirthDay())
        .firstName(userEntity.getFirstName())
        .lastName(userEntity.getLastName())
        .gender(User.Gender.valueOf(userEntity.getGender()))
        .id(userEntity.getId())
        .build();
  }

  public UserEntity toEntity(User user) {
    UserEntity userEntity = new UserEntity();
    userEntity.setBirthDay(user.getBirthDay());
    userEntity.setFirstName(user.getFirstName());
    userEntity.setLastName(user.getLastName());
    userEntity.setGender(user.getGender().name());
    userEntity.setId(user.getId());
    return userEntity;
  }

}
