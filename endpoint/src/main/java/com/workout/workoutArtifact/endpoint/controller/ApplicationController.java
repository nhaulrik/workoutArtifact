package com.workout.workoutArtifact.endpoint.controller;

import com.workout.workoutArtifact.endpoint.facade.ApplicationFacade;
import com.workout.workoutArtifact.endpoint.request.CreateSessionRequest;
import com.workout.workoutArtifact.endpoint.request.CreateSessionResponse;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/application/session")
@RequiredArgsConstructor
public class ApplicationController {

  private final ApplicationFacade applicationFacade;

  @PostMapping(consumes = "application/json", produces = "application/json")
  public CreateSessionResponse createSessions(@RequestBody List<CreateSessionRequest> createSessionRequests) {

    List<UUID> newSessionIds = applicationFacade.createSessions(createSessionRequests);
    return new CreateSessionResponse(newSessionIds);
  }

  @GetMapping("/{userId}/{date}")
  public void getBla() {

    boolean bla = false;

  }

//  @GetMapping("/{userId}/{date}")
//  public SessionDto getSession(@PathVariable UUID userId, @PathVariable String date) {
//
//    List<AbstractSpecification> specifications = new ArrayList<>();
//    LocalDateTime parsedTime = LocalDateTime.now();
//
//    if (userId != null) {
//      specifications.add(new SessionDto.UserIdSpecification(userId));
//    }
//    if (date != null) {
//      DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//      LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
//
//      parsedTime = localDate.atStartOfDay();
//      specifications.add(new SessionDto.DateTimeSpecification(parsedTime));
//    }
//
//    AbstractSpecification aggregatedSpecification = specifications.stream().reduce(AbstractSpecification::and).orElse(new MatchNoneSpecification());
//
//    List<SessionDto> sessionDtos = sessionFacade.getSessions(aggregatedSpecification);
//    if (sessionDtos.isEmpty()) {
//      SessionDto sessionDto = new SessionDto(
//          null,
//          Strings.EMPTY,
//          Strings.EMPTY,
//          Strings.EMPTY,
//          parsedTime,
//          new ArrayList<>(),
//          null
//      );
//      return sessionDto;
//
//    } else if (sessionDtos.size() > 1) {
//      throw new RuntimeException(String.format("multiple sessions found for date: %s and userId: %s", date, userId));
//    } else {
//      return sessionDtos.get(0);
//    }
//  }

}
