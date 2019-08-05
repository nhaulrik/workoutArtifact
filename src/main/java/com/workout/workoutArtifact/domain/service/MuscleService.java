package com.workout.workoutArtifact.domain.service;

import com.workout.workoutArtifact.backend.common.mapper.MuscleMapper;
import com.workout.workoutArtifact.backend.mysqldatabase.entity.MuscleEntity;
import com.workout.workoutArtifact.backend.mysqldatabase.repository.MuscleRepository;
import com.workout.workoutArtifact.domain.model.Muscle;
import com.workout.workoutArtifact.endpoint.specification.MuscleSpecification;
import com.workout.workoutArtifact.endpoint.specification.MuscleSpecification.SearchCriteria;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MuscleService {

  private final MuscleRepository muscleRepository;
  private final MuscleMapper muscleMapper;

  public String addMuscles(List<Muscle> muscles) {

    muscles.stream()
        .map(muscleMapper::toEntity)
        .forEach(muscleRepository::save);

    return "Muscles added: " + muscles.size() + ". " + muscles.toString();
  }

  public List<Muscle> getMuscles(List<String> muscleNames) {

    List<MuscleEntity> resultEntities = new ArrayList<>();

    for (String muscleName : muscleNames) {
      if (muscleName.contains("*")) {
        resultEntities.addAll(muscleRepository.findAll());
      } else {
        MuscleSpecification muscleSpecification = new MuscleSpecification(
            new SearchCriteria("name", ":", muscleName));
        resultEntities.addAll(muscleRepository.findAll(muscleSpecification));
      }
    }

    return resultEntities.stream()
        .map(muscleMapper::toDomainObject)
        .collect(Collectors.toList());
  }

}
