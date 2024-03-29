package com.workout.workoutArtifact.programme;

import com.workout.workoutArtifact.mysql.mapper.programme.ProgrammeEntityMapper;
import com.workout.workoutArtifact.programme.Programme;
import com.workout.workoutArtifact.programme.ProgrammeRepository;
import com.workout.workoutArtifact.mysql.repository.ProgrammeJpaRepository;
import com.workout.workoutArtifact.specification.Specification;
import com.workout.workoutArtifact.mysql.entity.programme.ProgrammeEntity;
import com.workout.workoutArtifact.mysql.mapper.programme.ProgrammeSpecificationMapper;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class ProgrammeEntityRepository implements ProgrammeRepository {

  private final ProgrammeJpaRepository programmeJpaRepository;
  private final ProgrammeEntityMapper programmeEntityMapper;
  private final ProgrammeSpecificationMapper programmeSpecificationMapper;

  @Override
  public List<Programme> getProgrammes(Specification<Programme> programmeSpecification) {
    org.springframework.data.jpa.domain.Specification<ProgrammeEntity> jpaSpecification = programmeSpecificationMapper.toJpaSpecification(programmeSpecification);

    return programmeJpaRepository.findAll(jpaSpecification).stream()
        .map(programmeEntityMapper::toDomain)
        .filter(programmeSpecification::isSatisfiedBy)
        .collect(Collectors.toList());
  }

  @Override
  public UUID save(Programme programme) {
    ProgrammeEntity programmeEntity = programmeEntityMapper.toEntity(programme);
    return programmeJpaRepository.save(programmeEntity).getId();
  }

  @Override
  @Transactional
  public Boolean delete(Specification<Programme> programmeSpecification) {

    org.springframework.data.jpa.domain.Specification<ProgrammeEntity> jpaSpecification = programmeSpecificationMapper.toJpaSpecification(programmeSpecification);
    List<String> idsToDelete = programmeJpaRepository.findAll(jpaSpecification).stream()
        .map(programmeEntity -> programmeEntity.getId().toString())
        .collect(Collectors.toList());

    programmeJpaRepository.deleteAllByIdIn(idsToDelete);
    return programmeJpaRepository.findAll(jpaSpecification).isEmpty();
  }
}
