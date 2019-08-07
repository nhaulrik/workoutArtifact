package com.workout.workoutArtifact.infrastructure.common.mapper;

import com.workout.workoutArtifact.infrastructure.common.enums.BodyPartEnum;
import com.workout.workoutArtifact.infrastructure.common.enums.MuscleEnum;
import com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.MuscleEntity;
import com.workout.workoutArtifact.domain.model.Exercise;
import com.workout.workoutArtifact.domain.muscle.model.Muscle;
import com.workout.workoutArtifact.endpoint.dto.MuscleDto;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class MuscleMapper {

  public MuscleDto toDto(Muscle muscle) {
    return new MuscleDto(
        muscle.getName()
    );
  }

  public Muscle toDomainObject(MuscleEntity muscleEntity) {
    Muscle muscle = Muscle.builder()
        .name(muscleEntity.getName())
        .bodyPart(BodyPartEnum.valueOf(muscleEntity.getBodyPart()))
        .build();

    muscle.setExerciseList(muscleEntity.getExerciseSet().stream()
        .map(entity -> new Exercise(
                entity.getName(),
                entity.getIsMultiJoint(),
                BodyPartEnum.valueOf(entity.getPrimaryBodyPart()),
                new ArrayList<>() // TODO: 04-04-2019 check this
            )
        ).collect(Collectors.toList()));
    return muscle;
  }

  public MuscleEntity toEntity(Muscle muscle) {
    MuscleEntity muscleEntity = new MuscleEntity();
    muscleEntity.setName(muscle.getName());
    muscleEntity.setBodyPart(muscle.getBodyPart().toString());
    return muscleEntity;
  }

}
