package com.workout.workoutArtifact;

import com.workout.workoutArtifact.common.BodyPartEnum;
import com.workout.workoutArtifact.common.ExerciseEnum;
import com.workout.workoutArtifact.common.MuscleEnum;
import com.workout.workoutArtifact.endpoint.service.MuscleService;
import com.workout.workoutArtifact.mysqldatabase.ExerciseEntity;
import com.workout.workoutArtifact.mysqldatabase.ExerciseRepository;
import com.workout.workoutArtifact.mysqldatabase.MuscleEntity;
import java.util.ArrayList;
import java.util.Arrays;
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

  @Autowired
  ExerciseRepository exerciseRepository;

  @Bean
  InitializingBean addInitialExercises() {
    return () -> {

      //BACK
      MuscleEntity lats = new MuscleEntity(MuscleEnum.LATS.toString(), BodyPartEnum.UPPER_BACK.toString());
      MuscleEntity innerBack = new MuscleEntity(MuscleEnum.INNER_BACK.toString(), BodyPartEnum.UPPER_BACK.toString());
      MuscleEntity rhomboids = new MuscleEntity(MuscleEnum.RHOMBOID.toString(), BodyPartEnum.UPPER_BACK.toString());
      MuscleEntity traps = new MuscleEntity(MuscleEnum.TRAPS.toString(), BodyPartEnum.UPPER_BACK.toString());
      MuscleEntity lowerBack = new MuscleEntity(MuscleEnum.LOWER_BACK.toString(), BodyPartEnum.LOWER_BACK.toString());

      //SHOULDER
      MuscleEntity rearDelt = new MuscleEntity(MuscleEnum.REAR_DELT.toString(), BodyPartEnum.SHOULDER.toString());
      MuscleEntity frontDelt = new MuscleEntity(MuscleEnum.FRONT_DELTS.toString(), BodyPartEnum.SHOULDER.toString());
      MuscleEntity deltoids = new MuscleEntity(MuscleEnum.DELTOIDS.toString(), BodyPartEnum.SHOULDER.toString());

      //ARMS
      MuscleEntity triceps = new MuscleEntity(MuscleEnum.TRICEPS.toString(), BodyPartEnum.ARM.toString());
      MuscleEntity tricepsLong = new MuscleEntity(MuscleEnum.TRICEPS_LONG.toString(), BodyPartEnum.ARM.toString());
      MuscleEntity bicepsLong = new MuscleEntity(MuscleEnum.BICEPS_LONG.toString(), BodyPartEnum.ARM.toString());
      MuscleEntity bicepsShort = new MuscleEntity(MuscleEnum.BICEPS_SHORT.toString(), BodyPartEnum.ARM.toString());
      MuscleEntity brachialis = new MuscleEntity(MuscleEnum.BRACHIALIS.toString(), BodyPartEnum.ARM.toString());

      //CHEST
      MuscleEntity pectoral = new MuscleEntity(MuscleEnum.PECTORAL.toString(), BodyPartEnum.CHEST.toString());
      MuscleEntity upperChest = new MuscleEntity(MuscleEnum.UPPER_CHEST.toString(), BodyPartEnum.CHEST.toString());
      MuscleEntity lowerChest = new MuscleEntity(MuscleEnum.LOWER_CHEST.toString(), BodyPartEnum.CHEST.toString());
      MuscleEntity sternalPectorals = new MuscleEntity(MuscleEnum.STERNAL_PECTORALS.toString(), BodyPartEnum.CHEST.toString());

      //ABDOMEN
      MuscleEntity upperAbdominal = new MuscleEntity(MuscleEnum.UPPER_ABDOMINAL.toString(), BodyPartEnum.ABDOMEN.toString());
      MuscleEntity lowerAbdominal = new MuscleEntity(MuscleEnum.LOWER_ABDOMINAL.toString(), BodyPartEnum.ABDOMEN.toString());
      MuscleEntity abs = new MuscleEntity(MuscleEnum.ABS.toString(), BodyPartEnum.ABDOMEN.toString());
      MuscleEntity obliques = new MuscleEntity(MuscleEnum.OBLIQUES.toString(), BodyPartEnum.ABDOMEN.toString());
      MuscleEntity core = new MuscleEntity(MuscleEnum.CORE.toString(), BodyPartEnum.ABDOMEN.toString());

      //LEGS
      MuscleEntity quads = new MuscleEntity(MuscleEnum.QUADS.toString(), BodyPartEnum.LEG.toString());
      MuscleEntity glutes = new MuscleEntity(MuscleEnum.GLUTES.toString(), BodyPartEnum.LEG.toString());
      MuscleEntity adductors = new MuscleEntity(MuscleEnum.ADDUCTORS.toString(), BodyPartEnum.LEG.toString());
      MuscleEntity hamstrings = new MuscleEntity(MuscleEnum.HAMSTRINGS.toString(), BodyPartEnum.LEG.toString());
      MuscleEntity calf = new MuscleEntity(MuscleEnum.CALF.toString(), BodyPartEnum.LEG.toString());

      List<ExerciseEntity> exerciseEntities = new ArrayList<>();

      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.DUMBELL_PULLOVER.toString(),false, Arrays.asList(lats, sternalPectorals)));
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.DUMBELL_ONE_ARM_ROW.toString(),true, Arrays.asList(lats, innerBack)));
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.BARBELL_REVERSE_BENT_ROW.toString(),true, Arrays.asList(innerBack, bicepsLong, bicepsShort, lats, rearDelt)));
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.CABLE_SEATED_ROW.toString(),true, Arrays.asList(rhomboids, traps, lats, innerBack, bicepsLong, bicepsShort)));

      exerciseRepository.saveAll(exerciseEntities);

    };
  }

  @Bean
  InitializingBean addInitialMuscles() {
    return () -> {
//      List<Muscle> muscleList = new ArrayList<>();
//
//      //BACK
//      muscleList.add(new Muscle(MuscleEnum.LATS, BodyPartEnum.UPPER_BACK));
//      muscleList.add(new Muscle(MuscleEnum.INNER_BACK, BodyPartEnum.UPPER_BACK));
//      muscleList.add(new Muscle(MuscleEnum.RHOMBOID, BodyPartEnum.UPPER_BACK));
//      muscleList.add(new Muscle(MuscleEnum.MIDDLE_TRAPS, BodyPartEnum.NECK));
//      muscleList.add(new Muscle(MuscleEnum.LOWER_BACK, BodyPartEnum.LOWER_BACK));
//
//      //SHOULDER
//      muscleList.add(new Muscle(MuscleEnum.REAR_DELT, BodyPartEnum.SHOULDER));
//      muscleList.add(new Muscle(MuscleEnum.FRONT_DELTS, BodyPartEnum.SHOULDER));
//      muscleList.add(new Muscle(MuscleEnum.DELTOIDS, BodyPartEnum.SHOULDER));
//
//      //ARMS
//      muscleList.add(new Muscle(MuscleEnum.TRICEPS, BodyPartEnum.ARM));
//      muscleList.add(new Muscle(MuscleEnum.TRICEPS_LONG, BodyPartEnum.ARM));
//      muscleList.add(new Muscle(MuscleEnum.BICEPS_LONG, BodyPartEnum.ARM));
//      muscleList.add(new Muscle(MuscleEnum.BICEPS_SHORT, BodyPartEnum.ARM));
//      muscleList.add(new Muscle(MuscleEnum.BRACHIALIS, BodyPartEnum.ARM));
//
//      //CHEST
//      muscleList.add(new Muscle(MuscleEnum.PECTORAL, BodyPartEnum.CHEST));
//      muscleList.add(new Muscle(MuscleEnum.UPPER_CHEST, BodyPartEnum.CHEST));
//      muscleList.add(new Muscle(MuscleEnum.LOWER_CHEST, BodyPartEnum.CHEST));
//      muscleList.add(new Muscle(MuscleEnum.STERNAL_PECTORALS, BodyPartEnum.CHEST));
//
//      //ABDOMEN
//      muscleList.add(new Muscle(MuscleEnum.UPPER_ABDOMINAL, BodyPartEnum.ABDOMEN));
//      muscleList.add(new Muscle(MuscleEnum.LOWER_ABDOMINAL, BodyPartEnum.ABDOMEN));
//      muscleList.add(new Muscle(MuscleEnum.ABS, BodyPartEnum.ABDOMEN));
//      muscleList.add(new Muscle(MuscleEnum.OBLIQUES, BodyPartEnum.ABDOMEN));
//      muscleList.add(new Muscle(MuscleEnum.CORE, BodyPartEnum.ABDOMEN));
//
//      //LEGS
//      muscleList.add(new Muscle(MuscleEnum.QUADS, BodyPartEnum.LEG));
//      muscleList.add(new Muscle(MuscleEnum.GLUTES, BodyPartEnum.LEG));
//      muscleList.add(new Muscle(MuscleEnum.ADDUCTORS, BodyPartEnum.LEG));
//      muscleList.add(new Muscle(MuscleEnum.HAMSTRINGS, BodyPartEnum.LEG));
//      muscleList.add(new Muscle(MuscleEnum.CALF, BodyPartEnum.LEG));
//
//      muscleService.addMuscles(muscleList);
    };
  }

}
