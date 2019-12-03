package com.workout.workoutArtifact.endpoint.facade;

import com.workout.workoutArtifact.domain.user.model.User;
import com.workout.workoutArtifact.domain.user.service.UserService;
import com.workout.workoutArtifact.endpoint.dto.UserDto;
import com.workout.workoutArtifact.endpoint.mapper.UserDtoSpecificationMapper;
import com.workout.workoutArtifact.infrastructure.common.mapper.UserMapper;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import com.workout.workoutArtifact.specification.Specification;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {

  private final UserService userService;
  private final UserMapper userMapper;
  private final UserDtoSpecificationMapper userDtoSpecificationMapper;

  public List<UserDto> getUsers(AbstractSpecification<UserDto> userDtoSpecification) {
    Specification<User> userSpecification = userDtoSpecificationMapper.toUserSpecification(userDtoSpecification);

    return userService.getUsers(userSpecification).stream()
        .filter(userSpecification::isSatisfiedBy)
        .map(userMapper::toDto)
        .collect(Collectors.toList());
  }

  public String addUser(UserDto userDto) {

    User user = userMapper.toDomainObject(userDto);
    return userService.addUser(user);
  }
}
