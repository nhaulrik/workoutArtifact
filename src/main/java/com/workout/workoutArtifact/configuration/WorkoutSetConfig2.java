package com.workout.workoutArtifact.configuration;

import com.workout.workoutArtifact.domain.workoutset.service.WorkoutSetService;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.SessionEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.WorkoutSetEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.SessionJpaRepository;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.WorkoutSetJpaRepository;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class WorkoutSetConfig2 {

  @Autowired
  WorkoutSetJpaRepository workoutSetJpaRepository;
  @Autowired
  SessionJpaRepository sessionJpaRepository;

  @Bean
  public void workoutSetConfig() {

    WorkoutSetEntity workoutSetEntity1 = new WorkoutSetEntity(
        8,
        80,
        false,
        8,
        1,
        7L
    );

    workoutSetEntity1.setExerciseId(7L);

    WorkoutSetEntity workoutSetEntity2 = new WorkoutSetEntity(
        6,
        82.5d,
        false,
        8,
        2,
        7L
    );

    workoutSetEntity2.setExerciseId(7L);

    WorkoutSetEntity workoutSetEntity3 = new WorkoutSetEntity(
        5,
        85d,
        false,
        8,
        3,
        7L
    );

    workoutSetEntity3.setExerciseId(7L);

    workoutSetJpaRepository.saveAll(Arrays.asList(workoutSetEntity1, workoutSetEntity2, workoutSetEntity3));

    SessionEntity sessionEntity = new SessionEntity("HOME");
    sessionEntity.setWorkoutSetEntities(Stream.of(workoutSetEntity1, workoutSetEntity2, workoutSetEntity3).collect(Collectors.toSet()));

    sessionJpaRepository.save(sessionEntity);
     }

}
