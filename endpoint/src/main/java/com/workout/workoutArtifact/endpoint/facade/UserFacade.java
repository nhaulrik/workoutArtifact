//package com.workout.workoutArtifact.endpoint.facade;
//
//import com.workout.workoutArtifact.domain.specification.AbstractSpecification;
//import com.workout.workoutArtifact.domain.specification.Specification;
//import com.workout.workoutArtifact.domain.user.model.User;
//import com.workout.workoutArtifact.domain.user.service.UserService;
//import com.workout.workoutArtifact.endpoint.dto.UserDto;
//import com.workout.workoutArtifact.graphql.dto.mapper.UserDtoMapper;
//import com.workout.workoutArtifact.endpoint.mapper.specification.UserDtoSpecificationMapper;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.UUID;
//import java.util.stream.Collectors;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class UserFacade {
//
//  private final UserService userService;
//  private final UserDtoMapper userDtoMapper;
//  private final UserDtoSpecificationMapper userDtoSpecificationMapper;
//
//  public List<UserDto> getUsers(AbstractSpecification<UserDto> userDtoSpecification) {
//    Specification<User> userSpecification = userDtoSpecificationMapper.toUserSpecification(userDtoSpecification);
//
//    return userService.getUsers(userSpecification).stream()
//        .filter(userSpecification::isSatisfiedBy)
//        .map(userDtoMapper::toDto)
//        .collect(Collectors.toList());
//  }
//
//  public String addUser(UserDto userDto) {
//
//    User user = userDtoMapper.toDomainObject(userDto);
//    return userService.addUser(user);
//  }
//
//
//  public List<UUID> createSession(LocalDateTime parsedTime, List<UUID> userIds) {
//    return userService.createSession(parsedTime, userIds);
//  }
//}
