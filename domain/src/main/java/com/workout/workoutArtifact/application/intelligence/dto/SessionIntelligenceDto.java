package com.workout.workoutArtifact.application.intelligence.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
public class SessionIntelligenceDto {

    private final String userId;
    private final Double totalWeight;
    private final Integer totalRepetitions;
    private final LocalDate date;
    private final Integer calories;
    private final Long durationMinutes;

    private final List<SessionWorkoutExerciseDto> sessionWorkoutExerciseDtos;
}
