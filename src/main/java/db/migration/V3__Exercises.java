package db.migration;

import com.workout.workoutArtifact.db.migration.SpringUtility;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseMuscleRelationEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.MuscleEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.ExerciseJpaRepository;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.MuscleJpaRepository;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

public class V3__Exercises extends BaseJavaMigration {

  ExerciseJpaRepository exerciseJpaRepository = SpringUtility.getBean(ExerciseJpaRepository.class);
  MuscleJpaRepository muscleJpaRepository = SpringUtility.getBean(MuscleJpaRepository.class);

  public void migrate(Context context) {

    addExercise("Barbell Chest Press From Migration", "Chest", true,
        withMuscle("Biceps", 10),
        withMuscle("Triceps", 20));

    addExercise("Dumbell Pullover", "Chest", false,
        withMuscle("Lats", 50),
        withMuscle("Sternal Pectorals", 40));

    addExercise("Dumbell One Arm Row", "Back", true,
        withMuscle("Inner Back", 70),
        withMuscle("Biceps", 50));
  }

  private ExerciseMuscleRelationEntity withMuscle(String muscleName, Integer utilization) {

    MuscleEntity muscleEntity = muscleJpaRepository.findByName(muscleName);
    ExerciseMuscleRelationEntity exerciseMuscleRelationEntity = new ExerciseMuscleRelationEntity();
    exerciseMuscleRelationEntity.setMuscleEntity(muscleEntity);
    exerciseMuscleRelationEntity.setRatio(utilization);
    return exerciseMuscleRelationEntity;
  }

  private void addExercise(String name, String bodyPart, Boolean isCompound, ExerciseMuscleRelationEntity... exerciseMuscleRelationEntityList) {

    ExerciseEntity exerciseEntity = new ExerciseEntity();
    exerciseEntity.setName(name);
    exerciseEntity.setBodyPart(bodyPart);
    exerciseEntity.setIsCompound(isCompound);
    exerciseEntity.setExerciseMuscleRelationEntities(Arrays.stream(exerciseMuscleRelationEntityList).collect(Collectors.toSet()));
    Arrays.stream(exerciseMuscleRelationEntityList).forEach(exerciseMuscleRelationEntity -> exerciseMuscleRelationEntity.setExerciseEntity(exerciseEntity));

    exerciseJpaRepository.save(exerciseEntity);
  }

}
