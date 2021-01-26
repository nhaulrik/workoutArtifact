package com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.programme;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "programme")
public class ProgrammeEntity {


  @Id
  private String id;
  @Column
  private LocalDateTime creationDateTime;

  @OneToMany(mappedBy = "programmeEntity")
  private List<PhaseEntity> phaseEntities;

  public UUID getId() {
    return UUID.fromString(this.id);
  }

}
