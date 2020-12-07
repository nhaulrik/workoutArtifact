package com.workout.workoutArtifact.graphql.dto.mapper;

import com.workout.workoutArtifact.graphql.dto.UserDto;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.UserEntity;
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
