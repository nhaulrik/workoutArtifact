package com.workout.workoutArtifact.domain.service;

import com.workout.workoutArtifact.backend.common.mapper.Mapper;
import com.workout.workoutArtifact.domain.model.Muscle;
import com.workout.workoutArtifact.endpoint.specification.MuscleSpecification;
import com.workout.workoutArtifact.endpoint.specification.MuscleSpecification.SearchCriteria;
import com.workout.workoutArtifact.backend.mysqldatabase.entity.MuscleEntity;
import com.workout.workoutArtifact.backend.mysqldatabase.repository.MuscleRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MuscleService {

  private final MuscleRepository muscleRepository;
  private final Mapper mapper;

  @Autowired
  public MuscleService(MuscleRepository muscleRepository, Mapper mapper) {
    this.muscleRepository = muscleRepository;
    this.mapper = mapper;
  }

  public String addMuscles(List<Muscle> muscles) {

    muscles.stream()
        .map(mapper::toEntity)
        .forEach(muscleEntity -> muscleRepository.save(muscleEntity));

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
        .map(mapper::toDomainObject)
        .collect(Collectors.toList());
  }

}
