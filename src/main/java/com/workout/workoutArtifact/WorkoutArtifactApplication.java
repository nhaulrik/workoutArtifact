package com.workout.workoutArtifact;

import com.workout.workoutArtifact.backend.common.enums.BodyPartEnum;
import com.workout.workoutArtifact.backend.common.enums.ExerciseEnum;
import com.workout.workoutArtifact.backend.common.enums.MuscleEnum;
import com.workout.workoutArtifact.domain.service.MuscleService;
import com.workout.workoutArtifact.backend.mysqldatabase.entity.ExerciseEntity;
import com.workout.workoutArtifact.backend.mysqldatabase.repository.ExerciseRepository;
import com.workout.workoutArtifact.backend.mysqldatabase.entity.MuscleEntity;
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
      MuscleEntity biceps = new MuscleEntity(MuscleEnum.BICEPS.toString(), BodyPartEnum.ARM.toString());
      MuscleEntity brachialis = new MuscleEntity(MuscleEnum.BRACHIALIS.toString(), BodyPartEnum.ARM.toString());

      //CHEST
      MuscleEntity pectoral = new MuscleEntity(MuscleEnum.PECTORAL.toString(), BodyPartEnum.CHEST.toString());
      MuscleEntity chest = new MuscleEntity(MuscleEnum.CHEST.toString(), BodyPartEnum.CHEST.toString());
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

      //BACK
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.DUMBELL_PULLOVER.toString(),false, Arrays.asList(lats, sternalPectorals, triceps),BodyPartEnum.CHEST.toString()));
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.DUMBELL_ONE_ARM_ROW.toString(),true, Arrays.asList(lats, innerBack, biceps), BodyPartEnum.UPPER_BACK.toString()));
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.BARBELL_REVERSE_BENT_ROW.toString(),true, Arrays.asList(innerBack, biceps, lats, rearDelt), BodyPartEnum.UPPER_BACK.toString()));
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.CABLE_SEATED_ROW.toString(),true, Arrays.asList(rhomboids, traps, lats, innerBack, biceps), BodyPartEnum.UPPER_BACK.toString()));
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.CHIN_UP.toString(),true, Arrays.asList(biceps, brachialis, lats, rhomboids, traps), BodyPartEnum.UPPER_BACK.toString()));
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.PULL_UP.toString(),true, Arrays.asList(biceps, brachialis, lats, rhomboids, traps), BodyPartEnum.UPPER_BACK.toString()));
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.LAT_PULL_DOWN.toString(),true, Arrays.asList(lats, rhomboids, traps, biceps, rearDelt), BodyPartEnum.UPPER_BACK.toString()));
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.LAT_PULL_DOWN_REVERSE_GRIP.toString(),true, Arrays.asList(lats, rhomboids, traps, biceps), BodyPartEnum.UPPER_BACK.toString()));
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.LAT_PULL_DOWN_CABLE.toString(),false, Arrays.asList(lats, rhomboids, traps, biceps), BodyPartEnum.UPPER_BACK.toString()));

      //CHEST
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.DUMBELL_INCLINE_PRESS.toString(),true, Arrays.asList(chest, sternalPectorals, triceps, frontDelt), BodyPartEnum.CHEST.toString()));
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.DUMBELL_DECLINE_PRESS.toString(),true, Arrays.asList(chest, sternalPectorals, triceps, frontDelt), BodyPartEnum.CHEST.toString()));
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.BARBELL_INCLINE_PRESS.toString(),true, Arrays.asList(chest, sternalPectorals, triceps, frontDelt), BodyPartEnum.CHEST.toString()));
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.BARBELL_CHEST_PRESS.toString(),true, Arrays.asList(chest, sternalPectorals, triceps, frontDelt), BodyPartEnum.CHEST.toString()));
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.BARBELL_DECLINE_PRESS.toString(),true, Arrays.asList(chest, sternalPectorals, triceps, frontDelt), BodyPartEnum.CHEST.toString()));
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.DUMBELL_FLAT_FLY.toString(),false, Arrays.asList(sternalPectorals), BodyPartEnum.CHEST.toString()));
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.DUMBELL_INCLINE_FLY.toString(),false, Arrays.asList(sternalPectorals), BodyPartEnum.CHEST.toString()));
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.PEC_DECK_FLY.toString(),false, Arrays.asList(sternalPectorals), BodyPartEnum.CHEST.toString()));
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.CABLE_FLY.toString(),false, Arrays.asList(sternalPectorals), BodyPartEnum.CHEST.toString()));

      //ABDOMEN
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.CRUNCH.toString(),true, Arrays.asList(upperAbdominal), BodyPartEnum.ABDOMEN.toString()));
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.REVERSE_CRUNCH.toString(),true, Arrays.asList(lowerAbdominal), BodyPartEnum.ABDOMEN.toString()));
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.BICYCLE_CRUNCH.toString(),true, Arrays.asList(abs), BodyPartEnum.ABDOMEN.toString()));
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.ROMAN_CHAIR_SIDE_CRUNCH.toString(),true, Arrays.asList(obliques), BodyPartEnum.ABDOMEN.toString()));
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.BALL_CRUNCH.toString(),true, Arrays.asList(abs), BodyPartEnum.ABDOMEN.toString()));
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.CABLE_ROPE_KNEELING_CRUNCH.toString(),true, Arrays.asList(abs, obliques), BodyPartEnum.ABDOMEN.toString()));
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.PLANK.toString(),true, Arrays.asList(core), BodyPartEnum.ABDOMEN.toString()));
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.PLANK_SIDE.toString(),true, Arrays.asList(core), BodyPartEnum.ABDOMEN.toString()));
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.RUSSIAN_TWIST.toString(),true, Arrays.asList(obliques), BodyPartEnum.ABDOMEN.toString()));
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.DUMBELL_SIDE_BEND.toString(),true, Arrays.asList(obliques), BodyPartEnum.ABDOMEN.toString()));

      //SHOULDER
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.ARNOLD_PRESS.toString(),true, Arrays.asList(deltoids, traps, triceps), BodyPartEnum.SHOULDER.toString()));
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.MILITARY_PRESS.toString(),true, Arrays.asList(deltoids, frontDelt, traps, triceps), BodyPartEnum.SHOULDER.toString()));
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.DUMBELL_SHOULDER_PRESS.toString(),true, Arrays.asList(frontDelt, deltoids, traps, triceps), BodyPartEnum.SHOULDER.toString()));
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.MACHINE_SHOULDER_PRESS.toString(),true, Arrays.asList(frontDelt, deltoids, traps, triceps), BodyPartEnum.SHOULDER.toString()));
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.DUMBELL_LATERAL_RAISE.toString(),false, Arrays.asList(deltoids), BodyPartEnum.SHOULDER.toString()));
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.MACHINE_LATERAL_RAISE.toString(),false, Arrays.asList(deltoids), BodyPartEnum.SHOULDER.toString()));
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.CABLE_LATERAL_RAISE.toString(),false, Arrays.asList(deltoids), BodyPartEnum.SHOULDER.toString()));
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.DUMBELL_BENT_REVERSE_FLY.toString(),false, Arrays.asList(rearDelt), BodyPartEnum.SHOULDER.toString()));
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.CABLE_REVERSE_FLY.toString(),false, Arrays.asList(rearDelt), BodyPartEnum.SHOULDER.toString()));
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.BARBELL_UPRIGHT_ROW.toString(),true, Arrays.asList(deltoids), BodyPartEnum.SHOULDER.toString()));
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.CABLE_UPRIGHT_ROW.toString(),true, Arrays.asList(deltoids), BodyPartEnum.SHOULDER.toString()));

      //BICEPS
      exerciseEntities.add(new ExerciseEntity(ExerciseEnum.DIP.toString(),true, Arrays.asList(chest, sternalPectorals, triceps, frontDelt), BodyPartEnum.ARM.toString()));

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
