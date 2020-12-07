//package com.workout.workoutArtifact.endpoint.mapper.dto;
//
//import com.workout.workoutArtifact.domain.user.model.User;
//import com.workout.workoutArtifact.endpoint.dto.UserDto;
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserDtoMapper {
//
//  public UserDto toDto(User user) {
//    return new UserDto(
//        user.getId(),
//        user.getFirstName(),
//        user.getLastName(),
//        user.getBirthday(),
//        user.getGender().name()
//    );
//  }
//
//  public User toDomainObject(UserDto userDto) {
//    return User.fromDto(
//        userDto.getId(),
//        userDto.getFirstName(),
//        userDto.getLastName(),
//        userDto.getBirthday(),
//        User.Gender.valueOf(userDto.getGender())
//    );
//  }
//}
