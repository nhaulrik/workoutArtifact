package com.workout.workoutArtifact.infrastructure.mysqldatabase;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import com.workout.workoutArtifact.domain.muscle.model.Muscle;
import com.workout.workoutArtifact.infrastructure.common.mapper.MuscleMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.MuscleEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.MuscleSpecificationMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.MuscleJpaRepository;
import java.util.Arrays;
import org.junit.Test;

public class MuscleEntityRepositoryTest {

  private final MuscleMapper muscleMapper = mock(MuscleMapper.class);
  private final MuscleJpaRepository muscleJpaRepository = mock(MuscleJpaRepository.class);
  private final MuscleSpecificationMapper muscleSpecificationMapper = mock(MuscleSpecificationMapper.class);
  private final MuscleEntityRepository muscleEntityRepository = new MuscleEntityRepository(muscleMapper, muscleJpaRepository, muscleSpecificationMapper);

  @Test
  public void addMuscles() {

    String chestString = "chest";
    String frontDeltString = "frontDelt";

    Muscle chest = mock(Muscle.class);
    Muscle shoulder = mock(Muscle.class);

    MuscleEntity chestEntity = new MuscleEntity(chestString, "");
    MuscleEntity shoulderEntity = new MuscleEntity(frontDeltString, "");

    doReturn(chestEntity)
        .when(muscleMapper).toEntity(chest);

    doReturn(shoulderEntity)
        .when(muscleMapper).toEntity(shoulder);

    doReturn(chestEntity)
        .when(muscleJpaRepository).save(chestEntity);

    doReturn(shoulderEntity)
        .when(muscleJpaRepository).save(shoulderEntity);

    String resultString = muscleEntityRepository.addMuscles(Arrays.asList(chest, shoulder));
    assertThat(resultString.contains(chestString), is(true));
    assertThat(resultString.contains(frontDeltString), is(true));
  }
}