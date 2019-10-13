package db.migration;

import com.workout.workoutArtifact.db.migration.SpringUtility;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.ExerciseJpaRepository;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.MuscleJpaRepository;
import java.util.stream.Collectors;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

public class R__Exercises extends BaseJavaMigration {

  ExerciseJpaRepository exerciseJpaRepository = SpringUtility.getBean(ExerciseJpaRepository.class);
  MuscleJpaRepository muscleJpaRepository = SpringUtility.getBean(MuscleJpaRepository.class);

  public void migrate(Context context) {

    addExercise("Barbell Chest Press", "Chest", true, "Biceps", "Triceps");
    addExercise("Dumbell Pullover", "Chest", false, "Lats", "Sternal Pectorals");
    addExercise("Dumbell One Arm Row", "Back", true, "Inner Back", "Biceps");
  }

  private void addExercise(String name, String bodyPart, Boolean isCompound, String ...muscleNames) {
    ExerciseEntity exerciseEntity = new ExerciseEntity();
    exerciseEntity.setName(name);
    exerciseEntity.setBodyPart(bodyPart);
    exerciseEntity.setIsCompound(isCompound);
    exerciseEntity.setMuscleEntities(muscleJpaRepository.findAllByNameIn(muscleNames).stream().collect(Collectors.toSet()));
    exerciseJpaRepository.save(exerciseEntity);
  }

}
