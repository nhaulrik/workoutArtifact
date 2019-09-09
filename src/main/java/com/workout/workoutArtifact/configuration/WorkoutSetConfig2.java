package com.workout.workoutArtifact.configuration;

import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import com.workout.workoutArtifact.domain.workoutset.service.WorkoutSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class WorkoutSetConfig2 {

  @Autowired
  WorkoutSetService workoutSetService;

  @Bean
  public void workoutSetConfig() {

    WorkoutSet workoutSet1 = WorkoutSet.builder()
        .exerciseId(1L)
        .setNumber(1)
        .repetitionMaximum(8)
        .repetitions(7)
        .single(false)
        .weight(80)
        .build();

    WorkoutSet workoutSet2 = WorkoutSet.builder()
        .exerciseId(1L)
        .setNumber(2)
        .repetitionMaximum(8)
        .repetitions(6)
        .single(false)
        .weight(82)
        .build();

    WorkoutSet workoutSet3 = WorkoutSet.builder()
        .exerciseId(1L)
        .setNumber(3)
        .repetitionMaximum(8)
        .repetitions(5)
        .single(false)
        .weight(85)
        .build();

    workoutSetService.addWorkoutSet(workoutSet1);
    workoutSetService.addWorkoutSet(workoutSet2);
    workoutSetService.addWorkoutSet(workoutSet3);
  }

}
