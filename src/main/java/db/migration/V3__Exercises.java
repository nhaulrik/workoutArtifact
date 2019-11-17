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

    // BACK
    addExercise("Dumbell Pullover", "Upper body", false,
        withMuscle("Lats", 5),
        withMuscle("Sternal Pectorals", 4));

    addExercise("Dumbell One Arm Row", "Back", true,
        withMuscle("Upper Back", 5),
        withMuscle("Biceps", 3));

    addExercise("Barbell Bent Row", "Back", true,
        withMuscle("Upper Back", 5),
        withMuscle("Lats", 4),
        withMuscle("Biceps", 3),
        withMuscle("Forearm", 2),
        withMuscle("Hamstrings", 2),
        withMuscle("Glutes", 2));

    addExercise("Cable Seated Row", "Back", true,
        withMuscle("Upper Back", 5),
        withMuscle("Lats", 4),
        withMuscle("Biceps", 3));

    addExercise("Chin Up", "Back", true,
        withMuscle("Lats", 5),
        withMuscle("Biceps", 4),
        withMuscle("Forearm", 2));

    addExercise("Pull Up", "Back", true,
        withMuscle("Lats", 5),
        withMuscle("Biceps", 4),
        withMuscle("Forearm", 2));

    //CHEST
    addExercise("Dumbell Incline Press", "Chest", true,
        withMuscle("Chest", 5),
        withMuscle("Pectoral", 4),
        withMuscle("Front Delts", 4),
        withMuscle("Sternal Pectorals", 4),
        withMuscle("Triceps", 3));

    addExercise("Dumbell Decline Press", "Chest", true,
        withMuscle("Chest", 5),
        withMuscle("Pectoral", 4),
        withMuscle("Front Delts", 4),
        withMuscle("Sternal Pectorals", 4),
        withMuscle("Triceps", 3));

    addExercise("Dumbell Chest Press", "Chest", true,
        withMuscle("Chest", 5),
        withMuscle("Pectoral", 4),
        withMuscle("Front Delts", 4),
        withMuscle("Sternal Pectorals", 4),
        withMuscle("Triceps", 3));

    addExercise("Barbell Incline Press", "Chest", true,
        withMuscle("Chest", 5),
        withMuscle("Pectoral", 4),
        withMuscle("Front Delts", 4),
        withMuscle("Sternal Pectorals", 4),
        withMuscle("Triceps", 3));

    addExercise("Barbell Chest Press", "Chest", true,
        withMuscle("Chest", 5),
        withMuscle("Pectoral", 4),
        withMuscle("Front Delts", 4),
        withMuscle("Sternal Pectorals", 4),
        withMuscle("Triceps", 3));

    addExercise("Barbell Decline Press", "Chest", true,
        withMuscle("Chest", 5),
        withMuscle("Pectoral", 4),
        withMuscle("Front Delts", 4),
        withMuscle("Sternal Pectorals", 4),
        withMuscle("Triceps", 3));

    addExercise("Dumbell Flat Fly", "Chest", false,
        withMuscle("Sternal Pectorals", 5),
        withMuscle("Pectoral", 4),
        withMuscle("Chest", 3));

    addExercise("Dumbell Incline Fly", "Chest", false,
        withMuscle("Sternal Pectorals", 5),
        withMuscle("Pectoral", 4),
        withMuscle("Chest", 3));

    addExercise("Pec Deck Fly", "Chest", false,
        withMuscle("Sternal Pectorals", 5),
        withMuscle("Pectoral", 4),
        withMuscle("Chest", 3));

    addExercise("Cable Fly", "Chest", false,
        withMuscle("Sternal Pectorals", 5),
        withMuscle("Pectoral", 4),
        withMuscle("Chest", 3));

    addExercise("Dips", "Chest", true,
        withMuscle("Sternal Pectorals", 5),
        withMuscle("Triceps", 5),
        withMuscle("Pectoral", 4),
        withMuscle("Chest", 4),
        withMuscle("Rhomboid", 3),
        withMuscle("Front Delts", 3));

    //Abdomen
    addExercise("Crunch", "Abdomen", true,
        withMuscle("Abs", 5),
        withMuscle("Upper Abdominal", 5),
        withMuscle("Core", 2));

    addExercise("Reverse Crunch", "Abdomen", true,
        withMuscle("Abs", 5),
        withMuscle("Lower Abdominal", 5),
        withMuscle("Core", 2));

    addExercise("Bicycle Crunch", "Abdomen", true,
        withMuscle("Abs", 5),
        withMuscle("Core", 2));

    addExercise("Cable Rope Kneeling Crunch", "Abdomen", true,
        withMuscle("Abs", 5),
        withMuscle("Upper Abdominal", 4),
        withMuscle("Core", 2));

    addExercise("Cable Rope Kneeling Twisting Crunch", "Abdomen", true,
        withMuscle("Abs", 5),
        withMuscle("Obliques", 4),
        withMuscle("Upper Abdominal", 3),
        withMuscle("Core", 2));

    addExercise("Plank", "Core", true,
        withMuscle("Core", 5),
        withMuscle("Abs", 2));

    addExercise("Side Plank", "Core", true,
        withMuscle("Core", 5),
        withMuscle("Obliques", 4),
        withMuscle("Abs", 2));

    addExercise("Hanging Knee Raise", "Abdomen", false,
        withMuscle("Abs", 5),
        withMuscle("Hip Flexors", 4),
        withMuscle("Core", 2));

    addExercise("Russian Twist", "Abdomen", false,
        withMuscle("Obliques", 5),
        withMuscle("Abs", 4),
        withMuscle("Core", 2));

    addExercise("Dumbell Side Bend", "Abdomen", false,
        withMuscle("Obliques", 5),
        withMuscle("Abs", 4),
        withMuscle("Core", 2));

    addExercise("Wheel Roll Out", "Abdomen", false,
        withMuscle("Abs", 5),
        withMuscle("Core", 4),
        withMuscle("Lats", 2));

    //Shoulder
    addExercise("Dumbell Arnold Press", "Shoulder", true,
        withMuscle("Deltoids", 5),
        withMuscle("Front Delts", 4));

    addExercise("Barbell Military Press", "Shoulder", true,
        withMuscle("Deltoids", 5),
        withMuscle("Front Delts", 4));

    addExercise("Dumbell Shoulder Press", "Shoulder", true,
        withMuscle("Deltoids", 5),
        withMuscle("Front Delts", 4));

    addExercise("Dumbell Lateral Raise", "Shoulder", false,
        withMuscle("Deltoids", 5),
        withMuscle("Front Delts", 4));

    addExercise("Cable Lateral Raise", "Shoulder", false,
        withMuscle("Deltoids", 5),
        withMuscle("Front Delts", 4));

    addExercise("Dumbell Bent Reverse Fly", "Shoulder", false,
        withMuscle("Rear Delts", 5),
        withMuscle("Deltoids", 3));

    addExercise("Cable Bent Reverse Fly", "Shoulder", false,
        withMuscle("Rear Delts", 5),
        withMuscle("Deltoids", 3));

    addExercise("Cable Rear Delt", "Shoulder", false,
        withMuscle("Rear Delts", 5),
        withMuscle("Upper Back", 3),
        withMuscle("Deltoids", 2));

    addExercise("Cable Upright Row", "Shoulder", false,
        withMuscle("Deltoids", 4));

    addExercise("Barbell Upright Row", "Shoulder", false,
        withMuscle("Deltoids", 4));

    //Biceps
    addExercise("Dumbell Biceps Curl", "Arm", false,
        withMuscle("Biceps", 5),
        withMuscle("Forearm", 3),
        withMuscle("Brachialis", 3));

    addExercise("Barbell Biceps Curl", "Arm", false,
        withMuscle("Biceps", 5),
        withMuscle("Forearm", 3),
        withMuscle("Brachialis", 3));

    addExercise("Cable Biceps Curl", "Arm", false,
        withMuscle("Biceps", 5),
        withMuscle("Forearm", 3),
        withMuscle("Brachialis", 3));

    addExercise("Dumbell Hammer Curl", "Arm", false,
        withMuscle("Biceps", 5),
        withMuscle("Forearm", 3),
        withMuscle("Brachialis", 3));

    addExercise("Cable Rope Hammer Curl", "Arm", false,
        withMuscle("Biceps", 5),
        withMuscle("Forearm", 3),
        withMuscle("Brachialis", 3));

    //Triceps
    addExercise("Dumbell Overhead Triceps Extension", "Arm", false,
        withMuscle("Triceps", 5));

    addExercise("Cable Rope Overhead Triceps Extension", "Arm", false,
        withMuscle("Triceps", 5));

    addExercise("Nosebreaker", "Arm", false,
        withMuscle("Triceps", 5),
        withMuscle("Forearm", 3));

    addExercise("Cable Rope Triceps Pressdown", "Arm", false,
        withMuscle("Triceps", 5),
        withMuscle("Forearm", 3));

    addExercise("Cable Triceps Pressdown", "Arm", false,
        withMuscle("Triceps", 5),
        withMuscle("Forearm", 3));

    //Multijoint Lower Body
    addExercise("Walking Lunge", "Lower Body", true,
        withMuscle("Quads", 5),
        withMuscle("Glutes", 4));

    addExercise("Barbell Lunge", "Lower Body", true,
        withMuscle("Quads", 5),
        withMuscle("Glutes", 4));

    addExercise("Dumbell Lunge", "Lower Body", true,
        withMuscle("Quads", 5),
        withMuscle("Glutes", 4));

    addExercise("Barbell Front Squat", "Lower Body", true,
        withMuscle("Quads", 5),
        withMuscle("Glutes", 5),
        withMuscle("Upper Back", 3),
        withMuscle("Core", 3),
        withMuscle("Abs", 2));

    addExercise("Barbell Back Squat", "Lower Body", true,
        withMuscle("Quads", 5),
        withMuscle("Glutes", 5),
        withMuscle("Upper Back", 3),
        withMuscle("Core", 3),
        withMuscle("Abs", 2));

    addExercise("Bulgarian Squat", "Lower Body", true,
        withMuscle("Quads", 5),
        withMuscle("Glutes", 5));

    addExercise("Leg Press", "Lower Body", true,
        withMuscle("Quads", 5),
        withMuscle("Glutes", 5));

    addExercise("Deadlift", "Lower Body", true,
        withMuscle("Quads", 5),
        withMuscle("Glutes", 5),
        withMuscle("Upper Back", 4),
        withMuscle("Core", 4),
        withMuscle("Abs", 2));

    addExercise("Stiff Legged Deadlift", "Lower Body", true,
        withMuscle("Glutes", 5),
        withMuscle("Upper Back", 3));

    //Misc
    addExercise("Leg Extension", "Lower Body", false,
        withMuscle("Quads", 5));

    addExercise("Lying Leg Curl", "Lower Body", false,
        withMuscle("Glutes", 5));

    addExercise("Barbell Calf Raise", "Lower Body", false,
        withMuscle("Calf", 5));

    addExercise("Toe Press", "Lower Body", false,
        withMuscle("Calf", 5));

    addExercise("Hyperextension", "Lower Back", false,
        withMuscle("Lower back", 5),
        withMuscle("Glutes", 3));

  }

  private ExerciseMuscleRelationEntity withMuscle(String muscleName, Integer utilization) {

    MuscleEntity muscleEntity = muscleJpaRepository.findByName(muscleName.toUpperCase());
    ExerciseMuscleRelationEntity exerciseMuscleRelationEntity = new ExerciseMuscleRelationEntity();
    exerciseMuscleRelationEntity.setMuscleEntity(muscleEntity);
    exerciseMuscleRelationEntity.setRatio(utilization);
    return exerciseMuscleRelationEntity;
  }

  private void addExercise(String name, String bodyPart, Boolean isCompound, ExerciseMuscleRelationEntity... exerciseMuscleRelationEntityList) {

    ExerciseEntity exerciseEntity = new ExerciseEntity();
    exerciseEntity.setName(name.toUpperCase());
    exerciseEntity.setBodyPart(bodyPart.toUpperCase());
    exerciseEntity.setIsCompound(isCompound);
    exerciseEntity.setExerciseMuscleRelationEntities(Arrays.stream(exerciseMuscleRelationEntityList).collect(Collectors.toSet()));
    Arrays.stream(exerciseMuscleRelationEntityList).forEach(exerciseMuscleRelationEntity -> exerciseMuscleRelationEntity.setExerciseEntity(exerciseEntity));

    exerciseJpaRepository.save(exerciseEntity);
  }

}
