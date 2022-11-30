package com.workout.workoutArtifact.dto.mapper;

import com.workout.workoutArtifact.dto.SessionDto;
import com.workout.workoutArtifact.mysql.entity.SessionEntity;
import com.workout.workoutArtifact.session.Sport;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class SessionDtoMapper {

  public SessionDto toDto(SessionEntity sessionEntity) {
    return new SessionDto(
        sessionEntity.getId(),
        sessionEntity.getLocation(),
        sessionEntity.getProgramme(),
        sessionEntity.getSplitName(),
        sessionEntity.getCreationDateTime(),
        sessionEntity.getUserEntity().getId(),
        sessionEntity.getDuration() != null ? sessionEntity.getDuration().toMinutes() : null,
        sessionEntity.getCalories(),
        sessionEntity.getSport(),
        sessionEntity.getHeartRateAverage(),
        sessionEntity.getHeartRateMaximum()
    );
  }
}
