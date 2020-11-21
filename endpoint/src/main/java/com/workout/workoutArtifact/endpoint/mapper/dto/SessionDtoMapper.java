package com.workout.workoutArtifact.endpoint.mapper.dto;

import com.workout.workoutArtifact.domain.session.model.Session;
import com.workout.workoutArtifact.domain.user.model.User;
import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import com.workout.workoutArtifact.endpoint.dto.SessionDto;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionDtoMapper {

  public Session toDomainObject(SessionDto sessionDto) {
    Session session = new Session(sessionDto.getId());
    session.setCreationDateTime(sessionDto.getLocalDateTime());
    session.setProgramme(sessionDto.getProgramme());
    session.setSplitName(sessionDto.getSplitName());
    session.setLocation(sessionDto.getLocation());
    return session;
  }

  public SessionDto toDto(Session session) {
    return SessionDto.builder()
        .programme(session.getProgramme())
        .splitName(session.getSplitName())
        .id(session.getId())
        .location(session.getLocation())
        .localDateTime(session.getCreationDateTime())
        .workoutSetIds(session.getWorkoutSet().stream().map(WorkoutSet::getId).collect(Collectors.toList()))
        .userId(session.getUserId())
        .build();
  }
}
