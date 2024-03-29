//package com.workout.workoutArtifact.graphql.fetcher.mapper.specification;
//
//import com.workout.workoutArtifact.specification.AbstractSpecification;
//import com.workout.workoutArtifact.specification.AndSpecification;
//import com.workout.workoutArtifact.specification.MatchAllSpecification;
//import com.workout.workoutArtifact.specification.NotSpecification;
//import com.workout.workoutArtifact.specification.OrSpecification;
//import com.workout.workoutArtifact.user.model.User;
//import com.workout.workoutArtifact.endpoint.dto.UserDto;
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserDtoSpecificationMapper {
//
//  public AbstractSpecification<User> toUserSpecification(AbstractSpecification<UserDto> userDtoSpecification) {
//
//    if (userDtoSpecification instanceof AndSpecification) {
//      return ((AndSpecification<UserDto>) userDtoSpecification).getSet().stream()
//          .map(this::toUserSpecification).reduce(AbstractSpecification::and)
//          .orElseThrow(() -> new RuntimeException("Missing specifications"));
//    } else if (userDtoSpecification instanceof OrSpecification) {
//      return ((OrSpecification<UserDto>) userDtoSpecification).getSet().stream()
//          .map(this::toUserSpecification).reduce(AbstractSpecification::or)
//          .orElseThrow(() -> new RuntimeException("Missing specifications"));
//    } else if (userDtoSpecification instanceof NotSpecification) {
//      return new NotSpecification<>(toUserSpecification(
//          ((NotSpecification<UserDto>) userDtoSpecification).getSpecification()));
//    } else if (userDtoSpecification instanceof UserDto.IdsSpecification) {
//      return new User.IdsSpecification(
//          ((UserDto.IdsSpecification) userDtoSpecification).getIds());
//    } else if (userDtoSpecification instanceof UserDto.FirstNameSpecification) {
//      return new User.FirstNameSpecification(((UserDto.FirstNameSpecification) userDtoSpecification).getFirstNames());
//    } else if (userDtoSpecification instanceof UserDto.LastNameSpecification) {
//      return new User.LastNameSpecification(((UserDto.LastNameSpecification) userDtoSpecification).getLastNames());
//    }
//    return new MatchAllSpecification();
//  }
//
//}
