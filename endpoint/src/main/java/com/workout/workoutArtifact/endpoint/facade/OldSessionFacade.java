//package com.workout.workoutArtifact.endpoint.facade;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class SessionFacade {
//
//  public List<UUID> addSessions(List<SessionDto> sessionDtos) {
//    List<UUID> sessionIds = sessionService.addSessions(sessionDtos.stream()
//        .map(sessionDtoMapper::toDomainObject)
//        .collect(Collectors.toList()));
//
//    return sessionIds;
//  }
//
//  public List<SessionDto> getSessions(AbstractSpecification<SessionDto> sessionDtoSpecification) {
//
//    Specification<Session> sessionSpecification = sessionDtoSpecificationMapper.toSessionSpecification(sessionDtoSpecification);
//
//    return sessionService.getSession(sessionSpecification).stream()
//        .filter(sessionSpecification::isSatisfiedBy)
//        .map(sessionDtoMapper::toDto)
//        .collect(Collectors.toList());
//  }
//
//  public Boolean deleteSessions(AbstractSpecification sessionIdSpecification) {
//
//    Specification<Session> sessionSpecification = sessionDtoSpecificationMapper.toSessionSpecification(sessionIdSpecification);
//
//    return sessionService.deleteSessions(sessionSpecification);
//  }
//
//  public Boolean postSession(UUID id, String location, String programme, String splitName, String time, UUID userId) {
//
//    Optional<Session> sessionOptional = sessionService.getSession(new IdsSpecification(Arrays.asList(id))).stream().findFirst();
//
//    if (sessionOptional.isPresent()) {
//      Session session = sessionOptional.get();
//      log.info(String.format("retrieved session with id: %s", id));
//      if (!Strings.isBlank(location) && !location.equals(session.getLocation())) {
//        session.changeLocation(location);
//        log.info(String.format("session with id: %s was updated with a location: %s", id, location));
//      }
//      if (!Strings.isBlank(programme) && !programme.equals(session.getProgramme())) {
//        session.changeProgramme(programme);
//        log.info(String.format("session with id: %s was updated with a programme: %s", id, programme));
//      }
//      if (!Strings.isBlank(splitName) && !splitName.equals(session.getSplitName())) {
//        session.changeSplitName(splitName);
//        log.info(String.format("session with id: %s was updated with a splitName: %s", id, splitName));
//      }
//      if (!Strings.isBlank(time)) {
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
//        LocalDateTime localDateTime = LocalDateTime.parse(time, dateTimeFormatter);
//
//        if (localDateTime.getDayOfYear() != session.getCreationDateTime().getDayOfYear() && localDateTime.getYear() != session.getCreationDateTime().getYear()) {
//          session.changeTime(localDateTime);
//          log.info(String.format("session with id: %s was updated with a new date: %s", id, localDateTime.toString()));
//        }
//      }
//      if (userId != null && userId.compareTo(session.getUserId()) != 0) {
//        session.changeUser(userId);
//        log.info(String.format("session with id: %s was updated with a new userId: %s", id, userId.toString()));
//      }
//      sessionService.addSessions(Arrays.asList(session));
//    }
//    return true;
//  }
//}
