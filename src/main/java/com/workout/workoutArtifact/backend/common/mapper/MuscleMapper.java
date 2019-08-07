package com.workout.workoutArtifact.backend.common.mapper;

import com.workout.workoutArtifact.backend.common.enums.BodyPartEnum;
import com.workout.workoutArtifact.backend.common.enums.ExerciseEnum;
import com.workout.workoutArtifact.backend.common.enums.MuscleEnum;
import com.workout.workoutArtifact.backend.mysqldatabase.entity.MuscleEntity;
import com.workout.workoutArtifact.domain.model.Exercise;
import com.workout.workoutArtifact.domain.model.Muscle;
import com.workout.workoutArtifact.endpoint.dto.MuscleDto;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class MuscleMapper {

  public MuscleDto toDto(Muscle muscle) {
    return new MuscleDto(
        muscle.getMuscle().toString()
    );
  }

  public Muscle toDomainObject(MuscleEntity muscleEntity) {
    Muscle muscle = Muscle.builder()
        .muscle(MuscleEnum.valueOf(muscleEntity.getName()))
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
    muscleEntity.setName(muscle.getMuscle().toString());
    muscleEntity.setBodyPart(muscle.getBodyPart().toString());
    return muscleEntity;
  }

}
