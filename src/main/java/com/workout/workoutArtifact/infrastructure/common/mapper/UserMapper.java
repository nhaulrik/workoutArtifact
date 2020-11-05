package com.workout.workoutArtifact.infrastructure.common.mapper;

import com.workout.workoutArtifact.domain.user.model.User;
import com.workout.workoutArtifact.endpoint.dto.UserDto;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {


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

  public UserDto toDto(User user) {
    return UserDto.builder()
        .birthday(user.getBirthDay())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .gender(UserDto.Gender.valueOf(user.getGender().name()))
        .id(user.getId())
        .build();
  }

  public User toDomainObject(UserDto userDto) {
    return User.builder()
        .firstName(userDto.getFirstName())
        .lastName(userDto.getLastName())
        .gender(User.Gender.valueOf(userDto.getGender().name()))
        .birthDay(userDto.getBirthday())
        .build();
  }
}
