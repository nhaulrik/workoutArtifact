package com.workout.workoutArtifact.endpoint.mapper.dto;

import com.workout.workoutArtifact.domain.user.model.User;
import com.workout.workoutArtifact.endpoint.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {

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
