package com.workout.workoutArtifact.infrastructure.common.mapper;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import com.workout.workoutArtifact.domain.workoutset.model.WorkoutSet;
import com.workout.workoutArtifact.endpoint.dto.WorkoutSetDto;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.ExerciseEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.SessionEntity;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.WorkoutSetEntity;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import org.junit.Test;

public class WorkoutSetMapperTest {

  private final EntityManager entityManager = mock(EntityManager.class);
  private final WorkoutSetMapper workoutSetMapper = new WorkoutSetMapper(entityManager);

  private final Double weight = 60d;
  private final Boolean single = false;
  private final int repetitions = 8;
  private final int repetitionMaximum = 10;
  private final int setNumber = 1;
  private final Long exerciseId = 1L;
  private final Long id = 2L;
  private final UUID sessionId = UUID.randomUUID();

  @Test
  public void domainToDto() {

    WorkoutSet workoutSet = WorkoutSet.builder()
        .weight(weight)
        .single(single)
        .repetitions(repetitions)
        .repetitionMaximum(repetitionMaximum)
        .setNumber(setNumber)
        .exerciseId(exerciseId)
        .id(id)
        .sessionId(sessionId)
        .build();

    WorkoutSetDto workoutSetDto = workoutSetMapper.toDto(workoutSet);

    assertThat(workoutSetDto.getWeight(), is(workoutSet.getWeight()));
    assertThat(workoutSetDto.isSingle(), is(workoutSet.getSingle()));
    assertThat(workoutSetDto.getRepetitions(), is(workoutSet.getRepetitions()));
    assertThat(workoutSetDto.getRepetitionMaximum(), is(workoutSet.getRepetitionMaximum()));
    assertThat(workoutSetDto.getSetNumber(), is(workoutSet.getSetNumber()));
    assertThat(workoutSetDto.getExerciseId(), is(workoutSet.getExerciseId()));
    assertThat(workoutSetDto.getId(), is(workoutSet.getId()));
    assertThat(workoutSetDto.getSessionId(), is(workoutSet.getSessionId()));
  }

//  @Test
//  public void dtoToDomain() {
//
//    WorkoutSetDto workoutSetDto = WorkoutSetDto.builder()
//        .weight(weight)
//        .single(single)
//        .repetitions(repetitions)
//        .repetitionMaximum(repetitionMaximum)
//        .setNumber(setNumber)
//        .exerciseId(exerciseId)
//        .id(id)
//        .sessionId(sessionId)
//        .build();
//
//    WorkoutSet workoutSet = workoutSetMapper.toDomain(workoutSetDto);
//
//    assertThat(workoutSetDto.getWeight(), is(workoutSet.getWeight()));
//    assertThat(workoutSetDto.isSingle(), is(workoutSet.getSingle()));
//    assertThat(workoutSetDto.getRepetitions(), is(workoutSet.getRepetitions()));
//    assertThat(workoutSetDto.getRepetitionMaximum(), is(workoutSet.getRepetitionMaximum()));
//    assertThat(workoutSetDto.getSetNumber(), is(workoutSet.getSetNumber()));
//    assertThat(workoutSetDto.getExerciseId(), is(workoutSet.getExerciseId()));
//    assertThat(workoutSetDto.getId(), is(workoutSet.getId()));
//    assertThat(workoutSetDto.getSessionId(), is(workoutSet.getSessionId()));
//  }

  @Test
  public void entityToDomain() {

    ExerciseEntity exerciseEntity = mock(ExerciseEntity.class);
    SessionEntity sessionEntity = mock(SessionEntity.class);

    doReturn(exerciseId).when(exerciseEntity).getId();
    doReturn(exerciseEntity).when(entityManager).getReference(ExerciseEntity.class, exerciseId);

    doReturn(sessionId).when(sessionEntity).getId();
    doReturn(sessionEntity).when(entityManager).getReference(SessionEntity.class, sessionId);

    WorkoutSetEntity workoutSetEntity = new WorkoutSetEntity();
    workoutSetEntity.setWeight(weight);
    workoutSetEntity.setSingle(single);
    workoutSetEntity.setRepetitions(repetitions);
    workoutSetEntity.setRepetitionMaximum(repetitionMaximum);
    workoutSetEntity.setSetNumber(setNumber);
    workoutSetEntity.setExerciseEntity(exerciseEntity);
    workoutSetEntity.setId(id);
    workoutSetEntity.setSessionEntity(sessionEntity);

    WorkoutSet workoutSet = workoutSetMapper.toDomain(workoutSetEntity);

    assertHasSameProperties(workoutSetEntity, workoutSet);
  }

  @Test
  public void domainToEntity() {
    WorkoutSet workoutSet = getWorkoutSetForTest();

    SessionEntity sessionEntity = mock(SessionEntity.class);

    ExerciseEntity exerciseEntity = mock(ExerciseEntity.class);

    doReturn(exerciseEntity)
        .when(entityManager).getReference(ExerciseEntity.class, workoutSet.getExerciseId());

    doReturn(exerciseId)
        .when(exerciseEntity).getId();

    doReturn(1L)
        .when(sessionEntity).getId();
    doReturn(sessionEntity)
        .when(entityManager).getReference(SessionEntity.class, workoutSet.getSessionId());

    WorkoutSetEntity workoutSetEntity = workoutSetMapper.toEntity(workoutSet);

    assertHasSameProperties(workoutSetEntity, workoutSet);
  }

  @Test
  public void toEntityFromList() {
    WorkoutSet workoutSet1 = getWorkoutSetForTest();
    WorkoutSet workoutSet2 = getWorkoutSetForTest();

    SessionEntity sessionEntity = mock(SessionEntity.class);
    ExerciseEntity exerciseEntity = mock(ExerciseEntity.class);

    doReturn(exerciseEntity)
        .when(entityManager).getReference(ExerciseEntity.class, workoutSet1.getExerciseId());

    doReturn(exerciseId)
        .when(exerciseEntity).getId();

    doReturn(1L)
        .when(sessionEntity).getId();
    doReturn(sessionEntity)
        .when(entityManager).getReference(SessionEntity.class, workoutSet1.getSessionId());

    List<WorkoutSetEntity> workoutSetEntities = workoutSetMapper.toEntity(Arrays.asList(workoutSet1, workoutSet2));

    assertHasSameProperties(workoutSetEntities.get(0), workoutSet1);
    assertHasSameProperties(workoutSetEntities.get(1), workoutSet2);
  }

  private WorkoutSet getWorkoutSetForTest() {
    return WorkoutSet.builder()
        .setNumber(1)
        .repetitionMaximum(2)
        .single(true)
        .weight(100d)
        .repetitions(8)
        .exerciseId(1L)
        .exerciseId(1L)
        .sessionId(UUID.randomUUID())
        .build();
  }

  private void assertHasSameProperties(WorkoutSetEntity workoutSetEntity, WorkoutSet workoutSet) {
    assertThat(workoutSetEntity.getSetNumber(), is(equalTo(workoutSet.getSetNumber())));
    assertThat(workoutSetEntity.getRepetitionMaximum(), is(equalTo(workoutSet.getRepetitionMaximum())));
    assertThat(workoutSetEntity.isSingle(), is(equalTo(workoutSet.getSingle())));
    assertThat(workoutSetEntity.getWeight(), is(equalTo(workoutSet.getWeight())));
    assertThat(workoutSetEntity.getRepetitions(), is(equalTo(workoutSet.getRepetitions())));
    assertThat(workoutSetEntity.getSessionEntity().getId(), is(equalTo(workoutSet.getSessionId())));
    assertThat(workoutSetEntity.getExerciseEntity().getId(), is(equalTo(workoutSet.getExerciseId())));
  }
}
