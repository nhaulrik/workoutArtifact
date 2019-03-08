package com.workout.workoutArtifact;

import com.workout.workoutArtifact.common.Mapper;
import com.workout.workoutArtifact.endpoint.domain.Muscle;
import com.workout.workoutArtifact.mysqldatabase.MuscleEntity;
import com.workout.workoutArtifact.mysqldatabase.MuscleRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
  MuscleRepository muscleRepository;

  @Bean
  InitializingBean addInitialMuscles() {
    return () -> {
      List<Muscle> muscleList = new ArrayList<>();

      muscleList.add(new Muscle("BICEPS", true));
      muscleList.add(new Muscle("CHEST", true));
      muscleList.add(new Muscle("TRICEPS", true));

      muscleRepository.saveAll(
          muscleList.stream()
              .map(Mapper::toEntity)
              .collect(Collectors.toList())
      );
    };
  }

}
