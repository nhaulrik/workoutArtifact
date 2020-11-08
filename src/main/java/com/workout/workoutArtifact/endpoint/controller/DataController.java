package com.workout.workoutArtifact.endpoint.controller;

import com.workout.workoutArtifact.endpoint.dto.SessionDto;
import com.workout.workoutArtifact.endpoint.facade.SessionFacade;
import com.workout.workoutArtifact.specification.AbstractSpecification;
import com.workout.workoutArtifact.specification.MatchNoneSpecification;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/session")
@RequiredArgsConstructor
public class DataController {

  private final SessionFacade sessionFacade;

  @GetMapping("/{userId}/{date}")
  public SessionDto getSession(@PathVariable UUID userId, @PathVariable String date) {

    List<AbstractSpecification> specifications = new ArrayList<>();
    LocalDateTime parsedTime = LocalDateTime.now();

    if (userId != null) {
      specifications.add(new SessionDto.UserIdSpecification(userId));
    }
    if (date != null) {
      DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
      LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);

      parsedTime = localDate.atStartOfDay();
      specifications.add(new SessionDto.DateTimeSpecification(parsedTime));
    }

    AbstractSpecification aggregatedSpecification = specifications.stream().reduce(AbstractSpecification::and).orElse(new MatchNoneSpecification());

    List<SessionDto> sessionDtos = sessionFacade.getSessions(aggregatedSpecification);
    if (sessionDtos.isEmpty()) {
      SessionDto sessionDto = new SessionDto(
          null,
          Strings.EMPTY,
          Strings.EMPTY,
          Strings.EMPTY,
          parsedTime,
          new ArrayList<>(),
          userId
      );
      return sessionDto;

    } else if (sessionDtos.size() > 1) {
      throw new RuntimeException(String.format("multiple sessions found for date: %s and userId: %s", date, userId));
    } else {
      return sessionDtos.get(0);
    }
  }

}
