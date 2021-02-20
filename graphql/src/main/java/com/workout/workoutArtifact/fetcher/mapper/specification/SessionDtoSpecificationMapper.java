//package com.workout.workoutArtifact.graphql.fetcher.mapper.specification;
//
//import com.workout.workoutArtifact.session.Session;
//import com.workout.workoutArtifact.specification.AbstractSpecification;
//import com.workout.workoutArtifact.specification.AndSpecification;
//import com.workout.workoutArtifact.specification.MatchAllSpecification;
//import com.workout.workoutArtifact.specification.NotSpecification;
//import com.workout.workoutArtifact.specification.OrSpecification;
//import com.workout.workoutArtifact.endpoint.dto.SessionDto;
//import org.springframework.stereotype.Component;
//
//@Component
//public class SessionDtoSpecificationMapper {
//
//  public AbstractSpecification<Session> toSessionSpecification(
//      AbstractSpecification<SessionDto> sessionDtoSpecification) {
//
//    if (sessionDtoSpecification instanceof AndSpecification) {
//      return ((AndSpecification<SessionDto>) sessionDtoSpecification).getSet().stream()
//          .map(this::toSessionSpecification).reduce(AbstractSpecification::and)
//          .orElseThrow(() -> new RuntimeException("Missing specifications"));
//    } else if (sessionDtoSpecification instanceof OrSpecification) {
//      return ((OrSpecification<SessionDto>) sessionDtoSpecification).getSet().stream()
//          .map(this::toSessionSpecification).reduce(AbstractSpecification::or)
//          .orElseThrow(() -> new RuntimeException("Missing specifications"));
//    } else if (sessionDtoSpecification instanceof NotSpecification) {
//      return new NotSpecification<>(toSessionSpecification(
//          ((NotSpecification<SessionDto>) sessionDtoSpecification).getSpecification()));
//    } else if (sessionDtoSpecification instanceof SessionDto.IdsSpecification) {
//      return new Session.IdsSpecification(
//          ((SessionDto.IdsSpecification) sessionDtoSpecification).getIds());
//    } else if (sessionDtoSpecification instanceof SessionDto.LocationsSpecification) {
//      return new Session.LocationsSpecification(
//          ((SessionDto.LocationsSpecification) sessionDtoSpecification).getLocations());
//    } else if (sessionDtoSpecification instanceof SessionDto.ProgrammeSpecification) {
//      return new Session.ProgrammeSpecification(
//          ((SessionDto.ProgrammeSpecification) sessionDtoSpecification).getProgramme());
//    } else if (sessionDtoSpecification instanceof SessionDto.SplitNameSpecification) {
//      return new Session.SplitNameSpecification(
//          ((SessionDto.SplitNameSpecification) sessionDtoSpecification).getSplitName());
//    } else if (sessionDtoSpecification instanceof SessionDto.DateTimeSpecification) {
//      return new Session.DateTimeSpecification(
//          ((SessionDto.DateTimeSpecification) sessionDtoSpecification).getLocalDateTime());
//    }
//    return new MatchAllSpecification();
//  }
//}
