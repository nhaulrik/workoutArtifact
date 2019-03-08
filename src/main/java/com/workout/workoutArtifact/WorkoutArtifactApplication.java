package com.workout.workoutArtifact;

import com.workout.workoutArtifact.common.BodyPartEnum;
import com.workout.workoutArtifact.endpoint.domain.Muscle;
import com.workout.workoutArtifact.endpoint.service.MuscleService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WorkoutArtifactApplication {

  public static void main(String[] args) {
    SpringApplication.run(WorkoutArtifactApplication.class, args);
  }

  @Autowired
  MuscleService muscleService;

  @Bean
  InitializingBean addInitialMuscles() {
    return () -> {
      List<Muscle> muscleList = new ArrayList<>();

      muscleList.add(new Muscle("BICEPS", BodyPartEnum.ARM));
      muscleList.add(new Muscle("TRICEPS", BodyPartEnum.ARM));
      muscleList.add(new Muscle("CHEST", BodyPartEnum.UPPER_FRONT));

      muscleService.addMuscles(muscleList);
    };
  }

}
