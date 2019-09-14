package com.workout.workoutArtifact.configuration;

import com.workout.workoutArtifact.domain.workoutset.service.WorkoutSetService;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseEntity;
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

    WorkoutSetEntity workoutSetEntity1 = new WorkoutSetEntity();
    workoutSetEntity1.setSetNumber(1);
    workoutSetEntity1.setRepetitionMaximum(12);
    workoutSetEntity1.setSingle(false);
    workoutSetEntity1.setWeight(80);
    workoutSetEntity1.setRepetitions(8);
    workoutSetEntity1.setExerciseEntity(new ExerciseEntity(7L));

    WorkoutSetEntity workoutSetEntity2 = new WorkoutSetEntity();
    workoutSetEntity2.setSetNumber(2);
    workoutSetEntity2.setRepetitionMaximum(12);
    workoutSetEntity2.setSingle(false);
    workoutSetEntity2.setWeight(82.5);
    workoutSetEntity2.setRepetitions(7);
    workoutSetEntity2.setExerciseEntity(new ExerciseEntity(7L));

    WorkoutSetEntity workoutSetEntity3 = new WorkoutSetEntity();
    workoutSetEntity3.setSetNumber(3);
    workoutSetEntity3.setRepetitionMaximum(12);
    workoutSetEntity3.setSingle(false);
    workoutSetEntity3.setWeight(85.5);
    workoutSetEntity3.setRepetitions(6);
    workoutSetEntity3.setExerciseEntity(new ExerciseEntity(7L)); // TODO: 14-09-2019 maybe use builder instead and avoid magic numbers

    workoutSetJpaRepository.saveAll(Arrays.asList(workoutSetEntity1, workoutSetEntity2, workoutSetEntity3));

    SessionEntity sessionEntity = new SessionEntity("HOME");
    sessionEntity.setWorkoutSetEntities(Stream.of(workoutSetEntity1, workoutSetEntity2, workoutSetEntity3).collect(Collectors.toSet()));

    sessionJpaRepository.save(sessionEntity);
     }

}
