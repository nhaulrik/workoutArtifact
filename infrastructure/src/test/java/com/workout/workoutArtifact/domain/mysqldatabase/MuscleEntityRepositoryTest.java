package com.workout.workoutArtifact.domain.mysqldatabase;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import com.workout.workoutArtifact.common.enums.BodyPartEnum;
import com.workout.workoutArtifact.domain.muscle.model.Muscle;
import com.workout.workoutArtifact.domain.muscle.model.Muscle.NameSpecification;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.MuscleEntityRepository;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.MuscleEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.MuscleEntityMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.mapper.MuscleSpecificationMapper;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.repository.MuscleJpaRepository;
import java.util.Arrays;
import java.util.List;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.mockito.Mockito;

public class MuscleEntityRepositoryTest {

  private final MuscleEntityMapper muscleEntityMapper = mock(MuscleEntityMapper.class);
  private final MuscleJpaRepository muscleJpaRepository = mock(MuscleJpaRepository.class);
  private final MuscleSpecificationMapper muscleSpecificationMapper = mock(MuscleSpecificationMapper.class);
  private final MuscleEntityRepository muscleEntityRepository = new MuscleEntityRepository(muscleEntityMapper, muscleJpaRepository, muscleSpecificationMapper);


  @Test
  public void getMuscles() {

    String someName = "some_name";
    Muscle.NameSpecification nameSpecification = new NameSpecification(Arrays.asList(someName));
    org.springframework.data.jpa.domain.Specification jpaSpecification = mock(org.springframework.data.jpa.domain.Specification.class);

    MuscleEntity muscleEntity = mock(MuscleEntity.class);

    Muscle muscle = Muscle.builder()
        .name(someName)
        .bodyPart(BodyPartEnum.SHOULDER.name())
        .build();

    doReturn(jpaSpecification)
        .when(muscleSpecificationMapper).toJpaSpecification(nameSpecification);

    doReturn(Arrays.asList(muscleEntity))
        .when(muscleJpaRepository).findAll(jpaSpecification);

    Mockito.doReturn(muscle)
        .when(muscleEntityMapper).toDomainObject(muscleEntity);

    List<Muscle> muscleList = muscleEntityRepository.getMuscles(nameSpecification);

    assertThat(muscleList.size(), is(1));
    assertThat(muscleList.get(0), CoreMatchers.is(muscle));
  }

  @Test
  public void addMuscles() {

    String chestString = "chest";
    String frontDeltString = "frontDelt";

    Muscle chest = mock(Muscle.class);
    Muscle shoulder = mock(Muscle.class);

    MuscleEntity chestEntity = new MuscleEntity(chestString, "");
    MuscleEntity shoulderEntity = new MuscleEntity(frontDeltString, "");

    Mockito.doReturn(chestEntity)
        .when(muscleEntityMapper).toEntity(chest);

    Mockito.doReturn(shoulderEntity)
        .when(muscleEntityMapper).toEntity(shoulder);

    Mockito.doReturn(chestEntity)
        .when(muscleJpaRepository).save(chestEntity);

    Mockito.doReturn(shoulderEntity)
        .when(muscleJpaRepository).save(shoulderEntity);

    String resultString = muscleEntityRepository.addMuscles(Arrays.asList(chest, shoulder));
    assertThat(resultString.contains(chestString), is(true));
    assertThat(resultString.contains(frontDeltString), is(true));
  }
}