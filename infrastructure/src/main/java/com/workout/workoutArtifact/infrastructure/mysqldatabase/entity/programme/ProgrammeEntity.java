package com.workout.workoutArtifact.infrastructure.mysqldatabase.entity.programme;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
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
  private String name;
  @Column
  private String description;
  @Column
  private LocalDate creationDate;

  @OneToMany(mappedBy = "programmeEntity", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<PhaseEntity> phaseEntities;

  public UUID getId() {
    return UUID.fromString(this.id);
  }

}
