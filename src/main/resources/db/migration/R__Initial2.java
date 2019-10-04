//package db.migration;
//
//import com.workout.workoutArtifact.configuration.SpringUtility;
//import com.workout.workoutArtifact.domain.muscle.model.Muscle;
//import com.workout.workoutArtifact.infrastructure.common.mapper.MuscleMapper;
//import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.MuscleJpaRepository;
//import org.flywaydb.core.api.migration.BaseJavaMigration;
//import org.flywaydb.core.api.migration.Context;
//
//public class R__Initial2 extends BaseJavaMigration {
//
//
//  MuscleJpaRepository muscleJpaRepository = SpringUtility.getBean(MuscleJpaRepository.class);
//  MuscleMapper muscleMapper = SpringUtility.getBean(MuscleMapper.class);
//
//  public void migrate(Context context) {
//
//
//    Muscle muscle = Muscle.builder()
//        .bodyPart("somePart")
//        .name("new_name")
//        .id(1L)
//        .build();
//
//    muscleJpaRepository.save(muscleMapper.toEntity(muscle));
//  }
//
//}
