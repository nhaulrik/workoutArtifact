package com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper;

import com.workout.workoutArtifact.domain.user.model.User;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.UserEntity;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEntityMapper {

  private final SessionEntityMapper sessionEntityMapper;

  public User toDomainObject(UserEntity userEntity) {
    return User.fromEntity(
        userEntity.getId(),
        userEntity.getFirstName(),
        userEntity.getLastName(),
        userEntity.getBirthDay(),
        User.Gender.valueOf(userEntity.getGender()),
        userEntity.getSessionEntities().stream().map(sessionEntityMapper::toDomainObject).collect(Collectors.toList())
    );
  }

  public UserEntity toEntity(User user) {
    UserEntity userEntity = new UserEntity();
    userEntity.setBirthDay(user.getBirthday());
    userEntity.setFirstName(user.getFirstName());
    userEntity.setLastName(user.getLastName());
    userEntity.setGender(user.getGender().name());
    userEntity.setId(user.getId());
    userEntity.setSessionEntities(user.getSessions().stream().map(sessionEntityMapper::toEntity).collect(Collectors.toSet()));

    userEntity.getSessionEntities().forEach(sessionEntity -> sessionEntity.setUserEntity(userEntity));
    return userEntity;
  }

}
