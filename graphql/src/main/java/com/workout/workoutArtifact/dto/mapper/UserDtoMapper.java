package com.workout.workoutArtifact.dto.mapper;

import com.workout.workoutArtifact.dto.UserDto;
import com.workout.workoutArtifact.mysql.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {

  public UserDto toDto(UserEntity userEntity) {
    return new UserDto(
        userEntity.getId(),
        userEntity.getFirstName(),
        userEntity.getLastName(),
        userEntity.getBirthDay(),
        userEntity.getGender()
    );
  }

}
