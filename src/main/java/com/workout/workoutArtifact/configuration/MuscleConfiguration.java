package com.workout.workoutArtifact.configuration;

import com.workout.workoutArtifact.domain.exercise.model.Exercise;
import com.workout.workoutArtifact.domain.exercise.service.ExerciseService;
import com.workout.workoutArtifact.domain.muscle.model.Muscle;
import com.workout.workoutArtifact.domain.muscle.service.MuscleService;
import com.workout.workoutArtifact.infrastructure.common.enums.BodyPartEnum;
import com.workout.workoutArtifact.infrastructure.common.enums.ExerciseEnum;
import com.workout.workoutArtifact.infrastructure.common.enums.MuscleEnum;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseEntity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MuscleConfiguration {

  @Autowired
  MuscleService muscleService;

  @Autowired
  ExerciseService exerciseService;

  @Bean
  public void configureMusclesForDatabase() {

    muscleService.addMuscles(Arrays.asList(Muscle.builder().name(MuscleEnum.LATS.toString()).bodyPart(BodyPartEnum.UPPER_BACK.toString()).build()));
    muscleService.addMuscles(Arrays.asList(Muscle.builder().name(MuscleEnum.INNER_BACK.toString()).bodyPart(BodyPartEnum.UPPER_BACK.toString()).build()));
    muscleService.addMuscles(Arrays.asList(Muscle.builder().name(MuscleEnum.RHOMBOID.toString()).bodyPart(BodyPartEnum.UPPER_BACK.toString()).build()));
    muscleService.addMuscles(Arrays.asList(Muscle.builder().name(MuscleEnum.TRAPS.toString()).bodyPart(BodyPartEnum.UPPER_BACK.toString()).build()));
    muscleService.addMuscles(Arrays.asList(Muscle.builder().name(MuscleEnum.LOWER_BACK.toString()).bodyPart(BodyPartEnum.LOWER_BACK.toString()).build()));
    muscleService.addMuscles(Arrays.asList(Muscle.builder().name(MuscleEnum.REAR_DELT.toString()).bodyPart(BodyPartEnum.SHOULDER.toString()).build()));
    muscleService.addMuscles(Arrays.asList(Muscle.builder().name(MuscleEnum.FRONT_DELTS.toString()).bodyPart(BodyPartEnum.SHOULDER.toString()).build()));
    muscleService.addMuscles(Arrays.asList(Muscle.builder().name(MuscleEnum.DELTOIDS.toString()).bodyPart(BodyPartEnum.SHOULDER.toString()).build()));
    muscleService.addMuscles(Arrays.asList(Muscle.builder().name(MuscleEnum.TRICEPS.toString()).bodyPart(BodyPartEnum.ARM.toString()).build()));
    muscleService.addMuscles(Arrays.asList(Muscle.builder().name(MuscleEnum.BICEPS.toString()).bodyPart(BodyPartEnum.ARM.toString()).build()));
    muscleService.addMuscles(Arrays.asList(Muscle.builder().name(MuscleEnum.BRACHIALIS.toString()).bodyPart(BodyPartEnum.ARM.toString()).build()));
    muscleService.addMuscles(Arrays.asList(Muscle.builder().name(MuscleEnum.PECTORAL.toString()).bodyPart(BodyPartEnum.CHEST.toString()).build()));
    muscleService.addMuscles(Arrays.asList(Muscle.builder().name(MuscleEnum.CHEST.toString()).bodyPart(BodyPartEnum.CHEST.toString()).build()));
    muscleService.addMuscles(Arrays.asList(Muscle.builder().name(MuscleEnum.STERNAL_PECTORALS.toString()).bodyPart(BodyPartEnum.CHEST.toString()).build()));
    muscleService.addMuscles(Arrays.asList(Muscle.builder().name(MuscleEnum.UPPER_ABDOMINAL.toString()).bodyPart(BodyPartEnum.ABDOMEN.toString()).build()));
    muscleService.addMuscles(Arrays.asList(Muscle.builder().name(MuscleEnum.LOWER_ABDOMINAL.toString()).bodyPart(BodyPartEnum.ABDOMEN.toString()).build()));
    muscleService.addMuscles(Arrays.asList(Muscle.builder().name(MuscleEnum.ABS.toString()).bodyPart(BodyPartEnum.ABDOMEN.toString()).build()));
    muscleService.addMuscles(Arrays.asList(Muscle.builder().name(MuscleEnum.OBLIQUES.toString()).bodyPart(BodyPartEnum.ABDOMEN.toString()).build()));
    muscleService.addMuscles(Arrays.asList(Muscle.builder().name(MuscleEnum.CORE.toString()).bodyPart(BodyPartEnum.ABDOMEN.toString()).build()));
    muscleService.addMuscles(Arrays.asList(Muscle.builder().name(MuscleEnum.QUADS.toString()).bodyPart(BodyPartEnum.LEG.toString()).build()));
    muscleService.addMuscles(Arrays.asList(Muscle.builder().name(MuscleEnum.GLUTES.toString()).bodyPart(BodyPartEnum.LEG.toString()).build()));
    muscleService.addMuscles(Arrays.asList(Muscle.builder().name(MuscleEnum.ADDUCTORS.toString()).bodyPart(BodyPartEnum.LEG.toString()).build()));
    muscleService.addMuscles(Arrays.asList(Muscle.builder().name(MuscleEnum.HAMSTRINGS.toString()).bodyPart(BodyPartEnum.LEG.toString()).build()));
    muscleService.addMuscles(Arrays.asList(Muscle.builder().name(MuscleEnum.CALF.toString()).bodyPart(BodyPartEnum.LEG.toString()).build()));

    configureExercises();
  }

  private void configureExercises() {

    List<Exercise> exerciseList = new ArrayList();

    //Chest Exercises
    exerciseList.add(Exercise.builder().bodyPart(BodyPartEnum.CHEST.name()).name(ExerciseEnum.DUMBELL_INCLINE_PRESS.toString()).isMultiJoint(true).muscleIds(Arrays.asList(9L,7L, 12L, 13L, 14L)).build());
    exerciseList.add(Exercise.builder().bodyPart(BodyPartEnum.CHEST.name()).name(ExerciseEnum.DUMBELL_DECLINE_PRESS.toString()).isMultiJoint(true).muscleIds(Arrays.asList(9L,7L, 12L, 13L, 14L)).build());
    exerciseList.add(Exercise.builder().bodyPart(BodyPartEnum.CHEST.name()).name(ExerciseEnum.BARBELL_INCLINE_PRESS.toString()).isMultiJoint(true).muscleIds(Arrays.asList(9L,7L, 12L, 13L, 14L)).build());
    exerciseList.add(Exercise.builder().bodyPart(BodyPartEnum.CHEST.name()).name(ExerciseEnum.BARBELL_CHEST_PRESS.toString()).isMultiJoint(true).muscleIds(Arrays.asList(9L,7L, 12L, 13L, 14L)).build());
    exerciseList.add(Exercise.builder().bodyPart(BodyPartEnum.CHEST.name()).name(ExerciseEnum.BARBELL_DECLINE_PRESS.toString()).isMultiJoint(true).muscleIds(Arrays.asList(9L,7L, 12L, 13L, 14L)).build());
    exerciseList.add(Exercise.builder().bodyPart(BodyPartEnum.CHEST.name()).name(ExerciseEnum.DUMBELL_FLAT_FLY.toString()).isMultiJoint(false).muscleIds(Arrays.asList(12L, 13L, 14L)).build());
    exerciseList.add(Exercise.builder().bodyPart(BodyPartEnum.CHEST.name()).name(ExerciseEnum.DUMBELL_INCLINE_FLY.toString()).isMultiJoint(false).muscleIds(Arrays.asList(12L, 13L, 14L)).build());
    exerciseList.add(Exercise.builder().bodyPart(BodyPartEnum.CHEST.name()).name(ExerciseEnum.PEC_DECK_FLY.toString()).isMultiJoint(false).muscleIds(Arrays.asList(12L, 13L, 14L)).build());
    exerciseList.add(Exercise.builder().bodyPart(BodyPartEnum.CHEST.name()).name(ExerciseEnum.CABLE_FLY.toString()).isMultiJoint(false).muscleIds(Arrays.asList(12L, 13L, 14L)).build());


    exerciseService.addExercises(exerciseList);
  }

}
