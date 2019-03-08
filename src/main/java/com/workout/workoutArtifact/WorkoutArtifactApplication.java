package com.workout.workoutArtifact;

import com.workout.workoutArtifact.common.BodyPartEnum;
import com.workout.workoutArtifact.common.MuscleEnum;
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

      //BACK
      muscleList.add(new Muscle(MuscleEnum.LATS, BodyPartEnum.UPPER_BACK));
      muscleList.add(new Muscle(MuscleEnum.INNER_BACK, BodyPartEnum.UPPER_BACK));
      muscleList.add(new Muscle(MuscleEnum.RHOMBOID, BodyPartEnum.UPPER_BACK));
      muscleList.add(new Muscle(MuscleEnum.MIDDLE_TRAPS, BodyPartEnum.NECK));
      muscleList.add(new Muscle(MuscleEnum.LOWER_BACK, BodyPartEnum.LOWER_BACK));

      //SHOULDER
      muscleList.add(new Muscle(MuscleEnum.REAR_DELT, BodyPartEnum.SHOULDER));
      muscleList.add(new Muscle(MuscleEnum.FRONT_DELTS, BodyPartEnum.SHOULDER));
      muscleList.add(new Muscle(MuscleEnum.DELTOIDS, BodyPartEnum.SHOULDER));

      //ARMS
      muscleList.add(new Muscle(MuscleEnum.TRICEPS, BodyPartEnum.ARM));
      muscleList.add(new Muscle(MuscleEnum.TRICEPS_LONG, BodyPartEnum.ARM));
      muscleList.add(new Muscle(MuscleEnum.BICEPS_LONG, BodyPartEnum.ARM));
      muscleList.add(new Muscle(MuscleEnum.BICEPS_SHORT, BodyPartEnum.ARM));
      muscleList.add(new Muscle(MuscleEnum.BRACHIALIS, BodyPartEnum.ARM));

      //CHEST
      muscleList.add(new Muscle(MuscleEnum.PECTORAL, BodyPartEnum.CHEST));
      muscleList.add(new Muscle(MuscleEnum.UPPER_CHEST, BodyPartEnum.CHEST));
      muscleList.add(new Muscle(MuscleEnum.LOWER_CHEST, BodyPartEnum.CHEST));
      muscleList.add(new Muscle(MuscleEnum.STERNAL_PECTORALS, BodyPartEnum.CHEST));

      //ABDOMEN
      muscleList.add(new Muscle(MuscleEnum.UPPER_ABDOMINAL, BodyPartEnum.ABDOMEN));
      muscleList.add(new Muscle(MuscleEnum.LOWER_ABDOMINAL, BodyPartEnum.ABDOMEN));
      muscleList.add(new Muscle(MuscleEnum.ABS, BodyPartEnum.ABDOMEN));
      muscleList.add(new Muscle(MuscleEnum.OBLIQUES, BodyPartEnum.ABDOMEN));
      muscleList.add(new Muscle(MuscleEnum.CORE, BodyPartEnum.ABDOMEN));

      //LEGS
      muscleList.add(new Muscle(MuscleEnum.QUADS, BodyPartEnum.LEG));
      muscleList.add(new Muscle(MuscleEnum.GLUTES, BodyPartEnum.LEG));
      muscleList.add(new Muscle(MuscleEnum.ADDUCTORS, BodyPartEnum.LEG));
      muscleList.add(new Muscle(MuscleEnum.HAMSTRINGS, BodyPartEnum.LEG));
      muscleList.add(new Muscle(MuscleEnum.CALF, BodyPartEnum.LEG));

      muscleService.addMuscles(muscleList);
    };
  }

}
